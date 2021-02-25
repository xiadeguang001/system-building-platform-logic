package com.haso.common.quartz.job;


import com.haso.common.constant.CommonConstant;
import com.haso.common.quartz.service.AbstractJobService;
import com.haso.common.system.vo.QuartzEntity;
import com.haso.common.system.vo.QuartzErrorEntity;
import com.haso.common.util.DateUtil;
import com.haso.common.util.QuartzCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * JOB抽象类
 * @author Gong Lingxiao
 */
@Slf4j
public abstract class AbstractJob implements InterruptableJob, Serializable {

    public abstract void executeJob(JobExecutionContext jobExecutionContext)  throws  Exception;

    @Autowired
    private QuartzCommonUtil utils;

    @Autowired
    private AbstractJobService service;

    /**
     * batch job执行共通入口
     * @param context
     */
    @Override
    @Transactional(rollbackFor = {JobExecutionException.class, Exception.class})
    public void execute(JobExecutionContext context) throws  JobExecutionException{
        JobKey jobKey = context.getJobDetail().getKey();
        String jobName = "";
        String jobGroup = "";
        String schedulerName = "";
        Integer execSort = null;
        // 创建实例，添加到执行JOB表中
        QuartzEntity entity = new QuartzEntity();
        try {
            // 获取JOB参数，获取执行顺序值
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            for (JobDataMap.Entry entry : jobDataMap.entrySet()) {
                String key = (String) entry.getKey();
                String param = (String) entry.getValue();
                if ("execSort".equals(key)) {
                    try {
                        execSort = Integer.parseInt(param);
                    } catch (Exception e) {
                        log.warn("============= " + jobGroup + ":" + jobName + " execSort参数不是整型数字");
                    }
                    break;
                }
            }

            // 新增记录到JOB执行顺序表
            Scheduler scheduler = context.getScheduler();
            schedulerName = scheduler.getSchedulerName();
            jobName = jobKey.getName();
            jobGroup = jobKey.getGroup();
            entity.setJobGroup(jobGroup);
            entity.setJobName(jobName);
            entity.setSchedName(schedulerName);
            entity.setCreateTime(System.currentTimeMillis());
            entity.setExecSort(execSort);
            service.addRunningJob(entity);

            log.info("============= " + jobGroup + ":" + jobName + " 処理を実行します =============");
            executeJob(context);

            // 更新前回触发时间
            utils.updatePreFireTime(context);
            log.info("============= " + jobGroup + ":" + jobName + " 処理正常終了しました =============");
        } catch (Exception e) {
            // 新增数据到job错误信息表
            QuartzErrorEntity quartzErrorEntity = new QuartzErrorEntity();
            quartzErrorEntity.setJobGroup(jobGroup);
            quartzErrorEntity.setJobName(jobName);
            quartzErrorEntity.setErrorTime(DateUtil.now());
            quartzErrorEntity.setStatusFlg(CommonConstant.SOLVE_STATUS_FLG_0);
            quartzErrorEntity.setErrorMsg(e.getLocalizedMessage());
            service.addErrorMessage(quartzErrorEntity);
            log.error("" + jobGroup + ":" + jobName + " 処理異常終了しました ERROR: \n" + e.getLocalizedMessage());
            throw  new JobExecutionException(e);
        } finally {
            // 删除JOB执行顺序表中的JOB
            service.deleteById(entity.getId());
        }
    }
}
