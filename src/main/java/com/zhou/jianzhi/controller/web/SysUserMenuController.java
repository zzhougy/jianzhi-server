package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.po.BaseUser;
import com.zhou.jianzhi.entity.po.SysMenu;
import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.po.SysRoleMenu;
import com.zhou.jianzhi.service.*;
import com.zhou.jianzhi.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SysUserMenuController {
    @Autowired
    private BaseUserService baseUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 获取用户信息集合
     *
     * @return AjaxResult
     */
    @GetMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public AjaxResult getUserInfoList() {
        List<BaseUser> userList = baseUserService.list();
        return AjaxResult.success(userList);
    }

    /**
     * 获取角色信息集合
     *
     * @Return AjaxResult
     */
    @GetMapping("/getRoleInfoList")
    @RequiresPermissions("sys:role:info")
    public AjaxResult getRoleInfoList() {
        List<SysRole> sysRoleList = sysRoleService.list();
        return AjaxResult.success(sysRoleList);
    }

    /**
     * 获取权限信息集合
     *
     * @Return AjaxResult
     */
    @GetMapping("/getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public AjaxResult getMenuInfoList() {
        List<SysMenu> sysMenus = sysMenuService.list();
        return AjaxResult.success(sysMenus);
    }

    /**
     * 获取所有数据
     *
     * @Return AjaxResult
     */
    @GetMapping("/getInfoAll")
    @RequiresPermissions("sys:info:all")
    public AjaxResult getInfoAll() {
        Map<String, Object> map = new HashMap<>();
        List<BaseUser> userList = baseUserService.list();
        map.put("userList", userList);
        List<SysRole> sysRoleList = sysRoleService.list();
        map.put("roleList", sysRoleList);
        List<SysMenu> sysMenus = sysMenuService.list();
        map.put("menuList", sysMenus);
        return AjaxResult.success(map);
    }

    /**
     * 添加角色权限(测试动态权限更新)
     *
     * @Return AjaxResult
     */

    @PostMapping("/addMenu")
    public AjaxResult addMenu(@RequestBody SysRoleMenu RoleMenu) {
        //添加管理员角色权限
        boolean result = sysRoleMenuService.save(RoleMenu);
        //清除缓存
        return AjaxResult.success();
    }
}
