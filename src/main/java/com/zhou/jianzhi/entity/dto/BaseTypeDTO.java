package com.zhou.jianzhi.entity.dto;

import com.zhou.jianzhi.vialidatedgroup.Add;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class BaseTypeDTO {

    private Integer id;
    /**
     * @Description : 用户id
    */
    private String userId;
    /**
     * @Description : 用户名
     */
    private String username;
    /**
     * @Description : 方案名称
     */
    @NotBlank(message = "请输入方案名称",groups = Add.class)
    private String type;
    /**
     * @Description : 方案内容
     */
    @NotBlank(message = "请输入方案内容",groups = Add.class)
    private String content;
    /**
     * @Description : 来源页
     */
    @NotBlank(message = "请输入来源页",groups = Add.class)
    private String sourcePage;

}
