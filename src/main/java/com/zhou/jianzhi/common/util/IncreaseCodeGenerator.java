//package com.zhou.jianzhi.common.util;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import BaseMudRecord;
//import BasePoundRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.regex.Pattern;
//
//@Component
//public class IncreaseCodeGenerator {
//    @Autowired
//    private BasePoundRecordMapper poundRecordMapper;
//    @Autowired
//    private BaseMudRecordMapper baseMudRecordMapper;
//
//    public String getCode(String prefix){
//        String nowCode = null;
//        //查询最新的榜单记录
//        List<BasePoundRecord> list = findLatestRecord();
//        boolean empty = list.isEmpty();
//        //System.out.println("【最近的一条记录是】:"+list.get(0).toString());
//        //没有记录,生成初始记录
//        if(empty){
//            System.out.println("【编码生成器:一条记录也没有】");
//            return createCode(prefix,null);
//        }else{
//            System.out.println("【编码生成器:磅单已经有记录,最近的一条记录是】:"+list.get(0).toString());
//            //获取最近一条记录的编码,生成下一条编码
//            return createCode(prefix,list.get(0).getPoundCode());
//        }
//    }
//
//    /**
//     * @description 获取运泥单号
//     * @author luojia
//     * @date 2020-11-09 11:41:57
//     * @param
//     * @param prefix
//     * @return java.lang.String
//     */
//    public String getMudRecordCode(String prefix){
//        List<BaseMudRecord> list = findLatestMudRecord();
//        boolean empty = list.isEmpty();
//        //System.out.println("【最近的一条记录是】:"+list.get(0).toString());
//        //没有记录,生成初始记录
//        if(empty){
//            System.out.println("【一条记录也没有】");
//            return createCode(prefix,null);
//        }else{
//            System.out.println("【运泥已经有记录,最近的一条记录是】:"+list.get(0).toString());
//            //获取最近一条记录的编码,生成下一条编码
//            return createCode(prefix,list.get(0).getPoundCode());
//        }
//    }
//
//    /***
//     * @description 查询最新的一条运泥录入记录
//     * @author luojia
//     * @date 2020-11-08 18:08:54
//     * @param
//     * @return java.util.List<BaseMudRecord>
//     */
//    private List<BaseMudRecord> findLatestMudRecord() {
//        Page<BaseMudRecord> page = new Page<>(1, 1);
//        QueryWrapper<BaseMudRecord> wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("id");
//        Page<BaseMudRecord> basePoundRecordPage = this.baseMudRecordMapper.selectPage(page, wrapper);
//        List<BaseMudRecord> records = basePoundRecordPage.getRecords();
//        return records;
//    }
//
//    /**
//     * 判断字符串是否是纯数字
//     * @author Roy
//     * @param
//     * @param str
//     * @date 2020-10-29 00:20:17
//     * @return
//     */
//    public static boolean isNumeric(String str) {
//        Pattern pattern = Pattern.compile("[0-9]*");
//        return pattern.matcher(str).matches();
//    }
//
//    private static String getConvertNum(String currStr) {
//        return String.format("%0" + currStr.length() + "d", (Integer.parseInt(currStr) + 1));
//    }
//
//    /**
//     * 生成下一个四位数递增序号
//     * @author Roy
//     * @param
//     * @param str
//     * @param i
//     * @date 2020-10-29 01:41:22
//     * @return
//     */
//    public static String splitNumNext(String str, int i) {
//        String suffix = str.substring(0, i);
//        String currStr = str.substring(i);
//        if (isNumeric(currStr)) {
//            if (suffix.equals("")) {
//                suffix = null;
//            }
//            if (currStr.equals("")) {
//                currStr = null;
//            }
//            //9999
//            if (suffix == null) {
//                //头
//                if (currStr.equals("9999")) {
//                    return "A001";
//                } else {
//                    //如果是纯数字,则加一
//                    return getConvertNum(currStr);
//                }
//            } else if (currStr == null) {
//                if (suffix.equals("ZZZZ")) {
//                    //当天流水号已用完，返回null
//                    return null;
//                } else {//ZZZA-ZZZY 只剩25个加一
//                    String s = getNextChar(suffix);
//                    return s;
//                }
//            } else if (suffix != null && currStr != null) {
//                //suffix长度为0或4的已经在上面处理，此处处理1-3
//                return getNextOdd(suffix.length(), suffix, currStr);
//            }
//        } else {
//            return splitNumNext(str, i + 1);
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @author Roy
//     * @param
//     * @param length 前缀长度
//     * @param suffix 前缀
//     * @param currStr 前缀后的编码
//     * @date 2020-10-29 00:35:29
//     * @return
//     */
//    private static String getNextOdd(int length, String suffix, String currStr) {
//        //前缀长度不可能为4，只处理1,2,3
//        String zNum = length == 2 ? "Z" : length == 3 ? "ZZ" : "";
//        suffix = suffix.substring(length - 1);
//        //不是Z就是A-Y，取得是最后一个字母
//        if (suffix.equals("Z")) {
//            if (currStr.equals("999")) {
//                return "ZA01";
//            } else if (currStr.equals("99")) {
//                return "ZZA1";
//            } else if (currStr.equals("9")) {
//                return "ZZZA";
//            } else {
//                String str = getConvertNum(currStr);
//                return zNum + suffix + str;
//            }
//        } else {
//            //如果不是Z，每个字母满999都要将前缀加一
//            if (currStr.equals("999")) {
//                return zNum + getNextChar(suffix) + "001";
//            } else if (currStr.equals("99")) {
//                return zNum + getNextChar(suffix) + "01";
//            } else if (currStr.equals("9")) {
//                return zNum + getNextChar(suffix) + "1";
//            } else {
//                String str = getConvertNum(currStr);
//                return zNum + suffix + str;
//            }
//        }
//    }
//
//    private static String getNextChar(String suffix) {
//        char[] chars = suffix.toCharArray();
//        char last = (char) (chars[chars.length - 1] + 1);
//        chars[chars.length - 1] = last;
//        return String.valueOf(chars);
//    }
//
//    /**
//     * 获取当前的日期(yyyyMMdd)数值
//     * @author Roy
//     * @param
//     * @date 2020-10-28 23:19:26
//     * @return
//     */
//    public static int getNowTimeNumber(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        //把格式化的当前日期转化成数值型
//        int nowTimeCode = Integer.parseInt(sdf.format(new Date()));
//        return nowTimeCode;
//    }
//
//    /**
//     * 截取流水单号的日期编码
//     * @author Roy
//     * @param
//     * @param string
//     * @date 2020-10-28 23:16:06
//     * @return
//     */
//    public static int getTimeNumber(String string){
//        //截取单号中的日期字符串,并变成数字返回
//        int timeNum = Integer.parseInt(string.substring(2, 10));
//        return timeNum;
//    }
//
//    public static String createCode(String prefix, String oddMaxCode) {
//        String nowOdd = null;
//        //如果最大流水单号不为空
//        if (oddMaxCode != null) {
//            //截取传入编码的日期字段,与当前日期进行比较
//            if (getNowTimeNumber() != getTimeNumber(oddMaxCode)) {
//                //当前日期不等,从1开始计数
//                int number = 1;
//                //生成前缀+yyyyMMdd0001的编码
//                nowOdd = new StringBuffer(prefix).append(getBody(number)).toString();
//            }else{
//                //是同一天生成的编码
//                //TODO 处理当天超过9999，然后进位 为 A001-A999 B001-B999   ZA01-ZA99
//                //获取最新编码的递增序号
//                String odd = oddMaxCode.substring(10);
//                //在最新编码的递增序号基础上加1
//                String s = splitNumNext(odd, 0);
//                //生成完整的编码
//                nowOdd = new StringBuffer(prefix).append(oddMaxCode.substring(2,10)).append(s).toString();
//            }
//        } else {
//            //如果没有流水单号，以当前日期重新开始生成流水单号
//            int number = 1;
//            nowOdd = new StringBuffer(prefix).append(getBody(number)).toString();
//        }
//        return nowOdd;
//    }
//
//    /**
//     * 生成编码的主体yyyyMMdd+四位递增序号
//     * @author Roy
//     * @param
//     * @param number
//     * @date 2020-10-29 01:32:10
//     * @return
//     */
//    private static String getBody(int number) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        //表示num这个参数输出长度是4位,长度不够时将用0进行填充
//        String str = String.format("%04d", number);
//        return sdf.format(new Date())+str;
//    }
//
//    /**
//     * 查询最新的榜单记录
//     * @author Roy
//     * @param
//     * @date 2020-10-29 01:22:22
//     * @return
//     */
//    private List<BasePoundRecord> findLatestRecord() {
//        Page<BasePoundRecord> page = new Page<>(1, 1);
//        QueryWrapper<BasePoundRecord> wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("id");
//        Page<BasePoundRecord> basePoundRecordPage = poundRecordMapper.selectPage(page, wrapper);
//        List<BasePoundRecord> records = basePoundRecordPage.getRecords();
//        return records;
//    }
//}
