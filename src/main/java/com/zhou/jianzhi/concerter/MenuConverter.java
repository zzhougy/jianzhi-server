package com.zhou.jianzhi.concerter;

import com.zhou.jianzhi.entity.dto.SysMenuDTO;
import com.zhou.jianzhi.entity.po.SysMenu;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


public class MenuConverter {
    /**
     * 转成menuVO(只包含菜单)List
     *
     * @param menus
     * @return
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static List<MenuNodeVO> converterToMenuNodeVO(List<SysMenu> menus) {
        //先过滤出用户的菜单
        List<MenuNodeVO> menuNodeVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menus)) {
            for (SysMenu menu : menus) {
                if (menu.getType() == 0) {
                    MenuNodeVO menuNodeVO = new MenuNodeVO();
                    BeanUtils.copyProperties(menu, menuNodeVO);
                    menuNodeVO.setOrderNum(Long.valueOf(menu.getOrderNum()));
                    menuNodeVO.setDisabled(menu.getAvailable() == 0);
                    menuNodeVOS.add(menuNodeVO);
                }
            }
        }
        return menuNodeVOS;
    }

    /**
     * SysMenu转成menuVO(菜单和按钮）
     *
     * @param menus
     * @return
     */
    public static List<MenuNodeVO> converterToALLMenuNodeVO(List<SysMenu> menus) {
        //接收转换成VO后的菜单
        List<MenuNodeVO> menuNodeVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menus)) {
            for (SysMenu menu : menus) {
                MenuNodeVO menuNodeVO = new MenuNodeVO();
                BeanUtils.copyProperties(menu, menuNodeVO);
                //若该菜单/按钮不可用,则该按钮/按钮设置为勾选禁用
                menuNodeVO.setDisabled(menu.getAvailable() == 0);
                menuNodeVOS.add(menuNodeVO);
            }
        }
        return menuNodeVOS;
    }

    /**
     * 转成menuVO(菜单和按钮）
     *
     * @param menu
     * @return
     */
    public static SysMenuDTO converterToMenuVO(SysMenu menu) {
        SysMenuDTO menuDto = new SysMenuDTO();
        if (menu != null) {
            BeanUtils.copyProperties(menu, menuDto);
            menuDto.setDisabled(menu.getAvailable() == 0);
        }
        return menuDto;
    }
}
