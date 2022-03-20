package com.zhou.jianzhi.entity.dto;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhou
 * @since 2021-01-03
 */
@Data
public class ResumeDTO extends PageParam {

    private static final long serialVersionUID = 1L;

    /**
     * 简历id
     */
    private Integer id;

    /**
     * 绑定求职者id
     */
    private Integer jobSeekerId;

    /**
     * 简历姓名
     */
    private String resName;

    /**
     * 简历性别
     */
    private String resSex;

    /**
     * 简历期望工作
     */
    private String resExpectWork;

    /**
     * 工作经历
     */
    private String resWorkExper;

    /**
     * 项目经验
     */
    private String resProjectExper;

    /**
     * 邮箱
     */
    private String resEmail;

    /**
     * 联系方式
     */
    private String resPhone;

    /**
     * 自我描述
     */
    private String resDesc;


    private String remark;


}
