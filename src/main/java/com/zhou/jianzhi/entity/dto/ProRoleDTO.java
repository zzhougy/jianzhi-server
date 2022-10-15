package com.zhou.jianzhi.entity.dto;

import lombok.Data;

/**
 * 生产角色对象 pro_role
 *
 * @author Bowen
 * @date 2020-11-09
 */
@Data
public class ProRoleDTO extends PageParam {

    /**
     * id
     */

    private Long id;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

}
