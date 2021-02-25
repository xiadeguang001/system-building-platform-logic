package com.haso.common.util;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;
import java.util.List;

/**
 * quartz 共通处理
 * @author Gong Lingxiao
 */
@Component
public class QuartzCommonUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(QuartzCommonUtil.class);

    /**
     * 根据groupName停止执行该组所有Job
     *
     * @param context
     */
    public void interruptJobByGroup(JobExecutionContext context) throws Exception {
        JobDetail job = context.getJobDetail();
        String groupName = job.getKey().getGroup();
        Scheduler scheduler = context.getScheduler();
        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

            String jobName = jobKey.getName();
            String jobGroup = jobKey.getGroup();

            //get job's trigger
            List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
            Date nextFireTime = triggers.get(0).getNextFireTime();

            System.out.println("[jobName] : " + jobName + " [groupName] : "
                    + jobGroup + " - " + nextFireTime);

            TriggerKey triggerKey = triggers.get(0).getKey();
            // トリガーを停止します
            scheduler.pauseTrigger(triggerKey);
            // トリガーを排除します
            scheduler.unscheduleJob(triggerKey);
            // ジョブを削除します
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));

        }
    }

    /**
     * 手动触发时更新前回触发时间
     * @param context
     * @throws SchedulerException
     */
    public void updatePreFireTime(JobExecutionContext context) throws Exception {
        JobDetail job = context.getJobDetail();
        Scheduler scheduler = context.getScheduler();
        String schedulerName = scheduler.getSchedulerName();
        String groupName = job.getKey().getGroup();
        String jobName = "trigger" + job.getKey().getName();
        //jobDetailService.updatePrevFireTimeByCondition(schedulerName, jobName, groupName, "" + System.currentTimeMillis());
    }

    /**
     * 读取BAT文件
     * @param batPath
     */
    public void loadBat(String batPath) throws Exception {
        String[] cmd = new String[3];
        cmd[0] = "cmd.exe" ;
        cmd[1] = "/C" ;
        cmd[2] = batPath;
        Runtime rt = Runtime.getRuntime();
        System.out.println("Execing " + cmd[0] + " " + cmd[1]  + " " + cmd[2]);
        Process proc = rt.exec(cmd);
//            // any error message?
//            CommandStreamReader errorStream = new CommandStreamReader(proc.getErrorStream(), "ERROR");
//
//            // any output? Here We take return or output value from bach file
//            CommandStreamReader outputStream = new CommandStreamReader(proc.getInputStream(), "OUTPUT");
//
//            // kick them off
//            errorStream.start();
//            outputStream.start();

        InputStreamReader r = new InputStreamReader(proc.getInputStream());
        LineNumberReader returnData = new LineNumberReader(r);


        String returnMsg = "";
        String line = "";
        while ((line = returnData.readLine()) != null) {
            System.out.println(returnData.getLineNumber()+" "+line);
            returnMsg += line;
        }
        System.out.println(returnMsg);
        // any error???
        int exitVal = proc.waitFor();
        System.out.println("ExitValue: " + exitVal);
    }
}
