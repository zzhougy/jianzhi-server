package com.zhou.jianzhi.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("job_seeker")
public class JobSeeker  {

    private static final long serialVersionUID = 1L;

    /**
     * 用户详细信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户表的id
     */
    @TableField("user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;

    /**
     * 年级
     */
    @TableField("grade")
    private String grade;

    @TableField("birthday")
    private Date birthday;

    @TableField("phone")
    private String phone;

    @TableField("wechat")
    private String wechat;

    @TableField("qq")
    private String qq;

    @TableField("email")
    private String email;

    @TableField("school_id")
    private Integer schoolId;
    @TableField("sno")
    private String sno;


    /**
     * 头像
     */
    @TableField("img")
    private String img;


    /**
     * 返回前端数据
     */
    @TableField(exist = false)
    private String username;

    /**
     * 返回前端数据
     */
    @TableField(exist = false)
    private String schoolName;

    /**
     * 返回前端数据 头像
     */
    @TableField(exist = false)
    private String imgUrl;

    /**
     * 返回前端数据 创建时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "创建时间")
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    @ColumnWidth(30)
    private Date createTime;

}
