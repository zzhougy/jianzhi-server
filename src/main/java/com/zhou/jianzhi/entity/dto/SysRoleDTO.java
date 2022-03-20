package com.zhou.jianzhi.entity.dto;

import com.zhou.jianzhi.vialidatedgroup.Add;
import com.zhou.jianzhi.vialidatedgroup.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SysRoleDTO extends PageParam {
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;
    @NotNull(message = "角色名称不能为空", groups = Add.class)
    private String roleName;

    private String remark;

    private Boolean status;
}
