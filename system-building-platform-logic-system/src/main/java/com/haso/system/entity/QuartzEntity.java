package com.haso.system.entity;

import java.util.List;

public class QuartzEntity {
    private Integer id;
    private String schedName;//計画名
    private String jobName;//ジョブ名称
    private String jobGroup;//ジョブグループ
    private String description;//内容
    private String jobClassName;//ジョブクラス名称
    private String cronExpression;//計画時間
    private String triggerName;//トリガー実行時間
    private String triggerState;//トリガー状態
    private String prevFireTime;//前回実行時間
    private String nextFireTime;//次回実行時間
    private String priority;//実行回数
    private Long createTime;//创建时间
    private String oldJobName;//修正前ジョブ名称
    private String oldJobGroup;//修正前ジョブグループ
    private List<JobArgs> jobArgs;
    private Integer jobSort;//表示顺
    private Integer execSort;//执行顺

    public QuartzEntity() {
        super();
    }

    public QuartzEntity(String schedName, String jobName, String jobGroup, String description, String jobClassName, String cronExpression, String triggerName) {
        super();
        this.schedName = schedName;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.description = description;
        this.jobClassName = jobClassName;
        this.cronExpression = cronExpression;
        this.triggerName = triggerName;
    }

    public Integer getExecSort() {
        return execSort;
    }

    public void setExecSort(Integer execSort) {
        this.execSort = execSort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(String prevFireTime) {
        this.prevFireTime = prevFireTime;
    }

    public String getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(String nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getOldJobName() {
        return oldJobName;
    }

    public void setOldJobName(String oldJobName) {
        this.oldJobName = oldJobName;
    }

    public String getOldJobGroup() {
        return oldJobGroup;
    }

    public void setOldJobGroup(String oldJobGroup) {
        this.oldJobGroup = oldJobGroup;
    }

    public List<JobArgs> getJobArgs() {
        return jobArgs;
    }

    public void setJobArgs(List<JobArgs> jobArgs) {
        this.jobArgs = jobArgs;
    }

    public static class JobArgs {
        private String name;
        private String value;

        public JobArgs() {
        }

        public JobArgs(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public Integer getJobSort() {
        return jobSort;
    }

    public void setJobSort(Integer jobSort) {
        this.jobSort = jobSort;
    }
}
