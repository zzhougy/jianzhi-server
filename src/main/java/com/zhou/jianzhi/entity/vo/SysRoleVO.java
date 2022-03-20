package com.zhou.jianzhi.entity.vo;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class SysRoleVO {
    private Long id;

    private String roleName;

    private String remark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    private Date updateTime;

    private Boolean status;
}
