package com.haso.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.haso.common.api.entity.ComHasoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 职位表对象
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914         2020-05-13    hejunnan         初期作成
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JobLogView extends ComHasoEntity {

    private static final long serialVersionUID = 1L;
    /**id*/
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**jobId*/
    private Integer jobId;

    /**ログ時間*/
    private String logTime;
    /**ログ内容*/
    private String content;
}
