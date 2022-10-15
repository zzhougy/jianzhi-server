package com.zhou.jianzhi.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
@Accessors(chain = true)
@TableName("hr_info")
public class HrInfo  {

    private static final long serialVersionUID = 1L;

    /**
     * HRid
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户表id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * hr姓名
     */
    @TableField("name")
    private String name;

    /**
     * 所属单位
     */
    @TableField("recruit_unit_id")
    private Integer recruitUnitId;

    /**
     * 所属
     */
    @TableField("sex")
    private String sex;


    /**
     * 返回前端数据
     */
    @TableField(exist = false)
    private String username;

    /**
     * 返回前端数据
     */
    @TableField(exist = false)
    private String unitName;


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
