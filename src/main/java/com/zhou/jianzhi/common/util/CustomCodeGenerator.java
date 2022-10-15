package com.zhou.jianzhi.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomCodeGenerator {

    public static String createCode(String prefix,long value){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String format = sdf.format(date);
        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        sb.append(format);
        sb.append(value);
        return sb.toString();
    }
}
