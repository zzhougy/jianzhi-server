package com.zhou.jianzhi.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 职位信息表
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
public class JobInfoDTO extends PageParam {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位信息id
     */
    private Integer id;

    /**
     * 招聘单位id
     */
    private Integer recruitUnitId;

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 兼职时间
     */
    private String workTime;

    /**
     * 地点
     */
    private String location;

    /**
     * 兼职类型/关键词（日结、java、计算机、开发）
     */
    private String label;

    /**
     * 岗位描述（具体内容，地点）
     */
    private String detail;

    /**
     * 岗位要求，人员要求
     */
    private String requirement;

    /**
     * 薪资待遇
     */
    private String salaryTreatment;

    /**
     * 岗位状态（1已发布    2岗位已关闭    3岗位已删除）
     */
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    private String updator;

    /**
     * 备注
     */
    private String remark;


    /**
     * 登录用户id
     */
    private Long userId;

    /**
     * need_resume_attachment
     */
    private String needResumeAttachment;

}
