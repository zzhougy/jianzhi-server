package com.zhou.jianzhi.common.util;

import com.zhou.jianzhi.entity.vo.MenuNodeVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 该类用于递归构建树形菜单
 *
 */
public class MenuTreeBuilder {
    /**
     * 构建多级菜单树
     *
     * @param nodes 从sys_menu查找的数据转换成的VO
     * @return
     */
    public static List<MenuNodeVO> build(List<MenuNodeVO> nodes) {
        //筛选出根节点(父节点为0),并设置其子节点
        List<MenuNodeVO> rootMenu = nodes.stream().filter(menuNodeVo -> menuNodeVo.getParentId() == 0).map((menuNodeVo) -> {
            menuNodeVo.setChildren(getChild(menuNodeVo.getId(),nodes));
            return menuNodeVo;
        }).collect(Collectors.toList());
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu, MenuNodeVO.order());
        return rootMenu;
    }

    /**
     * 递归设置子节点
     *
     * @param id 集合中每一个菜单/按钮的id
     * @param nodes 菜单/按钮VO集合
     * @return
     */
    private static List<MenuNodeVO> getChild(Long id, List<MenuNodeVO> nodes) {
        //从找到的菜单集合中找到其父节点id是传入的id的子节点,子节点递归设置其子节点
        List<MenuNodeVO> childList = nodes.stream().filter(menuNodeVo -> menuNodeVo.getParentId().equals(id)).map((menuNodeVo) -> {
            menuNodeVo.setChildren(getChild(menuNodeVo.getId(), nodes));
            return menuNodeVo;
        }).collect(Collectors.toList());
        //排序
        Collections.sort(childList, MenuNodeVO.order());
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<MenuNodeVO>();
        }
        return childList;
    }

}
