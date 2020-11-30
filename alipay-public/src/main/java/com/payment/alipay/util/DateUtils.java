package com.payment.alipay.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @author code
 * @Title: ValidationUtils
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/17下午5:39
 */
@Slf4j
public class DateUtils {

    private DateUtils() {

    }

    public static final String FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String FORMAT_MS = "yyyyMMddHHmmssSSS";
    public static final String DATE_TIME = "yyyyMMddHHmmss";

    public static final String TIME = "HHmmssSSS";

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static final String FORMAT_PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String formatPattern_full = "yyyy-MM-dd HH:mm:ss";

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();


    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    System.out.println("put new sdf of pattern " + pattern + " to map");

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }


    public static String createTime_expire(String time_expire) {
        return StringUtils.isEmpty(time_expire) ? null :
                DateUtils.long2FormatString(
                        System.currentTimeMillis() + (Long.parseLong(time_expire) * 1000), DateUtils.DATE_TIME);
    }

    /**
     * 将当前时间类型转换为上一级，仅支持整时间转换。60,120,240模式
     *
     * @param time
     * @return
     */
    public static String timeSuperLevel(int time) {
        return String.valueOf(time / 60);
    }


    public static Date addDays(Date date, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, amount);
    }


    /**
     * 日期转换成字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String formatPattern) {
        if (StringUtils.isBlank(formatPattern)) {
            formatPattern = DATE_FORMAT;
        }
        return getSdf(formatPattern).format(date);
    }

    public static String dateToString(String formatPattern) {
        return dateToString(new Date(), formatPattern);
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        return getSdf(formatPattern_full).format(date);
    }

    /**
     * 字符串转换日期
     *
     * @param str
     * @return
     */
    public static Date stringToDate(String str) {
        //str =  " 2008-07-10 19:20:00 " 格式
        if(org.springframework.util.StringUtils.hasText(str)){
            try {
                return getSdf(FORMAT_PATTERN).parse(str);
            } catch (ParseException e) {
                log.error("异常信息是:",e);;
            }
        }
        return null;
    }

    /**
     * 字符串转换日期
     * 针对 20190905141320 格式
     *
     * @param str
     * @return
     */
    public static Date str2DateByFormat(String str, String format) {
        //str =  " 2008-07-10 19:20:00 " 格式
        if(org.springframework.util.StringUtils.hasText(str)){
            try {
                return getSdf(format).parse(str);
            } catch (ParseException e) {
                log.error("异常信息是:",e);;
            }
        }
        return null;
    }

    /**
     * 根据输入的时间字符串，转换为对应的输出时间字符串
     *
     * @param time       时间串
     * @param timeFormat 对应的时间格式
     * @param outFormat  输出的时间格式
     * @return
     */
    public static String dateStringToString(String time, String timeFormat, String outFormat) {

        Date dateTime = str2DateByFormat(time, timeFormat);
        return dateToString(dateTime, outFormat);
    }


    public static String long2FormatString(Long time, String pattern) {
        if (null == time) {
            return "";
        }
        return getSdf(pattern).format(new Date(time));

    }


    /**
     * 获取当前时间之前多少秒的时间
     *
     * @param i
     * @return
     */
    public static Date getDateBeforeSecond(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, -i);

        return calendar.getTime();
    }


    /**
     * 将Date转换为年月日的Integer类型。e.g.2017-02-09 14:55:00 ==> 20170209
     *
     * @param generateTime
     * @return
     */
    public static Integer dateToInteger(Date generateTime) {
        if (null == generateTime) {
            return null;
        }
        try {
            return Integer.parseInt(getSdf(DATE_FORMAT).format(generateTime));
        } catch (Exception e) {
            log.error("dateToIntegerError,generateTime=" + generateTime, e);
            return null;
        }
    }

    public static String long2FormatString(Object time) {
        if (null == time) {
            return "";
        }
        if ("".equals(time.toString())) {
            return "";
        }
        return getSdf(FORMAT_PATTERN_FULL).format(new Date(Long.parseLong(time.toString())));
    }

    /**
     * 获取给定时间的哪一天的起始时间yyyy-MM-dd 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取给定时间的哪一天的结束时间yyyy-MM-dd 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    public static Date longStringToDate(String time) {
        Date date = new Date();
        date.setTime(Long.parseLong(time));
        return date;
    }

    public static Date longStringToDateEnd(String time) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(Long.parseLong(time));

        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.SECOND, 59);

        return cl.getTime();
    }

    /**
     * 获取指定间隔前几年的起始时间
     *
     * @param i
     * @return
     */
    public static Date getDayBeforeYear(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.YEAR, -i);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取前 day 天的相同时间点
     *
     * @param day 天数
     * @return
     */
    public static Date getPrefixDate(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -day);
        return cal.getTime();

    }

    /**
     * time1+指定的小时数 减去time2
     * <p>
     * time1 + hours - time2
     * time1与time2的时间间隔，是否超出指定小时数
     * 超出：<0
     * 未超出：>0
     *
     * @param time1 时间1
     * @param time2 时间2
     * @param hours 小时数
     * @return
     */
    public static long compare(Date time1, Date time2, int hours) {
        Calendar begin = Calendar.getInstance();
        begin.setTime(time1);
        begin.add(Calendar.HOUR_OF_DAY, hours);
        return begin.getTime().getTime() - time2.getTime();
    }

    /**
     * time1+指定的秒数 减去time2
     * <p>
     * time1 + seconds * 1000 - time2
     * time1与time2的时间间隔，是否超出指定小时数
     * 超出：<0
     * 未超出：>0
     *
     * @param time1   时间1 毫秒数
     * @param time2   时间2 毫秒数
     * @param seconds 秒数
     * @return
     */
    public static long compare(long time1, long time2, int seconds) {
        return time1 + seconds * 1000 - time2;
    }


    /**
     * 将日期按照指定的格式转换为Long类型
     *
     * @param generateTime
     * @param date2LongFormat
     * @return
     */
    public static Long dateToLong(Date generateTime, String date2LongFormat) {
        if (null == generateTime) {
            return null;
        }
        if (null == date2LongFormat) {
            date2LongFormat = DATE_FORMAT;
        }
        try {
            return Long.valueOf(getSdf(date2LongFormat).format(generateTime));
        } catch (Exception e) {
            log.error("dateToLongError,generateTime=" + generateTime, e);
            return null;
        }
    }

    public static List<Integer> getBetweenInteger(Date start, Date stop) {
        List<Integer> list = new ArrayList<>();

        Calendar begin = Calendar.getInstance();
        begin.setTime(start);
        Calendar end = Calendar.getInstance();
        end.setTime(stop);

        while (end.compareTo(begin) >= 0) {
            list.add(dateToInteger(begin.getTime()));
            begin.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        return getSdf(FORMAT_PATTERN).format(new Date());
    }

    /**
     * time1+指定的天数 减去time2
     * <p>
     * time1 + days - time2
     * time1与time2的时间间隔，单位为天，是否超出指定天数
     * 超出：<0
     * 未超出：>0
     *
     * @param time1 时间1 毫秒数
     * @param time2 时间2 毫秒数
     * @param days  指定天数
     * @return
     */
    public static long compareDays(long time1, long time2, int days) {
        Calendar begin = Calendar.getInstance();
        begin.setTimeInMillis(time1);
        begin.add(Calendar.DAY_OF_YEAR, days);
        return begin.getTime().getTime() - time2;

    }


}
