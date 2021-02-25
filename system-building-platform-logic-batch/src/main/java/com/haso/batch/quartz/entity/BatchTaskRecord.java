package com.haso.batch.quartz.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haso.common.api.entity.ComHasoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("batch_task_record")
public class BatchTaskRecord extends ComHasoEntity {

	private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	private Integer taskId;
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startTime;
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;
	
	private String result;
	
	private String logFilePath;
	
	private String logFileName;
	
	private String errorHandleStatus;
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date errorHandleTime;
	
	private String errorHandleContent;
	
	private String errorContent;
	
	private String processTime;
	
}
