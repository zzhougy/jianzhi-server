package com.zhou.jianzhi.entity.dto;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhou
 * @since 2021-01-09
 */
@Data
public class HrInfoDTO extends PageParam {

    private static final long serialVersionUID = 1L;

    /**
     * HRid
     */
    private Integer id;

    /**
     * 用户表id
     */
    private Long userId;

    /**
     * hr姓名
     */
    private String name;

    /**
     * 所属单位
     */
    private Integer recruitUnitId;

    private String sex;
}
