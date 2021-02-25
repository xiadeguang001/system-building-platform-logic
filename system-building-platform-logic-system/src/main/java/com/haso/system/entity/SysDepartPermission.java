package com.haso.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 部门权限表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Data
@TableName("sys_depart_permission")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_depart_permission对象", description="部门权限表")
public class SysDepartPermission {
    
	/**id*/
    @ApiModelProperty(value = "id")
	private Integer id;
	/**部门id*/
    @ApiModelProperty(value = "部门id")
	private Integer departId;
	/**权限id*/
    @ApiModelProperty(value = "权限id")
	private Integer permissionId;
	/**数据规则id*/
	@ApiModelProperty(value = "数据规则id")
	private String dataRuleIds;

	public SysDepartPermission() {

	}

	public SysDepartPermission(Integer departId, Integer permissionId) {
		this.departId = departId;
		this.permissionId = permissionId;
	}
}
