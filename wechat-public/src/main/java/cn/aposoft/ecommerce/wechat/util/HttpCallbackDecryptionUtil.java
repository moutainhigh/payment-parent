package cn.aposoft.ecommerce.wechat.util;

import cn.aposoft.ecommerce.wechat.tencent.AESUtil;
import cn.aposoft.ecommerce.wechat.tencent.WechatConstant;
import cn.aposoft.ecommerce.wechat.tencent.WechatSignature;
import cn.aposoft.ecommerce.wechat.tencent.WechatUtil;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Map;

/**
 * HTTP回调解密操作(支付与退款两种回调)
 *
 * @author code
 * @Title: HttpCallbackDecryptionUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/3/19下午5:14
 */
public class HttpCallbackDecryptionUtil {


    /**
     * 获取退款通知回调数据中req_info字段解密后的数据信息
     *
     * @param xml
     * @param key
     * @return
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static String getReqInfo(String xml, String key) throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> map = WechatUtil.xmlToMap(xml);
        //解密req_info字段信息
        return AESUtil.decodeXmlData(map.get("req_info"), key);
    }

    /**
     * 支付结果通知信息验签
     *
     * @param xml
     * @param signType
     * @param key
     * @return
     */
    public static boolean verifyPayNotify(String xml, String signType, String key) throws Exception {
        boolean verifyResult = false;
        if (WechatConstant.HMACSHA256.equals(signType)) {
            verifyResult = WechatSignature.verifySignWithHMACSHA256(xml, key);
        } else if (WechatConstant.MD5.equals(signType)) {
            verifyResult = WechatSignature.verifySignWithMD5(xml, key);
        }

        return verifyResult;
    }

    /**
     * 将回调通知信息转换为原始字符串信息
     *
     * @param request
     * @return
     * @throws IOException
     */
    private String readRequestBodyToString(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        StringBuilder buf = new StringBuilder();
        Reader isr = new InputStreamReader(is, WechatConstant.UTF8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            buf.append(line);
            buf.append('\n');
        }
        if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
        }
        isr.close();
        br.close();
        is.close();
        return buf.toString();
    }
}
