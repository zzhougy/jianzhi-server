package com.zhou.jianzhi.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhou
 * @since 2021-01-30
 */
@Data
@Accessors(chain = true)
@TableName("aliyun_oss")
public class AliyunOss  {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;


    @TableField("file_name")
    private String fileName;

    @TableField("file_path")
    private String filePath;

    @TableField("type")
    private String type;

    @TableField("source")
    private String source;

    /**
     * 文件用途（1：头像    2：其他。求职者为简历，招聘方为审核文件）
     */
    @TableField("used")
    private Integer used;

    @TableField("jobseeker_id")
    private Integer jobseekerId;

    @TableField("recruit_unit_id")
    private Integer recruitUnitId;


}
