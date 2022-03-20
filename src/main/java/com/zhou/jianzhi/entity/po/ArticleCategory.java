package com.zhou.jianzhi.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author shihh
 * @version 1.0
 * @date 2020/8/13 12:12
 */
@Data
@TableName("article_category")
public class ArticleCategory {
    @TableId
    private Long id;
    @TableField("name")
    private String name;
    @TableField("remark")
    private String remark;
    @TableField("pid")
    private Long pid;
}
