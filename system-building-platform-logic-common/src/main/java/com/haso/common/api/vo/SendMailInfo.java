package com.haso.common.api.vo;

import lombok.Data;

@Data
public class SendMailInfo {

	private String from;
	
	private String[] to;
	
	private String subject;
	
	private String text;
	
	public void setTo(String to) {
		this.to = new String[] {to};
	}

	public void setTo(String... to) {
		this.to = to;
	}
}
