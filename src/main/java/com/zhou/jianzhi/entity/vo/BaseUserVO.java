package com.zhou.jianzhi.entity.vo;


import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
public class BaseUserVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private Boolean status;
    private String ip;
    private String wechat;
    private Integer empId;
    private Integer type;
    private Set<String> url;
    private Set<String> perms;
    private List<String> roles;
    private List<Long> roleIds;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    private Date updateTime;
    private Boolean isAdmin=false;
    private List<SysRoleVO> sysRoleVOS;
    private Long deptId;
    private String deptName;
    private String phone;
    private String name;


    private String imgUrl;

}
