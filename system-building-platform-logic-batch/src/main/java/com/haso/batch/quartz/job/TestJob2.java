package com.haso.batch.quartz.job;

import org.springframework.stereotype.Service;

import com.haso.common.exception.AppException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestJob2 extends BasicJob {
	
	@Override
	protected void run(String param) {
		log.info("现在正在执行第二个任务！参数：" + param);
		throw new AppException("出错啦！！！！！");
	}

}
