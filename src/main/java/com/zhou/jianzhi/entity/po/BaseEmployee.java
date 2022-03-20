package com.zhou.jianzhi.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 */
@Data
@TableName("base_employee")
public class BaseEmployee extends BaseEntity {
    /**
     * Id主键
     */
    @TableId("id")
    private Integer id;
    /**
     * 姓名
     */
    @TableField("name")
    private String name;
    /**
     * 用户名,默认为手机号
     */
    @TableField("username")
    private String username;
    /**
     * 部门id
     */
    @TableField("dept_id")
    private Integer deptId;
    /**
     * 职位名称
     */
    @TableField("position_name")
    private String positionName;
    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;
    /**
     * 电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 邮件
     */
    @TableField("email")
    private String email;
    /**
     * qq号
     */
    @TableField("qq_num")
    private String qqNum;
    /**
     * 状态, 0 未启用 1 启用
     */
    @TableField("status")
    private Integer status;
    /**
     * 是否是用户
     */
    @TableField("is_user")
    private Integer isUser;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
    *   小程序用户表示openId
    */
    @TableField("openId")
    private String openId;

    /**
     *   头像地址
     */
    @TableField("avatarUrl")
    private String avatarUrl;
}
