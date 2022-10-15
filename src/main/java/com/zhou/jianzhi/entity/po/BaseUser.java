package com.zhou.jianzhi.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;


@Data
@TableName("base_user")
public class BaseUser {
    /**
     * Id主键
     */
    @TableId(type = IdType.INPUT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 登录账号
     */
    @TableField("username")
    private String username;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 加密盐值
     */
    @TableField("salt")
    private String salt;

    /**
     * 登录IP
     */
    @TableField("ip")
    private String ip;
    /**
     * 备注
     */
    @TableField("wechat")
    private String wechat;
    /**
     * 创建人
     */
    @TableField("emp_id")
    private Integer empId;
    /**
     * 状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 用户类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 创建人
     */
    @TableField("creator")
    @ExcelProperty(value = "创建人")
    @ColumnWidth(10)
    private String creator;
    /**
     * 创建时间
     */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "创建时间")
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    @ColumnWidth(30)
    private Date createTime;
    /**
     * 最后更新人
     */
    @TableField("updator")
    @ExcelProperty(value = "最后更新人")
    @ColumnWidth(15)
    private String updator;
    /**
     * 更新时间
     */
    @TableField(value="update_time",fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "最后更新时间")
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    @ColumnWidth(30)
    private Date updateTime;





}

