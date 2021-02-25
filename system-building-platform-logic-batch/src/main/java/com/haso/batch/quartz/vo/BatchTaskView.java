package com.haso.batch.quartz.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haso.common.api.vo.BaseView;
import com.haso.common.aspect.annotation.Dict;
import com.haso.common.constant.BatchConstant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchTaskView extends BaseView {

	private Integer id;
	
	private String taskName;
	
	private String cronExpression;
	
	@Dict(dicCode = "task_status")
	private String status;
	
	@Dict(dicCode = "task_execute_status")
	private String taskExecuteStatus;
	
	@Dict(dicCode = "blocking_strategy")
	private String blockingStrategy;
	
	private Integer retryTimes;
	
	private Integer noticeTimes;
	
	private String noticeEmails;
	
	private String description;
	
	private String searchString;
	
	private List<BatchTaskJobsView> taskJobsView;
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date preRunTime;
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date nextRunTime;
	
	public boolean getNormal() {
		return BatchConstant.TASK_EXECUTE_STATUS_NORMAL.equals(taskExecuteStatus);
	}
	
	public boolean getExecuting() {
		return BatchConstant.TASK_EXECUTE_STATUS_EXECUTING.equals(taskExecuteStatus);
	}
	
	public boolean getPause() {
		return BatchConstant.TASK_EXECUTE_STATUS_PAUSE.equals(taskExecuteStatus);
	}
	
	public boolean getError() {
		return BatchConstant.TASK_EXECUTE_STATUS_ERROR.equals(taskExecuteStatus);
	}
}
