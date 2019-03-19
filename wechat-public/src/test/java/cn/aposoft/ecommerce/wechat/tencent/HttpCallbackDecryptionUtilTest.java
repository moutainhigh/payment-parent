package cn.aposoft.ecommerce.wechat.tencent;

import cn.aposoft.ecommerce.wechat.beans.protocol.refund_notify_protocol.RefundNotifyData;
import cn.aposoft.ecommerce.wechat.util.HttpCallbackDecryptionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author code
 * @Title: HttpCallbackDecryptionUtilTest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/3/19下午6:35
 */
public class HttpCallbackDecryptionUtilTest {
    String key ="qiandinghulian2018WXZFFWS0815fyy";
    /**
     * 微信退款通知数据demo
     *
     * @return
     */
    private String getRefundNotifyData() {
        return "<xml><return_code>SUCCESS</return_code><appid><![CDATA[wxd23d0632ad28c805]]></appid><mch_id><![CDATA[1510598081]]></mch_id><sub_appid><![CDATA[wx34723380b2d8a699]]></sub_appid><sub_mch_id><![CDATA[1511458511]]></sub_mch_id><nonce_str><![CDATA[815651c292e844d4922f8f81c95c4cce]]></nonce_str><req_info><![CDATA[oigrYqzoFzD6CMYv4qC0rbUJheZDPHzinAZmxDrC7Yo3IwatEOPWvIzzMAD+9MlXJODp7UUueVkRpRqT89ROyGhpxS63cvZP2VAv+VNfeW6YB+3A1nZ/N2uExV6YS1lbvsFomVK+JTP+rMpZtsUJz1qB61HrsBnni2TQ1vC1gSsQ4XM5c6Rn5TUbUB5xPJ+HexBMR1BCWV1uQyAlz0fzgE3I4hDZnAYPN/ykdJVzhI8AKZO0sKWrIRmTfI3YwRSfUFzDCVFCI7KgJQ+2vAIQHdmCJSOs8bNPsRyyW+0I7OIw9+kpyR6nPxXSRR4ZQpZnGZRlGiI3rTpvgeK1MgzXv35pIRfdVDnaarPxVmvIa8RKmDjx+SQ3v/OP3nlUs4LEH3NsGAot9WXoWaGCjw+rt96scXawyWlzQPHSCus4z+VJxK7qmU8kM469+A63Y8meHKPmySL/pr1gOx9EJs+jDFUSNV//02mrq5XaRNO22RpDtGZCM4nWqtaRB8PXX5U03RZIVPVLl6be960I5G0aeH5G8eg5K3wlklCKd1Svq63NiCQxAzzFyCjY/bFfCAYJ2M8+hngKJBZkhTo3mkhTCBlYmYz2ArKxtbGu8tU9A0K01yUzZa6BDUhAoqYU1kciblAoza6WVn900w4Z1GTaFAZ8NZyk72nmdbnuNCnpLytfoRUMWKSAPoM1aSRBSvc73EAJrw5ERfdUXewQT1DU7+3rIwRPzgeHRjVi+ALkcepKfDNVPeYIxaGWzo7jqcccjZFfpwZzGEsKlBrT/cpgb4T5RYWAtMMuKnfirVlOpqrg6hzTauK9UWaw7S+306N4zJ90xqty9GUIiFv8NXWW990BZBx5E1MC35zUZqfnNQub29AMoF2faLvDgfdk2SRQMpbfYtoub38jO158VuZbHb+N4ui9qs26kXz8UfSShbAhZ8fYwIOYsfrPnTHf536FtJMMfLvuKavdRpcmh12TCHJE1qNtQj2W+TlIyiecMNEyWHlchCk6chRG8YaGvsglOGZqHaFzKnlozLel9j0gpyBJGhhrtN13waZtLNwX3zc=]]></req_info></xml>";
    }
    @Test
    public void refundNotify() throws Exception {

        String result = HttpCallbackDecryptionUtil.getReqInfo(getRefundNotifyData(), key);
        RefundNotifyData data = WechatUtil.convertXmlToObject(result, RefundNotifyData.class);
        Assert.assertEquals("SUCCESS",data.getRefund_status());
    }

    @Test
    public void notifyData() throws Exception {
        boolean result = HttpCallbackDecryptionUtil.verifyPayNotify(contentByte(), WechatConstant.HMACSHA256, key);
        Assert.assertTrue(result);
    }

    private String contentByte() {
        return ("<xml><appid><![CDATA[wxd23d0632ad28c805]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1510598081]]></mch_id>\n" +
                "<nonce_str><![CDATA[7CNAOpo5BbmbK8vOiqGoZu7hcufQLkCJ]]></nonce_str>\n" +
                "<openid><![CDATA[o44s5uBeQvP0Oof_s3u7SdacmkJ0]]></openid>\n" +
                "<out_trade_no><![CDATA[2018081701]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[5900180FBAC5E4C688D54F706143EBEF597557F1C430ECBCC344674B8F6680E2]]></sign>\n" +
                "<sub_appid><![CDATA[wx34723380b2d8a699]]></sub_appid>\n" +
                "<sub_is_subscribe><![CDATA[N]]></sub_is_subscribe>\n" +
                "<sub_mch_id><![CDATA[1511458511]]></sub_mch_id>\n" +
                "<sub_openid><![CDATA[oDodT0XcpFfrHVR9Kc_VgpFOj8lQ]]></sub_openid>\n" +
                "<time_end><![CDATA[20180817170723]]></time_end>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type><![CDATA[NATIVE]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000139201808179060622869]]></transaction_id>\n" +
                "</xml>");
    }
}
