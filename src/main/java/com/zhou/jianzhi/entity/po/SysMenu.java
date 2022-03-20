package com.zhou.jianzhi.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sys_menu")
public class SysMenu {

    /**
     * 权限ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父级功能ID
     */
    private Long parentId;
    /**
     * 权限名称
     */
    private String menuName;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否展开
     */
    private Integer open;
    /**
     * 菜单类型
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 接口参数
     */
    private String perms;
    /**
     * 是否可用,0禁用,1启用
     */
    private Integer available;

}