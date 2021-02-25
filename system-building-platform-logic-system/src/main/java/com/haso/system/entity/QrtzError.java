package com.haso.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haso.common.aspect.annotation.Dict;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@TableName("qrtz_error")
public class QrtzError implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "no", type = IdType.AUTO)
    private Integer no;

    private String jobgroup;

    private String jobname;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String errortime;

    @Dict(dicCode = "status_flg")
    private Integer statusFlg;

    private String errormsg;

    private String biko;
}
