package com.zhou.jianzhi.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("base_type")
public class BaseType {

    @TableId
    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("username")
    private String username;

    @TableField("type")
    private String type;

    @TableField("content")
    private String content;

    @TableField("source_page")
    private String sourcePage;
}
