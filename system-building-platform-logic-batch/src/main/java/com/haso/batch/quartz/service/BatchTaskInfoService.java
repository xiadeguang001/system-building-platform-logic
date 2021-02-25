package com.haso.batch.quartz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.constant.BatchConstant;
import com.haso.common.exception.AppException;
import com.haso.batch.quartz.entity.BatchTaskInfo;
import com.haso.batch.quartz.entity.BatchTaskJobs;
import com.haso.batch.quartz.job.MainTask;
import com.haso.batch.quartz.mapper.BatchTaskInfoMapper;
import com.haso.batch.quartz.vo.BatchTaskJobsView;
import com.haso.batch.quartz.vo.BatchTaskView;
/**
 * @Description: job管理
 * @Author: jeecg-boot
 * @Date: 2019-04-28
 * @Version: V1.1
 */
@Service
public class BatchTaskInfoService extends ServiceImpl<BatchTaskInfoMapper, BatchTaskInfo> {
	
	private static final String STATIC_MAIN_TASK_NAME = "com.haso.batch.quartz.job.MainTask";
	
	@Resource
	private BatchTaskInfoMapper batchTaskInfoMapper;
	@Resource
	private BatchTaskJobsService batchTaskJobsService;
	
	@Autowired
	private Scheduler scheduler;

	/**
	 * 根据查询条件获取计划分页
	 * @param page 分页
	 * @param view 查询条件
	 * @return
	 */
	public Page<BatchTaskView> searchList(Page<BatchTaskView> page, BatchTaskView view) {
		return batchTaskInfoMapper.searchList(page, view);
	}
	
	/**
	 * 更新或者插入
	 * @param view
	 */
	@Transactional
	public void saveOrUpdate(BatchTaskView view) {
		BatchTaskInfo entity = new BatchTaskInfo();
		if (view.getId() != null) {
			entity = getById(view.getId());
			// 去除job关联
			removeJobs(view.getId());
			// 去除定时信息
			removeSechduler(entity.getTaskName());
		}
		BeanUtils.copyProperties(view, entity);
		saveOrUpdate(entity);
		
		// 创建job关联信息
		List<BatchTaskJobsView> jobs = view.getTaskJobsView();
		List<BatchTaskJobs> taskJobs = new ArrayList<BatchTaskJobs>();
		if (!CollectionUtils.isEmpty(jobs)) {
			for (BatchTaskJobsView job : jobs) {
				BatchTaskJobs jobEntity = new BatchTaskJobs();
				jobEntity.setJobId(job.getJobId());
				jobEntity.setTaskId(entity.getId());
				jobEntity.setParams(job.getParams());
				jobEntity.setProcessOrder(job.getProcessOrder());
				taskJobs.add(jobEntity);
			}
		}
		batchTaskJobsService.saveBatch(taskJobs);
		
		// 创建计划
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put(MainTask.PARM_NAME_TASK_JOBS, taskJobs);
		dataMap.put(MainTask.PARM_NAME_TASK_Id, entity.getId());
		schedulerAdd(view.getTaskName(), entity, new JobDataMap(dataMap), STATIC_MAIN_TASK_NAME);
		
	}
	
	/**
	 * 删除job关联信息
	 * @param taskId
	 */
	private void removeJobs(Integer taskId) {
		List<BatchTaskJobsView> taskJobs = batchTaskJobsService.getTaskJobsByTask(taskId);
		if (!CollectionUtils.isEmpty(taskJobs)) {
			List<Integer> ids = new ArrayList<Integer>();
			for (BatchTaskJobsView taskJob : taskJobs) {
				ids.add(taskJob.getId());
			}
			batchTaskJobsService.removeByIds(ids);
		}
	}
	
	/**
	 * 删除计划
	 * @param view
	 */
	@Transactional
	public void removeBatchTask(BatchTaskView view) {
		BatchTaskInfo task = getById(view.getId());
		removeSechduler(task.getTaskName());
		removeJobs(view.getId());
		removeById(view.getId());
	}
	
	/**
	 * 添加定时计划
	 * @param taskName 计划名称
	 * @param info 计划信息
	 * @param dataMap 传参
	 * @param taskClazz 类名
	 */
	private void schedulerAdd(String taskName, BatchTaskInfo info, JobDataMap dataMap,String taskClazz) {
		try {
			// 启动调度器
			if (!scheduler.isStarted()) {
				scheduler.start();
			}

			// 构建job信息
			JobDetail jobDetail = JobBuilder.newJob(getClass(taskClazz).getClass()).withIdentity(taskName, taskName).usingJobData(dataMap).build();

			// 表达式调度构建器(即任务执行的时间)
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(info.getCronExpression());
			

			// 按新的cronExpression表达式构建一个新的trigger
			CronTriggerImpl trigger = (CronTriggerImpl)TriggerBuilder.newTrigger().withIdentity(taskName, taskName).withSchedule(scheduleBuilder).build();
			
			// 重新启动时，如果晚于下次执行时间，不立刻执行一次
			trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
			scheduler.scheduleJob(jobDetail, trigger);
			
			// 状态是停止的话暂停任务
			if (BatchConstant.TASK_STATUS_STOP.equals(info.getStatus())) {
				scheduler.pauseTrigger(TriggerKey.triggerKey(taskName, taskName));
			}
			
			// 状态是非阻塞的话设置jobDetail为并行
			if (BatchConstant.BLOCKING_STRATEGY_UNBLOCK.equals(info.getBlockingStrategy())) {
				batchTaskInfoMapper.transferJobDetailToConcurrent(taskName);
			}
		} catch (SchedulerException e) {
			throw new AppException("创建定时任务失败", e);
		} catch (RuntimeException e) {
			throw new AppException(e.getMessage(), e);
		}catch (Exception e) {
			throw new AppException("后台找不到该类名：" + taskClazz, e);
		}
	}
	
	private static Job getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (Job) class1.newInstance();
	}
	
	/**
	 * 删除定时任务
	 * 
	 * @param taskName 计划名称
	 */
	private void removeSechduler(String taskName) {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(taskName, taskName));
			scheduler.unscheduleJob(TriggerKey.triggerKey(taskName, taskName));
			scheduler.deleteJob(JobKey.jobKey(taskName, taskName));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException("删除定时任务失败");
		}
	}
	
	/**
	 * 获取所有计划任务
	 * @return
	 */
	public List<BatchTaskView> getAllTasks() {
		return batchTaskInfoMapper.getAllTasks();
	}
	
	/**
	 * 停止计划任务
	 * @param taskId
	 */
	public void stopTask(Integer taskId) {
		BatchTaskInfo task = getById(taskId);
		if (task == null) return;
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(task.getTaskName(), task.getTaskName()));
		} catch (SchedulerException e) {
			throw new AppException("停止触发器失败：", e);
		}
	}
	
	/**
	 * 恢复计划任务
	 * @param taskId
	 */
	public void startTask(Integer taskId) {
		BatchTaskInfo task = getById(taskId);
		if (task == null) return;
		try {
			scheduler.resumeTrigger(TriggerKey.triggerKey(task.getTaskName(), task.getTaskName()));
		} catch (SchedulerException e) {
			throw new AppException("恢复触发器失败：", e);
		}
	}
	
	/**
	 * 立刻执行一次
	 * @param taskId
	 */
	public void runNow(Integer taskId) {
		BatchTaskInfo task = getById(taskId);
		if (task == null) return;
		try {
			scheduler.triggerJob(new JobKey(task.getTaskName(), task.getTaskName()));
		} catch (SchedulerException e) {
			throw new AppException("立刻执行失败：", e);
		}
	}
	
	// 中止操作目前有问题，不使用
	public void stopNow(Integer taskId) {
		BatchTaskInfo task = getById(taskId);
		if (task == null) return;
		try {
//			Trigger trigger = scheduler.getTrigger(new TriggerKey(task.getTaskName(), task.getTaskName()));
//			String fireInstanceId = ((OperableTrigger)trigger).getFireInstanceId();
//			scheduler.interrupt(fireInstanceId);
			
			scheduler.interrupt(new JobKey(task.getTaskName(), task.getTaskName()));
		} catch (SchedulerException e) {
			throw new AppException("中断执行失败：", e);
		}
	}
	
}
