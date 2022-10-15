package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.entity.po.SysRoleMenu;

/**
 * @author shihh
 * @version 1.0
 * @date 2020/7/23 10:57
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    Boolean addRoleMenu(SysRoleMenu sysRoleMenu);
}
