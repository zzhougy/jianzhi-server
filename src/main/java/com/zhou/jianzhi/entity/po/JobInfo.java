package com.zhou.jianzhi.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 职位信息表
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("job_info")
public class JobInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 招聘单位id
     */
    @TableField(value = "recruit_unit_id")
    private Integer recruitUnitId;


    /**
     * 岗位名称
     */
    @TableField("job_name")
    private String jobName;

    /**
     * 兼职时间
     */
    @TableField("work_time")
    private String workTime;

    /**
     * 地点
     */
    @TableField("location")
    private String location;

    /**
     * 兼职类型/关键词（日结、java、计算机、开发）
     */
    @TableField("label")
    private String label;

    /**
     * 岗位描述（具体内容，地点）
     */
    @TableField("detail")
    private String detail;

    /**
     * 岗位要求，人员要求
     */
    @TableField("requirement")
    private String requirement;

    /**
     * 薪资待遇
     */
    @TableField("salary_treatment")
    private String salaryTreatment;

    /**
     * 岗位状态（1已发布    2岗位已关闭    3岗位已删除）
     */
    @TableField("status")
    private String status;


    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField(exist = false)
    private RecruitUnit recruitUnit;

    /**
     * need_resume_attachment
     */
    @TableField("need_resume_attachment")
    private String needResumeAttachment;

    @TableField(exist = false)
    private List<Integer> labelList;

    @TableField(exist = false)
    private String labelStr;

}
