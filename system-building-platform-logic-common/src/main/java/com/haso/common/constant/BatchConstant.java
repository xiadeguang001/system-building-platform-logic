package com.haso.common.constant;

public class BatchConstant {

	/**
	 * batch执行记录状态 0：处理中 1：成功 2：失败 3：中止
	 */
	public static final String TASK_RECORD_STATUS_PROCESSING = "0";
	public static final String TASK_RECORD_STATUS_SUCCESS = "1";
	public static final String TASK_RECORD_STATUS_FAILED = "2";
	public static final String TASK_RECORD_STATUS_STOP = "3";
	
	/**
	 * batch异常处理状态 0：未处理 1：处理中 2：处理完成
	 */
	public static final String BATCH_ERROR_HANDLE_STATUS_UNHANDLED = "0";
	public static final String BATCH_ERROR_HANDLE_STATUS_HANDLING = "1";
	public static final String BATCH_ERROR_HANDLE_STATUS_HANDLED = "2";
	
	/**
	 * 定时任务状态：0：启用 1：停止
	 */
	public static final String TASK_STATUS_NORMAL = "0";
	public static final String TASK_STATUS_STOP = "1";
	
	/**
	 * 阻塞状态：0：不阻塞 1：阻塞
	 */
	public static final String BLOCKING_STRATEGY_BLOCK = "1";
	public static final String BLOCKING_STRATEGY_UNBLOCK = "0";
	
	/**
	 * Task执行状态：0：正常 1：执行中 2：停止 3：错误
	 */
	public static final String TASK_EXECUTE_STATUS_NORMAL = "0";
	public static final String TASK_EXECUTE_STATUS_EXECUTING = "1";
	public static final String TASK_EXECUTE_STATUS_PAUSE = "2";
	public static final String TASK_EXECUTE_STATUS_ERROR = "3";

	
}
