package com.zhou.jianzhi.entity.dto;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhou
 * @since 2021-01-30
 */
@Data
public class AliyunOssDTO extends PageParam {

    private Integer id;


    private String fileName;


    private String filePath;


    private String type;


    private String source;

    /**
     * 文件用途（1：头像    2：其他。求职者为简历，招聘方为审核文件）
     */
    private Integer used;


    private Integer jobseekerId;


    private Integer recruitUnitId;


}
