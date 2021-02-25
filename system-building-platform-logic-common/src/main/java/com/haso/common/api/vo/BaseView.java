package com.haso.common.api.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BaseView {
	/**
     * 创建者ID
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者ID
     */
    private Integer updateUserId;

    /**
     * 删除标识
     */
    private Integer deleteFlg;

    /**排他控制*/
    private Integer exclcnt;
    
    /**
     * 排序字段
     */
    private String sortColumn;
    
    /**
     * 排序字段正倒序
     */
    private String sortColumnOrder;
}
