package com.haso.common.system.vo;

public class QuartzErrorEntity {
    private Integer No;
    //private String showNo;//シーケンス
    private String jobName;//ジョブ名称
    private String jobGroup;//ジョブグループ
    private String errorTime;//发生时间
    private Integer statusFlg;//修正状态
    //private String statusFlgShow;//表示状态
    private String errorMsg;//错误信息

    public QuartzErrorEntity() {
        super();
    }

    public QuartzErrorEntity(String jobName, String jobGroup, String errorTime, Integer statusFlg, String errorMsg) {
        super();
        //this.showNo = showNo;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.errorTime = errorTime;
        this.statusFlg = statusFlg;
       // this.statusFlgShow = statusFlgShow;
        this.errorMsg = errorMsg;
    }

    public Integer getNo() {
        return No;
    }

    public void setNo(Integer No) {
        this.No = No;
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

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }

    public Integer getStatusFlg() {
        return statusFlg;
    }

    public void setStatusFlg(Integer statusFlg) {
        this.statusFlg = statusFlg;
    }

//    public String getStatusFlgShow() {
//        return statusFlgShow;
//    }
//
//    public void setStatusFlgShow(String statusFlgShow) {
//        this.statusFlgShow = statusFlgShow;
//    }

//    public String getShowNo() {
//        return showNo;
//    }
//
//    public void setShowNo(String showNo) {
//        this.showNo = showNo;
//    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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
}
