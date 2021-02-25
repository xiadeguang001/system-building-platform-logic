package com.haso.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user_depart")
public class SysUserDepart implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**主键id*/
	private Integer id;
	/**用户id*/
	private Integer userId;
	/**部门id*/
	private Integer depId;
	public SysUserDepart(Integer id, Integer userId, Integer depId) {
		super();
		this.id = id;
		this.userId = userId;
		this.depId = depId;
	}

	public SysUserDepart(Integer id, Integer departId) {
		this.userId = id;
		this.depId = departId;
	}
}
