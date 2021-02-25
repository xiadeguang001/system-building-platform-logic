package com.haso.system.shiro.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysDepartUsersVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**部门id*/
	private Integer depId;
	/**对应的用户id集合*/
	private List<Integer> userIdList;
	public SysDepartUsersVO(Integer depId, List<Integer> userIdList) {
		super();
		this.depId = depId;
		this.userIdList = userIdList;
	}
    //update-begin--Author:kangxiaolin  Date:20190908 for：[512][部门管理]点击添加已有用户失败修复--------------------

	public SysDepartUsersVO(){

	}
    //update-begin--Author:kangxiaolin  Date:20190908 for：[512][部门管理]点击添加已有用户失败修复--------------------

}
