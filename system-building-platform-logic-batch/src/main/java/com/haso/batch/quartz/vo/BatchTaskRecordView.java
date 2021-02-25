package com.haso.batch.quartz.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.haso.common.api.vo.BaseView;
import com.haso.common.aspect.annotation.Dict;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchTaskRecordView extends BaseView {

	private Integer id;
	
	private Integer taskId;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
    @Dict(dicCode = "task_record_state")
	private String result;
	
	private String logFilePath;
	
	private String logFileName;
	
	@Dict(dicCode = "task_error_handle_state")
	private String errorHandleStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date errorHandleTime;
	
	private String errorHandleContent;
	
	private String errorContent;
	
	private String taskName;
	
	private String processTime;
	
	private String searchTimeStart;
	
	private String searchTimeEnd;
	
	public boolean getHasError() {
		return errorHandleStatus != null;
	}
	
}
