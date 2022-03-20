package com.zhou.jianzhi.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeChatLoginVo {

    // 用户id
    private String id;
    // 部门职位id
    private Integer empId;
    // 部门名
    private String deptName;
    // 用户名
    private String userName;
    // 头像地址
    private String avatarUrl;
    // 手机号
    private String phone;
    // openid
    private String openId;
    // 角色
    private ArrayList<Long> roles;
}
