package com.zhou.jianzhi.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : chenchen
 * @ClassName BaseModule
 * @date : 2020-07-22 17:06
 * @Description TODO
 **/
@Getter
@Setter
public class BaseEntity implements Serializable {

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
    /*
     * 逻辑删除标记位
     */
//    @TableField("is_del")
//    @TableLogic
//    @ExcelIgnore
//    private Integer isDel;
}
