package com.haso.batch.quartz.vo;

import com.haso.common.api.vo.BaseView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchJobView extends BaseView {
	
	private Integer id;
	
	/**job名称*/
	private String jobName;
	
	/**job处理器*/
	private String jobHandler;
	
	private String searchString;
}
