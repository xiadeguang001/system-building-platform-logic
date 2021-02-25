package com.haso.common.system.vo;

import com.haso.common.util.DateUtil;

import java.util.List;

/**
 * @ClassName SysUserCacheInfo
 * @Description TODO
 * @Author haso
 * @Date 2020/02/29 15:30
 * @Version 1.0
 **/
public class SysUserCacheInfo {

    private String sysUserCode;

    private String sysUserName;

    private String sysOrgCode;

    private List<String> sysMultiOrgCode;

    private boolean oneDepart;

    public boolean isOneDepart() {
        return oneDepart;
    }

    public void setOneDepart(boolean oneDepart) {
        this.oneDepart = oneDepart;
    }

    public String getSysDate() {
        return DateUtil.formatDate();
    }

    public String getSysTime() {
        return DateUtil.now();
    }

    public String getSysUserCode() {
        return sysUserCode;
    }

    public void setSysUserCode(String sysUserCode) {
        this.sysUserCode = sysUserCode;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    public List<String> getSysMultiOrgCode() {
        return sysMultiOrgCode;
    }

    public void setSysMultiOrgCode(List<String> sysMultiOrgCode) {
        this.sysMultiOrgCode = sysMultiOrgCode;
    }

}
