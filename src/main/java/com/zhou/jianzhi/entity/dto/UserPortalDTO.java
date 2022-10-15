package com.zhou.jianzhi.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhou.jianzhi.entity.po.BaseEntityNoDel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 门户用户表
 * </p>
 *
 * @author zhou
 * @since 2021-01-02
 */
@Data
public class UserPortalDTO extends PageParam {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型 0 求职者 1 招聘单位
     */
    private Integer type;

    /**
     * 企业号
     */
    private String companyCode;

    /**
     * 访问的ip地址
     */
    private String ip;

    /**
     * 关联的职员id
     */
    private Long empId;

    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 状态 0 未启用 1 启用
     */
    private Boolean status;

    /**
     * 创建者（管理员）
     */
    private String creator;

    /**
     * 创建时间（管理员）
     */
    private Date createTime;

    /**
     * 修改者（管理员）
     */
    private String updator;

    /**
     * 修改日期（管理员）
     */
    private Date updateTime;


}
