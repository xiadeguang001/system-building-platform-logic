package com.haso.system.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: JOB一览
 * @Author: jeecg-boot
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Data
@TableName("t_job_log")
public class JobLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**id*/
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**job_id*/
    private String jobId;
    /**exclcnt*/
    private Integer exclcnt;
    /**content*/
    private String content;
    /**log_time*/
    private String logTime;
    /**削除フラグ*/
    private Char sakujoflg;


}
