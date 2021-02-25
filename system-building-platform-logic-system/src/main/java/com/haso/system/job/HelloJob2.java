package com.haso.system.job;

import com.haso.common.annotation.JobAnnotation;
import com.haso.common.quartz.job.AbstractJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@DisallowConcurrentExecution
@Component
@JobAnnotation(showName = "测试定时任务HelloJob2")
public class HelloJob2 extends AbstractJob {
    private final static Logger LOGGER = LoggerFactory.getLogger(HelloJob2.class);
    @Override
    public void executeJob(JobExecutionContext jobExecutionContext) throws Exception {
        LOGGER.error("================HelloJob2");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }
}
