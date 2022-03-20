package com.zhou.jianzhi.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhou
 * @since 2021-02-12
 */
@Data
@Accessors(chain = true)
public class RecruitUnitLabelDTO extends PageParam{

    private static final long serialVersionUID = 1L;


    private Integer id;


    private String name;


}
