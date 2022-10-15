package com.zhou.jianzhi.entity.dto;

import lombok.Data;

/**
 * @ClassName PageParam
 * @Description TODO 分页
 **/
@Data
public class PageParam {
    /**
     * 当前页
     */
    private Integer pageCurrent = 0;

    /**
     * 每页显示数量
     */
    private Integer pageSize = Integer.MAX_VALUE;
}
