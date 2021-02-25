package com.haso.batch.quartz.vo;

import com.haso.common.api.vo.BaseView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchSystemConfigView extends BaseView {
	
	private Integer id;
	
	private Integer errorRetryTimes;
	
	private String errorNoticeEmails;
	
	private String emailHead;
	
	private String emailFoot;
}
