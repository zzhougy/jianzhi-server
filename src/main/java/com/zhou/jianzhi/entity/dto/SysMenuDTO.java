package com.zhou.jianzhi.entity.dto;


import com.zhou.jianzhi.vialidatedgroup.Add;
import com.zhou.jianzhi.vialidatedgroup.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author shihh
 * @version 1.0
 * @date 2020/7/24 17:06
 */
@Data
public class SysMenuDTO {
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;
    @NotBlank(message = "菜单/权限名称不能为空", groups = Add.class)
    private String menuName;
    private String perms;
    @NotNull(message = "菜单/权限类别不能为空", groups = Add.class)
    private Integer type;
    @NotNull(message = "父级ID必须")
    private Long parentId;
    /**
     * 是否可用
     */
    private Integer available;
    private String icon;
    private String url;
    private Integer orderNum;
    private Integer open;
    /**
     * 该字段在po中并没有
     */
    @NotNull(message = "菜单状态不能为空")
    private boolean disabled;


}

