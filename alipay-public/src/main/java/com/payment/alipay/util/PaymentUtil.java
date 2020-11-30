package com.payment.alipay.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 对于不清楚如何分类的工具方法可以存放于此
 *
 * @author code
 * @Title: PaymentUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/14下午5:58
 */
@Slf4j
public class PaymentUtil {

    private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private PaymentUtil() {

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

    /**
     * 注意:对象类如果使用slf4j注解日志方式，会在对象中放入log属性，创建签名的过程会将该属性计入，导致签名错误，需要手动删除该属性
     * 此处对象指 BaseRequestBeans类
     *
     * @param object
     * @return
     */
//    public static Map<String, String> objectToMap(Object object) {
//        Map<String, String> map = new HashMap<>();
//        Class<?> cls = object.getClass();
//        Field[] fields = object.getClass().getDeclaredFields();
//        setFieldToMap(object, map, fields);
//        //读取父类属性信息
//        for (Class<?> superCls = cls.getSuperclass(); superCls != null; superCls = superCls.getSuperclass()) {
//            setFieldToMap(object, map, superCls.getDeclaredFields());
//        }
//
//        return map;
//    }

    /**
     * 字符串转md5字符串的方法
     * @param str
     * @return
     */
//    public static String getMD5Str(String str){
//        if (str == null || str.length() == 0) {
//            log.error("md5转换传入字符串为空");
//            return null;
//        }
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(str.getBytes());
//
//            return new BigInteger(1, md.digest()).toString(16);
//        } catch (NoSuchAlgorithmException e){
//            log.error("md5转换出现异常", e);
//            return null;
//        }
//    }
//
//    public static boolean isAllEmpty(CharSequence... css) {
//        for (CharSequence cs : css) {
//            if (StringUtils.isNotEmpty(cs)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 判断某个值是否包含在数组内
//     *
//     * @param value
//     * @param arrayList
//     * @return
//     */
//    public static boolean containsValue(String value, String[] arrayList) {
//        if (arrayList == null || arrayList.length == 0) {
//            return false;
//        }
//        for (String array : arrayList) {
//            if (array.equals(value)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 千丁系统订单号创建
//     * 创建规则：yyyyMMddHHmmssSSS + 8位随机字符串
//     * 去掉入参：长度25
//     *
//     * @param preString 前置标识
//     * @return
//     */
//    public static String generateOrderNo(String preString) {
//        return new StringBuffer()
//                .append(preString)
//                .append(DateUtils.dateToString(DateUtils.FORMAT_MS))
//                .append(generateString(6)).toString();
//    }
//    /**
//     * @param object
//     * @return
//     */
//    public static HashMap<String, Object> toParamsMap(Object object) {
//        HashMap<String, Object> paramsMap = Maps.newHashMap();
//        Field[] fieldList = object.getClass().getDeclaredFields();
//
//        //当前类属性赋值
//        setFieldToMap(object, paramsMap, fieldList);
//
//        //读取父类属性信息
//        for (Class<?> superCls = object.getClass().getSuperclass(); superCls != null; superCls = superCls.getSuperclass()) {
//            setFieldToMap(object, paramsMap, superCls.getDeclaredFields());
//        }
//
//        return paramsMap;
//    }
}
