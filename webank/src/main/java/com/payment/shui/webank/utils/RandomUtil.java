package com.payment.shui.webank.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 随机数工具类
 *
 * @author code
 * @Title: RandomUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2020/7/2116:56
 */
public class RandomUtil {

    private RandomUtil() {
    }

    private static final String NUMBER = "1234567890";
    private static final String ALPHA_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALLCHAR = ALPHA_UPPER + ALPHA_LOWER + NUMBER;
    private static final String ALPHA = ALPHA_UPPER + ALPHA_LOWER;


    public static String generateString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            builder.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return builder.toString();
    }

    public static String generateAlphaString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            builder.append(ALPHA.charAt(random.nextInt(ALPHA.length())));
        }
        return builder.toString();
    }
}
