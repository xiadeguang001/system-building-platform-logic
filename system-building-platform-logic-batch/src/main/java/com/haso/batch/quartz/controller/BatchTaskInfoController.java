package com.haso.batch.quartz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.common.api.vo.Result;
import com.haso.common.exception.HandlerExcelcntException;
import com.haso.common.util.CommonQueryUtil;
import com.haso.batch.quartz.entity.BatchTaskInfo;
import com.haso.batch.quartz.service.BatchTaskInfoService;
import com.haso.batch.quartz.service.BatchTaskJobsService;
import com.haso.batch.quartz.vo.BatchTaskJobsView;
import com.haso.batch.quartz.vo.BatchTaskView;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**
 * @Description: Task在线管理
 * @Author: jeecg-boot
 * @Date: 2019-01-02
 * @Version:V1.0
 */
@RestController
@RequestMapping("/sys/task")
@Api(tags = "job接口")
@Slf4j
public class BatchTaskInfoController {
	@Autowired
	private BatchTaskInfoService batchTaskInfoService;
	
	@Autowired
	private BatchTaskJobsService batchTaskJobsService;

	/**
	 * 分页列表查询
	 * 
	 * @param batchTaskInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<?> queryPageList(BatchTaskView view, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<Page<BatchTaskView>> result = new Result<>();
		CommonQueryUtil.updateOrderColumn(req, view);
		
		Page<BatchTaskView> page = new Page<BatchTaskView>(pageNo, pageSize);
		Page<BatchTaskView> list = batchTaskInfoService.searchList(page, view);
		log.debug("查询当前页：" + list.getCurrent());
        log.debug("查询当前页数量：" + list.getSize());
        log.debug("查询结果数量：" + list.getRecords().size());
        log.debug("数据总数：" + list.getTotal());
        result.setSuccess(true);
        result.setResult(list);
		return result;

	}

	/**
	 * 添加Task
	 * 
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<?> add(@RequestBody BatchTaskView view) {
		batchTaskInfoService.saveOrUpdate(view);
		return Result.ok("创建Task成功");
	}

	/**
	 * 更新Task
	 * 
	 * @param BatchTaskInfo
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public Result<?> edit(@RequestBody BatchTaskView view) {
		batchTaskInfoService.saveOrUpdate(view);
	    return Result.ok("更新Task成功!");
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public Result<?> delete(@RequestBody BatchTaskView view) {
		excelcntCheck(view);
		batchTaskInfoService.removeBatchTask(view);
        return Result.ok("删除成功!");

	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryById", method = RequestMethod.GET)
	public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
		BatchTaskInfo BatchTaskInfo = batchTaskInfoService.getById(id);
        return Result.ok(BatchTaskInfo);
	}
	
	/**
	 * 获取taskJobs
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/queryTaskJobs", method = RequestMethod.GET)
	public Result<?> getTaskJobs(@RequestParam(name="taskId", required = true) Integer taskId) {
		List<BatchTaskJobsView> taskJobs = batchTaskJobsService.getTaskJobsByTask(taskId);
		return Result.ok(taskJobs);
	}
	
	/**
	 * 获取全部tasks
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/allTasks", method = RequestMethod.GET)
	public Result<?> getAllTasks() {
		List<BatchTaskView> tasks = batchTaskInfoService.getAllTasks();
		return Result.ok(tasks);
	}
	
	/**
	 * 停止
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/stop", method = RequestMethod.PUT)
	public Result<?> stopTask(@RequestBody BatchTaskView view) {
		excelcntCheck(view);
		batchTaskInfoService.stopTask(view.getId());
		return Result.ok();
	}
	
	/**
	 * 开始
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/start", method = RequestMethod.PUT)
	public Result<?> startTask(@RequestBody BatchTaskView view) {
		excelcntCheck(view);
		batchTaskInfoService.startTask(view.getId());
		return Result.ok();
	}
	
	/**
	 * 立刻执行
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/runNow", method = RequestMethod.PUT)
	public Result<?> runNow(@RequestBody BatchTaskView view) {
		excelcntCheck(view);
		batchTaskInfoService.runNow(view.getId());
		return Result.ok();
	}
	
	/**
	 * 中断执行
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/stopNow", method = RequestMethod.PUT)
	public Result<?> stopNow(@RequestBody BatchTaskView view) {
		excelcntCheck(view);
		batchTaskInfoService.stopNow(view.getId());
		return Result.ok();
	}
	
	private void excelcntCheck(BatchTaskView view) {
		BatchTaskInfo entity = batchTaskInfoService.getById(view.getId());
		if (view.getExclcnt() != null) {
			if (!view.getExclcnt().equals(entity.getExclcnt())) {
				throw new HandlerExcelcntException("");
			}
		}
		
	}

}
