package com.haso.system.job;

import com.haso.common.annotation.JobAnnotation;
import com.haso.common.quartz.job.AbstractJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@DisallowConcurrentExecution
@Component
@JobAnnotation(showName = "测试定时任务HelloJob")
public class HelloJob extends AbstractJob {
    private final static Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);
    @Override
    public void executeJob(JobExecutionContext jobExecutionContext) throws Exception {
        JobDetail jd = jobExecutionContext.getJobDetail();
        String jobId= jobExecutionContext.getMergedJobDataMap().get("jobId").toString();
        LOGGER.error("================接收到的job id 参数为:"+jobId);
        LOGGER.error("================HelloJob");
        Thread.sleep(120000);
        LOGGER.error("================job id 为:"+jobId+"执行完毕");

    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }
}
