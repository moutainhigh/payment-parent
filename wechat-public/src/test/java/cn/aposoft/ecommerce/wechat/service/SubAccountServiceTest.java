package cn.aposoft.ecommerce.wechat.service;

import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_protocol.WechatSubAccountResData;
import cn.aposoft.ecommerce.wechat.beans.subaccount.SubAccountParamsDTO;
import cn.aposoft.ecommerce.wechat.enums.SignTypeEnum;
import cn.aposoft.ecommerce.wechat.params.SubAccountParams;
import cn.aposoft.ecommerce.wechat.tencent.WechatConstant;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author code
 * @Title: SubAccountServiceTest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/9下午2:48
 */
public class SubAccountServiceTest extends BaseAppTest {

    @Test
    public void subAccount() throws Exception {
        SubAccountParams params = getSubAccountParams();
        WechatSubAccountResData result = paymentService.subAccount(params);
        System.out.println("请求分账返回："+JSON.toJSONString(result));
        Assert.assertNotNull(result);
    }

    private SubAccountParams getSubAccountParams() {
        SubAccountParamsDTO dto = new SubAccountParamsDTO();
        dto.setOut_order_no("sub_"+System.currentTimeMillis())

                .setSign_type(WechatConstant.HMACSHA256)
                .setTransaction_id("4200000244201901029687627369")
                .setReceivers("[" +
                        "{" +
                        "\"account\":\"codejiayou\"," +
                        "\"amount\":10,\n" +
                        "\"description\":\"channel分账测试\"," +
                        "\"type\":\"PERSONAL_WECHATID\"" +
                        "}" +
                        "]");
        return dto;
    }
}
