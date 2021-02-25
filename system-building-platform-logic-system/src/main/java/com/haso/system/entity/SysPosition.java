package com.haso.system.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.haso.common.api.entity.ComHasoEntity;
import com.haso.common.aspect.annotation.Dict;
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
public class SysPosition extends ComHasoEntity {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 职务编码
     */
    private String code;
    /**
     * 职务名称
     */
    private String name;
    /**
     * 职级
     */
    @Dict(dicCode = "post_rank")
    private String postRank;
    /**
     * 备注
     */
    private String memo;
    /**
     * 组织机构编码
     */
    private String sysOrgCode;
    /**
     * 删除状态（0，正常，1已删除）
     */
    @TableLogic
    private Integer deleteFlg;

    /**
     * 排他控制
     */
    private Integer exclcnt;
}
