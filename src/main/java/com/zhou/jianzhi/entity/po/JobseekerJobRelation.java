package com.zhou.jianzhi.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 求职者与岗位关系表
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("jobseeker_job_relation")
public class JobseekerJobRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 求职者id
     */
    @TableField("job_seeker_id")
    private Integer jobSeekerId;

    /**
     * 简历id
     */
    @TableField("resume_id")
    private Integer resumeId;

    /**
     * 招聘单位id
     */
    @TableField("recruit_unit_id")
    private Integer recruitUnitId;

    /**
     * 岗位id
     */
    @TableField("job_id")
    private Integer jobId;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 求职者的信息
     */
    @TableField(exist = false)
    private JobSeeker jobSeeker;

    /**
     * 求职者简历
     */
    @TableField(exist = false)
    private Resume resume;

    /**
     * 岗位信息
     */
    @TableField(exist = false)
    private JobInfo jobInfo;

    /**
     *简历状态（1待处理    2待沟通      3不合适       4已录用）
     */
    @TableField("status")
    private Integer status;


}
