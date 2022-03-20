package com.zhou.jianzhi.entity.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: luojia
 * @time: 2020/8/29 15:51
 */
@Data
public class MonthRangeDTO {
    List<Date> monthArray;
    String remark;
}
