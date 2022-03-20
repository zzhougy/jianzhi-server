package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.entity.dto.SysRoleDTO;
import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.vo.SysRoleVO;

import java.util.List;

/**
 * @author shihh
 * @version 1.0
 * @date 2020/7/23 10:57
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 通过用户ID查询角色集合
     * @Param  userId 用户ID
     * @Return List<SysRole> 角色名集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);

    /**
     * 给角色授权
     * 根据RoleId 和 MenuId 数组 往关联表添加数据
     * @param id
     * @param mids
     */
    void authority(Long id,Long[] mids);

    /**
     * 分页获取角色列表
     * @param sysRoleDto
     * @return
     */
    IPage<SysRoleVO> findRoleList(SysRoleDTO sysRoleDto);

    /**
     * 添加角色
     * @param roleDto
     */
    void add(SysRoleDTO roleDto);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteById(Long roleId);

    /**
     * 编辑角色 (根据ID查询角色信息)
     * @param id
     * @return
     */
    SysRole edit(Long id);

    /**
     * 更新角色
     * @param roleDto
     */
    boolean update(SysRoleDTO roleDto);

    /**
     * 查询所有的角色
     * @return
     */
    List<SysRole> findAll();

    /**
     * 查询角色拥有的菜单权限id
     * @param id
     * @return
     */
    List<Long> findMenuIdsByRoleId(Long id);
    /**
     * 更新角色
     * @param id
     * @param roleDto
     */
    void update(Long id, SysRoleDTO roleDto);

    /**
     * 根据角色状态
     * @param id
     * @param status
     */
    void updateStatus(Long id, Boolean status);

    /*
     * @author chenchen
     * @data 2020/8/14
     * @return 
     * @description 根据部门角色查询用户id
     */
    List<String> selectUserId(String deptid, String role);

    /**
     * 根据角色查询用户ID
     * @param role
     * @return
     */
    List<String> selectUserId(List role);


}
