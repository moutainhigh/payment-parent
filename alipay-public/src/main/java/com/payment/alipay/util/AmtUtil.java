package com.payment.alipay.util;

import java.math.BigDecimal;

/**
 * @author code
 * @Title: AmtUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/92:38 PM
 */
public class AmtUtil {

    private AmtUtil() {

    }

    /**
     * 将分 转换为 元
     *
     * @param amt
     * @return
     */
    public static String getRmb(int amt) {
        return BigDecimal.valueOf(amt).multiply(BigDecimal.valueOf(0.01))
                .setScale(2, BigDecimal.ROUND_DOWN).toString();

    }
}
