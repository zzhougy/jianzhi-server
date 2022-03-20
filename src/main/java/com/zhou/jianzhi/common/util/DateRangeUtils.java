package com.zhou.jianzhi.common.util;

import com.zhou.jianzhi.common.enums.ErrorCodeEnum;
import com.zhou.jianzhi.exception.MyException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateRangeUtils {

    /**
     * 解析开始和结束日期,需要把结束日期时分秒从00:00:00-->23:59:59
     * @param start
     * @param end
     * @return
     */
    public static List<Date> parseStartAndEndDate(String start,String end){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<Date> dates = new ArrayList<>();
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate);
            endCal.set(endCal.get(Calendar.YEAR),endCal.get(Calendar.MONTH),endCal.get(Calendar.DAY_OF_MONTH),
                    23,59,59);
            endDate = endCal.getTime();
            dates.add(startDate);
            dates.add(endDate);
            return dates;
        } catch (ParseException e) {
            throw new MyException(ErrorCodeEnum.PARSE_ERROR.getCode(),ErrorCodeEnum.PARSE_ERROR.getMsg());
        }

    }
}
