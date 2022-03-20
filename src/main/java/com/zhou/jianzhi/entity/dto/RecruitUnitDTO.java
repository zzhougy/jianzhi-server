package com.zhou.jianzhi.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 招聘单位表	
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
public class RecruitUnitDTO extends PageParam {

    private static final long serialVersionUID = 1L;

    /**
     * 招聘方id
     */
    private Integer id;

    /**
     * 招聘单位名称
     */
    private String name;

    /**
     * 地址
     */
    private String location;

    /**
     * 领域
     */
    private String field;

    /**
     * 规模
     */
    private String scale;

    /**
     * 介绍
     */
    private String profile;

    /**
     * 状态（0未通过审核  1审核中    2通过申请审核）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updator;

    private Long userId;

private Integer hrId;
}
