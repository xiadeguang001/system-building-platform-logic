package com.haso.batch.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haso.common.api.entity.ComHasoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("batch_system_config")
public class BatchSystemConfig extends ComHasoEntity {

	private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	private Integer errorRetryTimes;
	
	private String errorNoticeEmails;
	
	private String emailHead;
	
	private String emailFoot;
	
}
