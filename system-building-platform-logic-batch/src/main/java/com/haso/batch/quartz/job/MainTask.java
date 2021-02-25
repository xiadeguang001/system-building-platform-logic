package com.haso.batch.quartz.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.MDC;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.haso.common.api.vo.SendMailInfo;
import com.haso.common.constant.BatchConstant;
import com.haso.common.exception.AppException;
import com.haso.common.util.ApplicationContextUtil;
import com.haso.common.util.DateUtil;
import com.haso.common.util.ExceptionUtil;
import com.haso.common.util.LogBackUtil;
import com.haso.common.util.SendMailUtil;
import com.haso.batch.quartz.entity.BatchJobInfo;
import com.haso.batch.quartz.entity.BatchSystemConfig;
import com.haso.batch.quartz.entity.BatchTaskInfo;
import com.haso.batch.quartz.entity.BatchTaskJobs;
import com.haso.batch.quartz.entity.BatchTaskRecord;
import com.haso.batch.quartz.service.BatchJobInfoService;
import com.haso.batch.quartz.service.BatchSystemConfigService;
import com.haso.batch.quartz.service.BatchTaskInfoService;
import com.haso.batch.quartz.service.BatchTaskRecordService;

import lombok.extern.slf4j.Slf4j;

/**
 * 基础定时任务
 * 
 * @Author Scott
 */
@Slf4j
@DisallowConcurrentExecution
public class MainTask implements InterruptableJob {
	
	public static final String PARM_NAME_TASK_JOBS = "taskJobs";
	
	public static final String PARM_NAME_TASK_Id = "taskId";
	
	private static final String SERVICE_NAME_BATCH_JOB = "batchJobInfoService";
	
	private static final String SERVICE_NAME_BATCH_TASK = "batchTaskInfoService";
	
	private static final String SERVICE_NAME_BATCH_TASK_SERVICE = "batchTaskRecordService";
	
	private static final String SERVICE_NAME_BATCH_SYSTEM_CONFIG = "batchSystemConfigService";
	
	private static final String LOG_KEY = "taskFileName";
	
	private static final String SLASH = "/";
	
	private static final String UNDERLINE = "_";
	
	private BatchTaskInfoService taskService;
	private BatchJobInfoService jobService;
	private BatchTaskRecordService recordService;
	private BatchSystemConfigService batchSystemConfigService;

	/** 子Job列表 */
	private List<BatchTaskJobs> taskJobs;
	
	/** 计划Id */
	private Integer taskId;
	
	/** 计划信息 */
	private BatchTaskInfo batchTask;
	
	/** 执行记录 */
	private BatchTaskRecord record;
	
	/** 当前任务 */
	private BatchJobInfo currentJob;
	
	/** 开始时间 */
	private Date startTime;
	
	/** 重试次数 */
	private Integer retryTimes = 0;
	
	/** 定义重试次数 */
	private Integer retryTimesDefined = 0;
	
	/** 邮件头 */
	private String mailHead;
	
	/** 邮件脚 */
	private String mailFoot;
	
	/** 通知人 */
	private String noticeEmails;
	
	/** 日志文件名 */
	private String fileName;
	
	/** 处理线程 */
	private volatile Thread thisThread;

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public void setTaskJobs(List<BatchTaskJobs> taskJobs) {
		this.taskJobs = taskJobs;
	}
	
	/**
	 * 创建执行记录
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createTaskRecord() {
		record = new BatchTaskRecord();
		record.setTaskId(taskId);
		record.setStartTime(startTime);
		Map<String, String> fileInfoMap = LogBackUtil.getSiftingFilePath(fileName);
		record.setLogFilePath(fileInfoMap.get(LogBackUtil.FILE_PATH));
		record.setLogFileName(fileInfoMap.get(LogBackUtil.FILE_NAME));
		record.setResult(BatchConstant.TASK_RECORD_STATUS_PROCESSING);
		recordService.save(record);
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		taskService = (BatchTaskInfoService)ApplicationContextUtil.getBean(SERVICE_NAME_BATCH_TASK);
		jobService = (BatchJobInfoService)ApplicationContextUtil.getBean(SERVICE_NAME_BATCH_JOB);
		recordService = (BatchTaskRecordService)ApplicationContextUtil.getBean(SERVICE_NAME_BATCH_TASK_SERVICE);
		batchSystemConfigService = (BatchSystemConfigService)ApplicationContextUtil.getBean(SERVICE_NAME_BATCH_SYSTEM_CONFIG);
		batchTask = taskService.getById(taskId);
		initParams();
		log.info("【" + batchTask.getId() + "】【" + batchTask.getTaskName() + "】执行开始");
		createTaskRecord();
	}
	
	/**
	 * 创建日志文件名
	 * @return
	 */
	private String createFileName() {
		StringBuffer sf = new StringBuffer();
		sf.append(taskId);
		sf.append(SLASH);
		sf.append(taskId);
		sf.append(UNDERLINE);
		sf.append(DateUtil.YYYYMMDDHHMMSS.format(startTime));
		return sf.toString();
	}
	
	/**
	 * 初始化参数
	 */
	private void initParams() {
		BatchSystemConfig configInfo = batchSystemConfigService.getConfigInfo();
		retryTimesDefined = batchTask.getRetryTimes();
		noticeEmails = batchTask.getNoticeEmails();
		mailHead = configInfo.getEmailHead();
		mailFoot = configInfo.getEmailFoot();
		noticeEmails = configInfo.getErrorNoticeEmails();
		if (retryTimesDefined == null) {
			retryTimesDefined = configInfo.getErrorRetryTimes();
		}
		if (noticeEmails == null) {
			noticeEmails = configInfo.getErrorNoticeEmails();
		}
	}
	
	/**
	 * 执行计划
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			thisThread = Thread.currentThread();
			startTime = new Date();
			fileName = createFileName();
			MDC.put(LOG_KEY, fileName);
			init();
		} catch (Exception e) {
			errorLog(e);
			endWithException(e);
			MDC.remove(LOG_KEY);
		}
		processBody();
	}
	
	/**
	 * 主要执行体
	 */
	private void processBody() {	
		try {
			process();
			end();
		} catch (Exception e) {
			errorLog(e);
			retryTimes++;
			if (retryTimes <= retryTimesDefined) {
				log.info(String.format("开始进行:%s第次 重试！", retryTimes));
				processBody();
				return;
			}
			endWithException(e);
		} finally {
			MDC.remove(LOG_KEY);
		}
	}
	
	/**
	 * 循环任务列表执行
	 * @throws InterruptedException
	 */
	public void process() throws InterruptedException {
		try {
			if (!CollectionUtils.isEmpty(taskJobs)) {
				for (BatchTaskJobs taskJob : taskJobs) {
					currentJob = jobService.getById(taskJob.getJobId());
					Class<?> handlerCLazz = Class.forName(currentJob.getJobHandler());
					BasicJob handlerInstance = (BasicJob)ApplicationContextUtil.getBean(handlerCLazz);
					log.info("【" + currentJob.getId() + "】【" + currentJob.getJobName() + "】执行开始");
					handlerInstance.execute(taskJob.getParams());
					log.info("【" + currentJob.getId() + "】【" + currentJob.getJobName() + "】执行结束， 执行时长：" + DateUtil.getTimeDiffString(record.getStartTime(), new Date()));
				}
			}
		} catch (Exception e) {
			throw new AppException("执行主处理失败", e);
		}
	}
	
	/**
	 * 执行结束
	 */
	private void end() {
		record.setEndTime(new Date());
		record.setProcessTime(DateUtil.getTimeDiffString(record.getStartTime(), record.getEndTime()));
		record.setResult(BatchConstant.TASK_RECORD_STATUS_SUCCESS);
		recordService.updateById(record);
		
		log.info("【" + batchTask.getId() + "】【" + batchTask.getTaskName() + "】执行结束，执行时长："+ record.getProcessTime());
	}
	
	/**
	 * 出错记录更新
	 * @param e
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void endExceptionRecord(Exception e) {
		record.setResult(BatchConstant.TASK_RECORD_STATUS_FAILED);
		record.setEndTime(new Date());
		record.setProcessTime(DateUtil.getTimeDiffString(record.getStartTime(), record.getEndTime()));
		record.setErrorHandleStatus(BatchConstant.BATCH_ERROR_HANDLE_STATUS_UNHANDLED);
		record.setErrorContent(ExceptionUtil.getCauseStringWithLimit(e, 900));
		recordService.updateById(record);
	}
	
	/**
	 * 中断记录更新
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void endStopRecord() {
		record.setResult(BatchConstant.TASK_RECORD_STATUS_STOP);
		record.setEndTime(new Date());
		record.setProcessTime(DateUtil.getTimeDiffString(record.getStartTime(), record.getEndTime()));
		recordService.updateById(record);
	}
	
	/**
	 * 出错结束操作
	 * @param e
	 */
	private void endWithException(Exception e) {
		endExceptionRecord(e);
		sendErrorMail(e);
		throw new AppException("执行失败", e);
	}

	/**
	 * 中断
	 */
	@Override
	public void interrupt() throws UnableToInterruptJobException {
		endStopRecord();
		thisThread.interrupt();
	}
	
	/**
	 * 出错日志
	 * @param e
	 */
	private void errorLog(Exception e) {
		log.error(String.format("任务执行失败 ！  时间:%s", DateUtil.now()), e);
	}
	
	/**
	 * 发送报错邮件
	 * @param e
	 */
	private void sendErrorMail(Exception e) {
		if (Strings.isEmpty(noticeEmails)) return;
		SendMailInfo sendMailInfo = new SendMailInfo();
		sendMailInfo.setFrom("notice_test@haso-soft.com");
		sendMailInfo.setTo(noticeEmails.split(","));
		sendMailInfo.setSubject(getErrorMailSubject());
		sendMailInfo.setText(getErrorMailContent(e));
		SendMailUtil.sendMail(sendMailInfo);
	}
	
	/**
	 * 创建邮件名
	 * @return
	 */
	private String getErrorMailSubject() {
		StringBuffer subject = new StringBuffer();
		subject.append("【计划Id:").append(batchTask.getId()).append("】");
		subject.append("【计划名称:").append(batchTask.getTaskName()).append("】");
		subject.append("【任务Id:").append(currentJob.getId()).append("】");
		subject.append("【任务名称:").append(currentJob.getJobName()).append("】");
		subject.append("【时间:").append(DateUtil.date2Str(DateUtil.TIME_SECOND_SDF)).append("】");
		subject.append("定时任务执行失败!");
		return subject.toString();
	}
	
	/**
	 * 创建邮件内容
	 * @param e
	 * @return
	 */
	private String getErrorMailContent(Exception e) {
		StringBuffer content = new StringBuffer();
		if (!Strings.isEmpty(mailHead)) {
			content.append(mailHead);
			content.append("\n\n\n");
		}
		content.append(ExceptionUtil.getCauseStringWithLimit(e, 2000));
		if (!Strings.isEmpty(mailFoot)) {
			content.append("\n\n\n");
			content.append(mailFoot);
		}
		return content.toString();
	}
	
}
