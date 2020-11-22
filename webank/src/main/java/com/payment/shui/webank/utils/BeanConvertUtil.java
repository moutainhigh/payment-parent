package com.payment.shui.webank.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author code
 * @Title: BeanConvertUtil
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/7/29 9:20
 */
public class BeanConvertUtil {

    private BeanConvertUtil() {
    }

    public static <T> T convert(Object source, Class<T> targetClass) {
        return convert(source, targetClass, (String[]) null);

    }

    private static <T> T convert(Object source, Class<T> targetClass, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
