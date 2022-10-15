package com.zhou.jianzhi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.entity.po.SysRoleMenu;
import com.zhou.jianzhi.mapper.SysRoleMenuMapper;
import com.zhou.jianzhi.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 先添加到关系表
     * 删除所有此角色下用户的缓存重新授权
     *
     * @param sysRoleMenu
     * @return
     */
    @Override
    public Boolean addRoleMenu(SysRoleMenu sysRoleMenu) {
        return null;
    }
}
