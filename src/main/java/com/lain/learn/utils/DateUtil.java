package com.lain.learn.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String Y_M_D_HM = "yyyy_MM_dd HH:mm";
    public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyyMMdd";
    public static final String YM = "yyyyMM";
    public static final String MD = "MMdd";
    public static final String YMD02 = "yyyyMd";
    public static final String YMDHM = "yyyyMMddHHmm";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String ymd = "yyyy/MM/dd";
    public static final String ymd_HM = "yyyy/MM/dd HH:mm";
    public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 智能转换日期
     */
    public static Date smartFormat(String text) throws ParseException {
        Date date = null;
        if (text.length() == 8) {
            if (text.contains("/") || text.contains("|")) {
                date = formatStringToDate(cleanFormatter(text), YMD02);
            } else {
                date = formatStringToDate(text, YMD);
            }
        } else if (text.length() == 9) {
            date = formatStringToDate(cleanFormatter(text), YMD02);
        } else if (text.length() == 10) {
            date = formatStringToDate(cleanFormatter(text), YMD);
        } else if (text.length() == 13) {
            date = new Date(Long.parseLong(cleanFormatter(text)));
        } else if (text.length() == 16) {
            date = formatStringToDate(cleanFormatter(text), YMDHM);
        } else if (text.length() == 19) {
            date = formatStringToDate(cleanFormatter(text), YMDHMS);
        }
        return date;
    }

    /**
     * 清除日期格式符
     *
     * @param text
     * @return
     */
    private static String cleanFormatter(String text) {
        return text.replace("-", "").replace("/", "");
    }

    /**
     * 字符串转换日期
     *
     * @param argDateStr
     * @param argFormat
     * @return
     */
    public static Date formatStringToDate(String argDateStr, String argFormat) throws ParseException {
        if (argDateStr == null || argDateStr.trim().length() < 1) {
            throw new RuntimeException();
        }
        String strFormat = null;
        if (StringUtils.isEmpty(argFormat)) {
            strFormat = Y_M_D;
            if (argDateStr.length() > 16) {
                strFormat = Y_M_D_HMS;
            } else if (argDateStr.length() > 10) {
                strFormat = Y_M_D_HM;
            }
        } else {
            strFormat = argFormat;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
        //严格模式
        simpleDateFormat.setLenient(false);//这个功能是不能把1996-13-1转换为1997-1-3
        return simpleDateFormat.parse(argDateStr);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Y_M_D);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取特定日期开始时间：00：00：00
     *
     * @return 特定日0时0分0秒
     */
    public static Date getStartTimeOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取特定日期23：59：59
     *
     * @return 特定日期23：59：59
     */
    public static Date getEndTimeOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取日期中的年份
     *
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @param date
     * @return
     */
    public static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.parseInt(timestamp);
    }

    /**
     * 得到未来N天
     *
     * @param n
     * @return
     */
    public static Date getInnerDay(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    /**
     * 获取下个月第一天时间 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static Date getFirstDayOfNextMonth() {
        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.MONTH, 1);
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        return (Date) startCal.getTime().clone();
    }

    /**
     * 获得下个月最后一天时间 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static Date getLastDayOfNextMonth() {
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, 2);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        return (Date) endCal.getTime().clone();
    }

    /**
     * 获取距当前指定月份第一天时间 (yyyy-MM-dd HH:mm:ss)
     *
     * @param month
     * @return
     */
    public static Date getFirstDayOfDistanceMonth(int month) {
        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.MONTH, month);
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        return (Date) startCal.getTime().clone();
    }

    /**
     * 获得距当前月指定月份最后一天时间 (yyyy-MM-dd HH:mm:ss)
     *
     * @param month
     * @return
     */
    public static Date getLastDayOfDistanceMonth(int month) {
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, month);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        return (Date) endCal.getTime().clone();
    }

    /**
     * 获得上个月的今天时间 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static Date getLastMonthOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return (Date) calendar.getTime().clone();
    }

}
