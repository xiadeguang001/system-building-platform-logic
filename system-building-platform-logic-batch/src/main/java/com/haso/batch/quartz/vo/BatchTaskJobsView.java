package com.haso.batch.quartz.vo;

import com.haso.common.api.vo.BaseView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchTaskJobsView extends BaseView {

	private Integer id;
	
	private Integer taskId;
	
	private Integer jobId;
	
	private String params;
	
	private Integer processOrder;
	
}
