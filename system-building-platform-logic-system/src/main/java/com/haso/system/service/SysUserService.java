package com.haso.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.api.vo.Result;
import com.haso.common.constant.CommonConstant;
import com.haso.common.system.vo.LoginUser;
import com.haso.common.system.vo.SysUserCacheInfo;
import com.haso.common.util.ConvertUtil;
import com.haso.common.util.OConvertUtil;
import com.haso.common.util.PasswordUtil;
import com.haso.system.entity.*;
import com.haso.system.mapper.*;
import com.haso.system.model.SysUserSysDepartModel;
import com.haso.system.shiro.vo.SysUserDepVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author: scott
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

	@Resource
	private SysUserMapper userMapper;
	@Resource
	private SysPermissionMapper sysPermissionMapper;
	@Resource
	private SysUserRoleMapper sysUserRoleMapper;
	@Resource
	private SysUserDepartMapper sysUserDepartMapper;
	@Resource
	private SysBaseApi sysBaseAPI;
	@Resource
	private SysDepartMapper sysDepartMapper;

	/**
	 * 用户一览查询
	 * @param page
	 * @param user
	 * @param column
	 * @param order
	 * @return 用户集合
	 */
	public IPage<SysUser> selPage(IPage<SysUser> page, SysUser user, String column, String order) {
		return userMapper.selUserList(page, user, column, order);
	}

//	//@CacheEvict(value = {CacheConstant.SYS_USERS_CACHE}, allEntries = true)
	public Result<?> resetPassword(String username, String oldpassword, String newpassword, String confirmpassword) {
		SysUser user = userMapper.getUserByName(username);
		String passwordEncode = PasswordUtil.encrypt(username, oldpassword, user.getSalt());
		if (!user.getPassword().equals(passwordEncode)) {
			return Result.error("旧密码输入错误!");
		}
		if (OConvertUtil.isEmpty(newpassword)) {
			return Result.error("新密码不允许为空!");
		}
		if (!newpassword.equals(confirmpassword)) {
			return Result.error("两次输入密码不一致!");
		}
		String password = PasswordUtil.encrypt(username, newpassword, user.getSalt());
		this.userMapper.update(new SysUser().setPassword(password), new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, user.getId()));
		return Result.ok("密码重置成功!");
	}

	//@CacheEvict(value = {CacheConstant.SYS_USERS_CACHE}, allEntries = true)
	public Result<?> changePassword(SysUser sysUser) {
		String salt = OConvertUtil.randomGen(8);
		sysUser.setSalt(salt);
		String password = sysUser.getPassword();
		String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), password, salt);
		sysUser.setPassword(passwordEncode);
		this.userMapper.updateById(sysUser);
		return Result.ok("密码修改成功!");
	}

	//@CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteUser(String userId) {
		//1.删除用户
		this.removeById(ConvertUtil.getInteger(userId));
		return false;
	}

	//@CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteBatchUsers(String userIds) {
		String[] id = userIds.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < id.length; i++) {
			idList.add(ConvertUtil.getInteger(id[i]));
		}
		//1.删除用户
		this.removeByIds(idList);
		return false;
	}

	public SysUser getUserByName(String username) {
		return userMapper.getUserByName(username);
	}


	@Transactional
	public void addUserWithRole(SysUser user, String roles) {
		this.save(user);
		if(OConvertUtil.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), ConvertUtil.getInteger(roleId));
				sysUserRoleMapper.insert(userRole);
			}
		}
	}

	//@CacheEvict(value= {CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional
	public void editUserWithRole(SysUser user, String roles) {
		this.updateById(user);
		//先删后加
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, user.getId()));
		if(OConvertUtil.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), ConvertUtil.getInteger(roleId));
				sysUserRoleMapper.insert(userRole);
			}
		}
	}


	public List<String> getRole(String username) {
		return sysUserRoleMapper.getRoleByUserName(username);
	}

	/**
	 * 通过用户名获取用户角色集合
	 * @param username 用户名
	 * @return 角色集合
	 */
	public Set<String> getUserRolesSet(String username) {
		// 查询用户拥有的角色集合
		List<String> roles = sysUserRoleMapper.getRoleByUserName(username);
		log.info("-------通过数据库读取用户拥有的角色Rules------username： " + username + ",Roles size: " + (roles == null ? 0 : roles.size()));
		return new HashSet<>(roles);
	}

	/**
	 * 通过用户名获取用户权限集合
	 *
	 * @param username 用户名
	 * @return 权限集合
	 */
	public Set<String> getUserPermissionsSet(String username) {
		Set<String> permissionSet = new HashSet<>();
		List<SysPermission> permissionList = sysPermissionMapper.queryByUser(username);
		for (SysPermission po : permissionList) {
//			// TODO URL规则有问题？
			if (OConvertUtil.isNotEmpty(po.getUrl())) {
				permissionSet.add(po.getUrl());
			}
			if (OConvertUtil.isNotEmpty(po.getPerms())) {
				permissionSet.add(po.getPerms());
			}
		}
		log.info("-------通过数据库读取用户拥有的权限Perms------username： "+ username+",Perms size: "+ (permissionSet==null?0:permissionSet.size()) );
		return permissionSet;
	}

	public SysUserCacheInfo getCacheUser(String username) {
		SysUserCacheInfo info = new SysUserCacheInfo();
		info.setOneDepart(true);
//		SysUser user = userMapper.getUserByName(username);
//		info.setSysUserCode(user.getUsername());
//		info.setSysUserName(user.getRealname());


		LoginUser user = sysBaseAPI.getUserByName(username);
		if(user!=null) {
			info.setSysUserCode(user.getUsername());
			info.setSysUserName(user.getRealname());
			info.setSysOrgCode(user.getOrgCode());
		}

		//多部门支持in查询
		List<SysDepart> list = sysDepartMapper.queryUserDeparts(user.getId());
		List<String> sysMultiOrgCode = new ArrayList<String>();
		if(list==null || list.size()==0) {
			//当前用户无部门
			//sysMultiOrgCode.add("0");
		}else if(list.size()==1) {
			sysMultiOrgCode.add(list.get(0).getOrgCode());
		}else {
			info.setOneDepart(false);
			for (SysDepart dpt : list) {
				sysMultiOrgCode.add(dpt.getOrgCode());
			}
		}
		info.setSysMultiOrgCode(sysMultiOrgCode);

		return info;
	}

	// 根据部门Id查询
	public IPage<SysUser> getUserByDepId(Page<SysUser> page, String departId,String username) {
		return userMapper.getUserByDepId(page, departId,username);
	}

	public IPage<SysUser> getUserByDepIds(Page<SysUser> page, List<String> departIds, String username) {
		return userMapper.getUserByDepIds(page, departIds,username);
	}

	public Map<Integer, String> getDepNamesByUserIds(List<Integer> userIds) {
		List<SysUserDepVo> list = this.baseMapper.getDepNamesByUserIds(userIds.stream().collect(Collectors.toList()));

		Map<Integer, String> res = new HashMap<Integer, String>();
		list.forEach(item -> {
					if (res.get(item.getUserId()) == null) {
						res.put(item.getUserId(), item.getDepartName());
					} else {
						res.put((item.getUserId()), res.get(item.getUserId()) + "," + item.getDepartName());
					}
				}
		);
		return res;
	}

	public IPage<SysUser> getUserByDepartIdAndQueryWrapper(Page<SysUser> page, String departId, QueryWrapper<SysUser> queryWrapper) {
		LambdaQueryWrapper<SysUser> lambdaQueryWrapper = queryWrapper.lambda();

		lambdaQueryWrapper.eq(SysUser::getDelFlag, "0");
		lambdaQueryWrapper.inSql(SysUser::getId, "SELECT user_id FROM sys_user_depart WHERE dep_id = '" + departId + "'");

		return userMapper.selectPage(page, lambdaQueryWrapper);
	}

	public IPage<SysUserSysDepartModel> queryUserByOrgCode(String orgCode, SysUser userParams, IPage page) {
		List<SysUserSysDepartModel> list = baseMapper.getUserByOrgCode(page, orgCode, userParams);
		Integer total = baseMapper.getUserByOrgCodeTotal(orgCode, userParams);

		IPage<SysUserSysDepartModel> result = new Page<>(page.getCurrent(), page.getSize(), total);
		result.setRecords(list);

		return result;
	}

	// 根据角色Id查询
	public IPage<SysUser> getUserByRoleId(Page<SysUser> page, String roleId, String username) {
		return userMapper.getUserByRoleId(page,roleId,username);
	}


	//@CacheEvict(value= {CacheConstant.SYS_USERS_CACHE}, key="#username")
	public void updateUserDepart(String username,String orgCode) {
		baseMapper.updateUserDepart(username, orgCode);
	}


	public SysUser getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}


	public SysUser getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	@Transactional
	public void addUserWithDepart(SysUser user, String selectedParts) {
//		this.save(user);  //保存角色的时候已经添加过一次了
		if(OConvertUtil.isNotEmpty(selectedParts)) {
			String[] arr = selectedParts.split(",");
			for (String deaprtId : arr) {
				SysUserDepart userDeaprt = new SysUserDepart(user.getId(), ConvertUtil.getInteger(deaprtId));
				sysUserDepartMapper.insert(userDeaprt);
			}
		}
	}


	@Transactional
	//@CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	public void editUserWithDepart(SysUser user, String departs) {
		this.updateById(user);  //更新角色的时候已经更新了一次了，可以再跟新一次
		//先删后加
		sysUserDepartMapper.delete(new QueryWrapper<SysUserDepart>().lambda().eq(SysUserDepart::getUserId, user.getId()));
		if(OConvertUtil.isNotEmpty(departs)) {
			String[] arr = departs.split(",");
			for (String departId : arr) {
				SysUserDepart userDepart = new SysUserDepart(user.getId(), ConvertUtil.getInteger(departId));
				sysUserDepartMapper.insert(userDepart);
			}
		}
	}


	/**
	 * 校验用户是否有效
	 * @param sysUser
	 * @return
	 */
	public Result<?> checkUserIsEffective(SysUser sysUser) {
		Result<?> result = new Result<Object>();
		//情况1：根据用户信息查询，该用户不存在
		if (sysUser == null) {
			result.error500("该用户不存在，请注册");
			sysBaseAPI.addLog("用户登录失败，用户不存在！", CommonConstant.LOG_TYPE_1, null);
			return result;
		}
		//情况2：根据用户信息查询，该用户已注销
		if (CommonConstant.DEL_FLAG_1.toString().equals(sysUser.getDelFlag())) {
			sysBaseAPI.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已注销！", CommonConstant.LOG_TYPE_1, null);
			result.error500("该用户已注销");
			return result;
		}
		//情况3：根据用户信息查询，该用户已冻结
		if (CommonConstant.USER_FREEZE.equals(sysUser.getStatus())) {
			sysBaseAPI.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已冻结！", CommonConstant.LOG_TYPE_1, null);
			result.error500("该用户已冻结");
			return result;
		}
		return result;
	}

	public List<SysUser> queryLogicDeleted() {
		return this.queryLogicDeleted(null);
	}

	public List<SysUser> queryLogicDeleted(LambdaQueryWrapper<SysUser> wrapper) {
		if (wrapper == null) {
			wrapper = new LambdaQueryWrapper<>();
		}
		wrapper.eq(SysUser::getDelFlag, 1);
		return userMapper.selectLogicDeleted(wrapper);
	}

	public boolean revertLogicDeleted(List<String> userIds, SysUser updateEntity) {
		String ids = String.format("'%s'", String.join("','", userIds));
		return userMapper.revertLogicDeleted(ids, updateEntity) > 0;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean removeLogicDeleted(List<String> userIds) {
		String ids = String.format("'%s'", String.join("','", userIds));
		// 1. 删除用户
		int line = userMapper.deleteLogicDeleted(ids);
		// 2. 删除用户部门关系
		line += sysUserDepartMapper.delete(new LambdaQueryWrapper<SysUserDepart>().in(SysUserDepart::getUserId, userIds));
		//3. 删除用户角色关系
		line += sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, userIds));
		return line != 0;
	}

}
