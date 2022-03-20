package com.zhou.jianzhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.util.MenuTreeBuilder;
import com.zhou.jianzhi.concerter.MenuConverter;
import com.zhou.jianzhi.entity.dto.SysMenuDTO;
import com.zhou.jianzhi.entity.po.SysMenu;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;
import com.zhou.jianzhi.exception.MyException;
import com.zhou.jianzhi.mapper.SysMenuMapper;
import com.zhou.jianzhi.service.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class SysMenusServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> selectSysMenuByRoleId(Long roleId) {
        return null;
    }

    /**
     * 加载菜单数据
     *
     * @return
     */
    @Override
    public List<MenuNodeVO> findMenuTree() {
        //查询数据库原始数据
        List<SysMenu> menus = sysMenuMapper.selectList(new QueryWrapper<>());
        //转化成menuVO
        List<MenuNodeVO> menuNodeVOS = MenuConverter.converterToALLMenuNodeVO(menus);
        //构建多级菜单数并返回
        return MenuTreeBuilder.build(menuNodeVOS);
    }

    /**
     * 找到加载时需要打开的节点id
     *
     * @return
     */
    @Override
    public List<Long> findOpenIds() {
        List<Long> ids = new ArrayList<>();
        List<SysMenu> menus = sysMenuMapper.selectList(new QueryWrapper<>());
        if (!CollectionUtils.isEmpty(menus)) {
            for (SysMenu menu : menus) {
                if (menu.getOpen() == 1) {
                    ids.add(menu.getId());
                }
            }
        }
        return ids;
    }

    /**
     * 添加菜单/按钮
     *
     * @param menuDto
     * @return
     */
    @Override
    public SysMenu add(SysMenuDTO menuDto) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuDto, menu);
        menu.setAvailable(menuDto.isDisabled() ? 0 : 1);
        sysMenuMapper.insert(menu);
        //返回添加成功的记录
        return menu;
    }

    @Override
    public void delete(Long id) {
        SysMenu menu = sysMenuMapper.selectById(id);
        if (StringUtils.isEmpty(menu)) {
            throw new MyException("要删除的菜单不存在");
        }
        sysMenuMapper.deleteById(id);
    }

    /**
     * 返回需要编辑的菜单/按钮数据详情
     *
     * @param id
     * @return
     */
    @Override
    public SysMenuDTO edit(Long id) {
        SysMenu menu = sysMenuMapper.selectById(id);
        if (StringUtils.isEmpty(menu)) {
            throw new MyException("该编辑的菜单不存在");
        }
        return MenuConverter.converterToMenuVO(menu);
    }

    @Override
    public void update(Long id, SysMenuDTO menuDto) {
        SysMenu dbMenu = sysMenuMapper.selectById(id);
        if (StringUtils.isEmpty(dbMenu)) {
            throw new MyException("要更新的菜单不存在");
        }
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuDto, menu);
        menu.setId(id);
        menu.setAvailable(menuDto.isDisabled() ? 0 : 1);
        sysMenuMapper.updateById(menu);
    }
}
