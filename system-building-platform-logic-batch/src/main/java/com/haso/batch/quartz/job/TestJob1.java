package com.haso.batch.quartz.job;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestJob1 extends BasicJob {
	
	@Override
	protected void run(String param) {
		log.info("现在正在执行第一个任务！参数：" + param);
	}

}
