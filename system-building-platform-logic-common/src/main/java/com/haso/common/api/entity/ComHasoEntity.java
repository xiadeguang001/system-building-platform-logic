package com.haso.common.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class ComHasoEntity implements Serializable {

    /**
     * 创建者ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建者ID")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    /**
     * 创建者名
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者名")
    private String createUserName;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    /**
     * 更新者ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "更新者ID")
    private Integer updateUserId;

    /**
     * 更新者名
     */
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新者名")
    private String updateUserName;

    /**
     * 删除标识
     */
    @TableLogic
    @ApiModelProperty(value = "删除标识")
    @TableField(value = "delete_flg")
    private Integer deleteFlg;

    /**排他控制*/
	@ApiModelProperty(value = "排他控制")
	@Version
    private Integer exclcnt;
}
