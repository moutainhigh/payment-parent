package com.payment.alipay.http;

import com.payment.alipay.util.ConstantUtil;
import org.apache.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author code
 * @Title: HttpUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/21下午2:07
 */
public class HttpUtil {

    private HttpUtil(){

    }

    public static void writeToStream(HttpServletResponse response, String result) throws IOException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write(result.getBytes(ConstantUtil.UTF8));
        out.flush();
    }
}
