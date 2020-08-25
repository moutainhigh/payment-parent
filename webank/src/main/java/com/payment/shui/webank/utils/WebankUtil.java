package com.payment.shui.webank.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.Collections;
import java.util.List;

/**
 * webank专用工具类
 *
 * @author code
 * @Title: WebankUtil
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 14:24
 */
public class WebankUtil {
    private WebankUtil() {
    }

    /**
     * 签名生成算法
     *
     * @param values
     * @param signTicket
     * @return
     */
    public static String sign(List<String> values, String signTicket) {
        if (values == null) {
            throw new NullPointerException("values is null");
        }
        values.removeAll(Collections.singleton(null));// remove null
        values.add(signTicket);
        java.util.Collections.sort(values);
        StringBuilder sb = new StringBuilder();
        for (String s : values) {
            sb.append(s);
        }
        return Hashing.sha1().hashString(sb, Charsets.UTF_8).toString().toUpperCase();
    }
}
