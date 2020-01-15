/*
* **************************************************************
* Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
* RIGHTS RESERVED.
* **************************************************************
* PROJECT INFORMATION:
* 项目名称：DateUtils
* 文件名称：DateUtils.java
* 代码说明：日期工具类
* **************************************************************
* CHANGE HISTORY:
* Author Date Version Reason
* Dong.L 2020/1/13 14:58 v1.0.0 初始创建
*
* **************************************************************
*/
package com.dongl.utils.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日期工具类
 * @Project: DateUtils
 * @CreateDate: Created in 2020/1/13 14:58
 * @Author: Dong.L
 */
public class DateUtils {

    /**
     * 日期格式(yyyy-MM)
     */
    public static final String yyyy_MM = "yyyy-MM";
    /**
     * 日期格式(yyyy-MM-dd)
     */
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    /**
     * 日期格式(yyyy-MM-dd_HH_mm)
     */
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    /**
     * 日期格式(yyyy-MM-dd HH:mm:ss)
     */
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式(yyyy-MM-dd HH:mm:ss:sss)
     */
    public static final String yyyy_MM_dd_HH_mm_ss_sss = "yyyy-MM-dd HH:mm:ss:sss";
    /**
     * 日期格式(yyyy-MM-dd HH:mm:ss:sss2)
     */
    public static final String yyyy_MM_dd_HH_mm_ss_sss2 = "yyyy-MM-dd HH:mm:ss.sss";
    /**
     * 日期格式(HH:mm:ss)
     */
    public static final String HH_mm_ss = "HH:mm:ss";
    /**
     * 日期格式(yyyyMM)
     */
    public static final String yyyyMM = "yyyyMM";
    /**
     * 日期格式(yyyyMMdd)
     */
    public static final String yyyyMMdd = "yyyyMMdd";
    /**
     * 日期格式(yyyyMMddHHmmss)
     */
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    /**
     * 日期格式(yyyy.MM.dd)
     */
    public static final String yyyy_MM_dd_0 = "yyyy.MM.dd";
    /**
     * 日期格式(yyyy年MM月dd日)
     */
    public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";
    /**
     * 日期格式(yyyy年MM月)
     */
    public static final String yyyy_MM_CN = "yyyy年MM月";
    /**
     * 日期格式(yyyy年M月，不含0)
     */
    public static final String yyyy_M_CN = "yyyy年M月";
    /**
     * 日期格式(yyyy年MM月dd日HH时mm分)
     */
    public static final String yyyy_MM_dd_HH_mm_CN = "yyyy年MM月dd日HH时mm分";
    /**
     * 日期格式(yyyy年MM月dd日HH时mm分ss秒)
     */
    public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";
    /**
     * 日期格式(yyyy/MM/dd)
     */
    public static final String yyMMdd = "yyyy/MM/dd";
    /**
     * 日期格式(yyyy/MM/dd HH:mm:ss)
     */
    public static final String yyMMddHms = "yyyy/MM/dd HH:mm:ss";
    /**
     * 日期格式(M月W周)
     */
    public static final String MW = "M月W周";

//    /**
//     * DateFormat缓存
//     */
//    private static Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();

    public static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 获取DateFormat
     *
     * @param formatStr
     * @return
     */
    public static DateFormat getDateFormat(String formatStr) {
//        DateFormat df = dateFormatMap.get(formatStr);
//        if (df == null) {
//            df = new SimpleDateFormat(formatStr);
//            dateFormatMap.put(formatStr, df);
//        }
        return new SimpleDateFormat(formatStr);
    }

    /**
     * 转化String为Date类型(yyyy-MM-dd)
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr) throws ParseException {
        return strToDate(dateStr, yyyy_MM_dd);
    }

    /**
     * 转化String为Date类型
     *
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr, String format) throws ParseException {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        }
        if (format == null || format.equals("")) {
            format = yyyy_MM_dd_HH_mm_ss;
        }

        return getDateFormat(format).parse(dateStr);
    }

    /**
     * 将Date转换成字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将Date转换成formatStr格式的字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null || format.equals("")) {
            format = yyyy_MM_dd_HH_mm_ss;
        }

        return getDateFormat(format).format(date);
    }

    /**
     * 将String转换成另一格式的字符串
     *
     * @param dateStr
     * @param format1
     * @param format2
     * @return
     * @throws ParseException
     */
    public static String strToStr(String dateStr, String format1, String format2) throws ParseException {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        }
        if (format1 == null || format1.equals("")) {
            format1 = yyyy_MM_dd_HH_mm_ss;
        }
        if (format2 == null || format2.equals("")) {
            format2 = yyyy_MM_dd;
        }
        return getDateFormat(format2).format(strToDate(dateStr, format1));
    }

    /**
     * 在date上按指定calendarField上增加num
     *
     * @param date
     * @param calendarField
     * @param num
     * @return
     */
    public static Date addDate(Date date, int calendarField, int num) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(calendarField, num);

        return calendar.getTime();
    }

    /**
     * 在date上增加days天数
     *
     * @param date
     * @param days
     * @return
     * @throws ParseException
     */
    public static Date addDate(Date date, int days) throws ParseException {
        return addDate(date, Calendar.DATE, days);
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return
     * @throws Exception
     */
    public static String getCurDate() {
        return dateToStr(new Date(), yyyy_MM_dd);
    }

    /**
     * 获取当前日期时间
     *
     * @return
     * @throws
     */
    public static String getCurDateTime(String formatStr) {
        return dateToStr(new Date(), formatStr);
    }

    /**
     * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期，之间相差多少毫秒,dateStr2-dateStr1
     *
     * @param dateStr1
     * @param dateStr2
     * @return
     * @throws ParseException
     */
    public static long compareDateStr(String dateStr1, String dateStr2) throws ParseException {
        Date d1 = strToDate(dateStr1, yyyy_MM_dd_HH_mm_ss);
        Date d2 = strToDate(dateStr2, yyyy_MM_dd_HH_mm_ss);
        return d2.getTime() - d1.getTime();
    }

    /**
     * 比较date1 和 date2 的相差天数,date2-date1
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int difference(Date date1, Date date2) throws ParseException {
        DateFormat sdf = getDateFormat(yyyy_MM_dd);
        date1 = sdf.parse(sdf.format(date1));
        date2 = sdf.parse(sdf.format(date2));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        long time1 = calendar.getTimeInMillis();
        calendar.setTime(date2);
        long time2 = calendar.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 比较dateStr1 和 dateStr2 的相差天数,dateStr2-dateStr1
     *
     * @param dateStr1
     * @param dateStr2
     * @return
     * @throws ParseException
     */
    public static int difference(String dateStr1, String dateStr2) throws ParseException {
        DateFormat sdf = getDateFormat(yyyy_MM_dd);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(dateStr1));
        long time1 = calendar.getTimeInMillis();
        calendar.setTime(sdf.parse(dateStr2));
        long time2 = calendar.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 将小时数换算成以毫秒为单位的时间
     *
     * @param hours
     * @return
     */
    public static long getMicroSec(BigDecimal hours) {
        BigDecimal bd = hours.multiply(new BigDecimal(3600 * 1000));
        return bd.longValue();
    }

    /**
     * 获得指定日期当月的总天数
     *
     * @return
     */
    public static int getMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得指定日期的天数
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得指定日期的月数
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前星期数
     */
    public static String getCurWeek() {
        return weekDays[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获得指定日期的星期数
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获得指定日期的月期间
     *
     * @param date
     * @return
     */
    public static String getDaysOfMon(Date date) {
        return dateToStr(getMonthFirstDay(date), "yyyy/M/d") + "-" + dateToStr(getMonthLastDay(date), "yyyy/M/d");
    }

    /**
     * 获得昨天最后一分钟时间(XXXX-XX-XX 23:59:59)
     *
     * @param date
     * @return
     * @throws ParseException
     * @throws Exception
     */
    public static Date getYesterdayLastTime(Date date) throws ParseException {
        date = DateUtils.addDate(date, -1);
        String dateStr = DateUtils.dateToStr(date, DateUtils.yyyy_MM_dd) + " 23:59:59";
        return DateUtils.strToDate(dateStr, DateUtils.yyyy_MM_dd_HH_mm_ss);
    }

    public static final String getDateTimeFileName() throws Exception {
        DateFormat format = getDateFormat(yyyyMMddHHmmss);

        java.sql.Date tempDate = new java.sql.Date(System.currentTimeMillis());
        String fileName = format.format(tempDate);

        int value = (int) Math.round(Math.random() * 100.0D);
        if (value < 10) {
            value += 10;
        }
        value += 100;
        fileName += value;

        return fileName;
    }

    /**
     * 转成格式(yyyy-MM-dd)
     *
     * @return
     * @throws ParseException
     */
    public static Date getDateYMD(Date date) throws ParseException {
        return strToDate(dateToStr(date, yyyy_MM_dd), yyyy_MM_dd);
    }

    /**
     * 智能转换日期
     *
     * @param date
     * @return
     */
    public static String smartFormat(Date date) {

        String dateStr = null;
        if (date == null) {
            dateStr = "";
        } else {
            try {
                dateStr = dateToStr(date, yyyy_MM_dd_HH_mm_ss);
                //时分秒  
                if (dateStr.endsWith(" 00:00:00")) {
                    dateStr = dateStr.substring(0, 10);
                }
                //时分  
                else if (dateStr.endsWith("00:00")) {
                    dateStr = dateStr.substring(0, 16);
                }
                //秒 暂时注释掉一般这种场景很少用到  
                /*else if (dateStr.endsWith(":00")) {  
                    dateStr = dateStr.substring(0, 19);  
                }*/
            } catch (Exception ex) {
                throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
            }
        }
        return dateStr;
    }

    /**
     * 智能转换日期
     *
     * @param text
     * @return
     */
    public static Date smartFormat(String text) {
        Date date = null;
        try {
            if (text == null || text.length() == 0) {
                date = null;
            } else if (text.length() == 8) {
                date = strToDate(text, yyyyMMdd);
            } else if (text.length() == 10) {
                date = strToDate(text, yyyy_MM_dd);
            } else if (text.length() == 13) {
                date = new Date(Long.parseLong(text));
            } else if (text.length() == 16) {
                date = strToDate(text, yyyy_MM_dd_HH_mm);
            } else if (text.length() == 19) {
                date = strToDate(text, yyyy_MM_dd_HH_mm_ss);
            } else {
                throw new IllegalArgumentException("日期长度不符合要求!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("日期转换失败!");
        }
        return date;
    }

    /**
     * 月初
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        return new Date(cal.getTime().getTime());
    }

    /**
     * 月末
     *
     * @param date
     * @return
     */
    public static Date getMonthLastDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);
        return new Date(cal.getTime().getTime());
    }

    /**
     * 年初
     *
     * @param date
     * @return
     */
    public static Date getYearFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        return new Date(cal.getTime().getTime());
    }

    /**
     * 年末
     *
     * @param date
     * @return
     */
    public static Date getYearLastDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);
        return new Date(cal.getTime().getTime());
    }

    /**
     * 根据传入日期获取到星期一日期
     *
     * @param date
     * @return
     */
    public static Date getMondayDate(Date date) {
        // 根据今天的时间获取本周属于本月的第几周
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期

        // 根据今天的时间获取本周的开始时间
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    /**
     * 根据传入日期获取到星期天日期
     *
     * @param date
     * @return
     */
    public static Date getSundayDate(Date date) {
        // 根据今天的时间获取本周属于本月的第几周
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        // 根据今天的时间获取本周的结束时间
        now.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        now.add(Calendar.WEEK_OF_YEAR, 1);
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        now.set(Calendar.MILLISECOND, 999);
        return now.getTime();
    }

    /**
     * 判断是不是当月的日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean justMonth(String date) throws ParseException {
        return strToDate(date, yyyy_MM).compareTo(strToDate(getCurDate(), yyyy_MM)) == 0;
    }

    /**
     * 通话时长时间转换
     *
     * @param time
     * @return
     */
    public static String time(long time) {
        String totalTime = "";
        if (time == 0) {
            totalTime = 0 + "秒";
        } else {
            time = time / 1000;
            if (time >= 3600) {
                totalTime = time / 3600 + "小时";
                time = time - (time / 3600) * 3600;
            }
            if (time >= 60) {
                totalTime += time / 60 + "分";
                time = time - (time / 60) * 60;
            }
            if (time < 60) {
                totalTime += time + "秒";
            }
        }

        return totalTime;
    }

}  
