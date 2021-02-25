package com.haso.batch.quartz.job;

public abstract class BasicJob {

	protected abstract void run(String param);
	
	protected void init() {
		
	}
	
	protected void end() {
		
	}
	
	public void execute(String param) {
		init();
		run(param);
		end();
	}
}
