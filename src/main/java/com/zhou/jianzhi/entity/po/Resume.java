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
 * 
 * </p>
 *
 * @author zhou
 * @since 2021-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("resume")
public class Resume extends BaseEntityNoDel {

    private static final long serialVersionUID = 1L;

    /**
     * 简历id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 绑定求职者id
     */
    @TableField("job_seeker_id")
    private Integer jobSeekerId;

    /**
     * 简历姓名
     */
    @TableField("res_name")
    private String resName;

    /**
     * 简历性别
     */
    @TableField("res_sex")
    private String resSex;

    /**
     * 简历期望工作
     */
    @TableField("res_expect_work")
    private String resExpectWork;

    /**
     * 工作经历
     */
    @TableField("res_work_exper")
    private String resWorkExper;

    /**
     * 项目经验
     */
    @TableField("res_project_exper")
    private String resProjectExper;

    /**
     * 邮箱
     */
    @TableField("res_email")
    private String resEmail;

    /**
     * 联系方式
     */
    @TableField("res_phone")
    private String resPhone;

    /**
     * 自我描述
     */
    @TableField("res_desc")
    private String resDesc;


    @TableField("remark")
    private String remark;


    //建议不用这个
    @TableField(exist = false)
    private AliyunOss aliyunOss;


    //建议用这个
    @TableField(exist = false)
    private String resumeAttachUrl;


}
