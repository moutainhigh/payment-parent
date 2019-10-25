package com.shui.payment.cmb.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author code
 * @Title: CmbUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/10/255:31 PM
 */
@Slf4j
public class CmbUtil {
    public static final String FORMAT_MS = "yyyyMMddHHmmssSSS";
    private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 千丁系统订单号创建
     * 创建规则：yyyyMMddHHmmssSSS + 8位随机字符串
     * 去掉入参：长度25
     *
     * @param preString 前置标识
     * @return
     */
    public static String generateOrderNo(String preString) {
        return new StringBuffer()
                .append(preString == null ? "" : preString)
//                .append(DateUtils.dateToString(DateUtils.FORMAT_MS))
                .append(dateToString(new Date(), FORMAT_MS))
                .append(generateString(8)).toString();
    }


    /**
     * 日期转换成字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String formatPattern) {
        if (StringUtils.isBlank(formatPattern)) {
            formatPattern = "yyyyMMdd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(date);
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */

    public static String generateString(int length) {

        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();

    }

    /**
     * 注意:对象类如果使用slf4j注解日志方式，会在对象中放入log属性，创建签名的过程会将该属性计入，导致签名错误，需要手动删除该属性
     * 此处对象指 BaseRequestBeans类
     *
     * @param object
     * @return
     */
    public static Map<String, String> objectToMap(Object object) {
        Map<String, String> map = new HashMap<>();
        Class<?> cls = object.getClass();
        Field[] fields = object.getClass().getDeclaredFields();
        setFieldToMap(object, map, fields);
        //读取父类属性信息
        for (Class<?> superCls = cls.getSuperclass(); superCls != null; superCls = superCls.getSuperclass()) {
            setFieldToMap(object, map, superCls.getDeclaredFields());
        }

        return map;
    }

    private static void setFieldToMap(Object object, Map paramsMap, Field[] fieldList) {
        for (Field field : fieldList) {
            field.setAccessible(true);
            Object obj = null;
            try {
                obj = field.get(object);

            } catch (IllegalAccessException e) {
                log.error("转换为map异常", e);
            }
            if (obj != null && obj != "") {
                paramsMap.put(field.getName(), obj);
            }
        }
    }
}
