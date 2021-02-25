package com.haso.common.process;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonProcessRequest {
	
	private String dbDomain;
	
	private String sqlId;
	
	private ProcessType operationMod;
	
	private List<Map<String, Object>> params;
	
	private List<String> inteceptors;

}
