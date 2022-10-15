package com.zhou.jianzhi.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 求职者与岗位关系表
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
public class JobseekerJobRelationDTO extends PageParam {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 求职者id
     */
    private Integer jobSeekerId;

    /**
     * 简历id
     */
    private Integer resumeId;

    /**
     * 招聘单位id
     */
    private Integer recruitUnitId;

    /**
     * 岗位id
     */
    private Integer jobId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

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

    /**
     * 简历状态（1待处理    2待沟通      3不合适       4录用）',
     */
    private Integer status;
}
