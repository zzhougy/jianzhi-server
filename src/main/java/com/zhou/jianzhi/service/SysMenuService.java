package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.entity.dto.SysMenuDTO;
import com.zhou.jianzhi.entity.po.SysMenu;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;

import java.util.List;

/**
 * @author shihh
 * @version 1.0
 * @date 2020/7/23 10:57
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据角色查询用户权限
     * @Param  roleId 角色ID
     * @Return List<SysMenu> 权限集合
     */
    List<SysMenu> selectSysMenuByRoleId(Long roleId);

    /**
     * 获取菜单树
     * @return
     */
    List<MenuNodeVO> findMenuTree();
    /**
     * 所有展开菜单的ID
     * @return
     */
    List<Long> findOpenIds();
    /**
     * 添加菜单
     * @param menuDto
     */
    SysMenu add(SysMenuDTO menuDto);
    /**
     * 删除节点
     * @param id
     */
    void delete(Long id);
    /**
     * 编辑节点
     * @param id
     * @return
     */
    SysMenuDTO edit(Long id);
    /**
     * 更新节点
     * @param id
     */
    void update(Long id, SysMenuDTO menuDto);
}
