package com.haso.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.system.entity.SysRole;
import com.haso.system.mapper.SysRoleMapper;
import com.haso.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {
    @Resource
    SysRoleMapper sysRoleMapper;
    @Resource
    SysUserMapper sysUserMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(Integer roleid) {
        //1.删除角色和用户关系
        sysRoleMapper.deleteRoleUserRelation(roleid);
        //2.删除角色和权限关系
        sysRoleMapper.deleteRolePermissionRelation(roleid);
        //3.删除角色
        this.removeById(roleid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchRole(Integer[] roleIds) {
        //1.删除角色和用户关系
        sysUserMapper.deleteBathRoleUserRelation(roleIds);
        //2.删除角色和权限关系
        sysUserMapper.deleteBathRolePermissionRelation(roleIds);
        //3.删除角色
        this.removeByIds(Arrays.asList(roleIds));
        return true;
    }
}
