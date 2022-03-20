package com.zhou.jianzhi.entity.dto;

import com.zhou.jianzhi.vialidatedgroup.Add;
import com.zhou.jianzhi.vialidatedgroup.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class BaseUserDTO extends PageParam{
    @NotNull(message = "id不能为空",groups = Update.class)
    private Long id;
    @NotBlank(message = "登陆账号不能为空",groups= Add.class)
    private String username;
    @NotBlank(message = "密码不能为空",groups= Add.class)
    private String password;
    private Integer status;
    private String salt;
    private String ip;
    private String wechat;
    private Integer empId;
    private String name;
    private Long deptId;
    private String phone;
    private Integer type;

    private Integer schoolId;

    private String sno;



}
