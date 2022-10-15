package com.zhou.jianzhi.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author shihh
 * @version 1.0
 * @date 2020/7/27 15:10
 */
@Data
public class MenuNodeVO {
    private Long id;

    private Long parentId;

    private String menuName;

    private String url=null;

    private String icon;

    private Long orderNum;

    private Integer open;

    /**
     * 与SysMenu不同,用于判断前端树形菜单里的节点是否禁用
     */
    private boolean disabled;

    private String perms;

    private Integer type;
    /**
     * 子节点
     */
    private List<MenuNodeVO> children=new ArrayList<>();
    /**
     * 排序,根据order排序
     */
    public static Comparator<MenuNodeVO> order(){
        Comparator<MenuNodeVO> comparator = (o1, o2) -> {
            if(o1.getOrderNum() != o2.getOrderNum()){
                return (int) (o1.getOrderNum() - o2.getOrderNum());
            }
            return 0;
        };
        return comparator;
    }
}
