package com.zhou.jianzhi.entity.dto;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO extends PageParam {
    /**
     * Id主键
     */
    private Long id;
    /**
     * 登录账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 企业号
     */
    private String companyCode;
    /**
     * 手机
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 性别
     */
    private String sex;
    /**
     * 盐
     */
    private String salt;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 最后更新人
     */
    private String updator;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ColumnWidth(30)
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    private String redisKey;


}