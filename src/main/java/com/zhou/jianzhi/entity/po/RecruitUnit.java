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
 * 招聘单位表	
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("recruit_unit")
public class RecruitUnit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 招聘方id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 招聘单位名称
     */
    @TableField("name")
    private String name;

    /**
     * 地址
     */
    @TableField("location")
    private String location;

    /**
     * 领域
     */
    @TableField("field")
    private String field;

    /**
     * 规模
     */
    @TableField("scale")
    private String scale;

    /**
     * 介绍
     */
    @TableField("profile")
    private String profile;

    /**
     * 状态（0未通过审核  1审核中    2通过申请审核）
     */
    @TableField("status")
    private Integer status;

    /**
     *
     */
    @TableField("hr_id")
    private Integer hrId;

    /**
     * 单位logo
     */
    @TableField(exist = false)
    private String unitImageUrl;

    /**
     * 在招岗位数量
     */
    @TableField(exist = false)
    private Long jobNum;

    /**
     *
     */
    @TableField(exist = false)
    private String fieldStr;


}
