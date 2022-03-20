package com.zhou.jianzhi.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 求职者信息表
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
public class JobSeekerDTO extends PageParam {

    private static final long serialVersionUID = 1L;

    /**
     * 用户详细信息id
     */
    private Integer id;

    /**
     * 用户表的id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    private String sex;

    /**
     * 年级
     */

    private String grade;


    private Date birthday;


    private String phone;


    private String wechat;


    private String qq;


    private String email;



    private Integer schoolId;

    private String sno;

    /**
     * 头像
     */

    private String img;


}
