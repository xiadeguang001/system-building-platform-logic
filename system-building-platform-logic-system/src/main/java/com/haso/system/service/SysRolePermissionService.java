package com.haso.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.util.ConvertUtil;
import com.haso.common.util.OConvertUtil;
import com.haso.system.entity.SysRolePermission;
import com.haso.system.mapper.SysRolePermissionMapper;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Service
public class SysRolePermissionService extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> {

	public void saveRolePermission(String roleId, String permissionIds) {
		LambdaQueryWrapper<SysRolePermission> query = new QueryWrapper<SysRolePermission>().lambda().eq(SysRolePermission::getRoleId, roleId);
		this.remove(query);
		List<SysRolePermission> list = new ArrayList<SysRolePermission>();
        String[] arr = permissionIds.split(",");
		for (String p : arr) {
			if(OConvertUtil.isNotEmpty(p)) {
				SysRolePermission rolepms = new SysRolePermission(ConvertUtil.getInteger(roleId), ConvertUtil.getInteger(p));
				list.add(rolepms);
			}
		}
		this.saveBatch(list);
	}

	public void saveRolePermission(String roleId, String permissionIds, String lastPermissionIds) {
		List<String> add = getDiff(lastPermissionIds,permissionIds);
		if(add!=null && add.size()>0) {
			List<SysRolePermission> list = new ArrayList<SysRolePermission>();
			for (String p : add) {
				if(OConvertUtil.isNotEmpty(p)) {
					SysRolePermission rolepms = new SysRolePermission(ConvertUtil.getInteger(roleId), ConvertUtil.getInteger(p));
					list.add(rolepms);
				}
			}
			this.saveBatch(list);
		}
		
		List<String> delete = getDiff(permissionIds,lastPermissionIds);
		if(delete!=null && delete.size()>0) {
			for (String permissionId : delete) {
				this.remove(new QueryWrapper<SysRolePermission>().lambda().eq(SysRolePermission::getRoleId, Integer.valueOf(roleId)).eq(SysRolePermission::getPermissionId, Integer.valueOf(permissionId)));
			}
		}
	}
	
	/**
	 * 从diff中找出main中没有的元素
	 * @param main
	 * @param diff
	 * @return
	 */
	private List<String> getDiff(String main,String diff){
		if(OConvertUtil.isEmpty(diff)) {
			return null;
		}
		if(OConvertUtil.isEmpty(main)) {
			return Arrays.asList(diff.split(","));
		}
		
		String[] mainArr = main.split(",");
		String[] diffArr = diff.split(",");
		Map<String, Integer> map = new HashMap<>();
		for (String string : mainArr) {
			map.put(string, 1);
		}
		List<String> res = new ArrayList<String>();
		for (String key : diffArr) {
			if(OConvertUtil.isNotEmpty(key) && !map.containsKey(key)) {
				res.add(key);
			}
		}
		return res;
	}

}
