package com.haso.batch.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haso.common.api.entity.ComHasoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("batch_task_info")
public class BatchTaskInfo extends ComHasoEntity {

	private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	private String taskName;
	
	private String cronExpression;
	
	/**状态 0正常 -1停止*/
	private String status;
	
	/**阻塞策略 0不阻塞 -1阻塞*/
	private String blockingStrategy;
	
	private Integer retryTimes;
	
	private Integer noticeTimes;
	
	private String noticeEmails;
	
	private String description;
}
