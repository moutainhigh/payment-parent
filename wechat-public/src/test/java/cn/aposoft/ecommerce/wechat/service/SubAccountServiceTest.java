package cn.aposoft.ecommerce.wechat.service;

import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_finish_protocol.WechatSubAccountFinishResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_protocol.WechatSubAccountResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_query_protocol.WechatSubAccountQueryResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_receiver_protocol.WechatSubAccountReceiverData;
import cn.aposoft.ecommerce.wechat.beans.subaccount.SubAccountFinishParamsDTO;
import cn.aposoft.ecommerce.wechat.beans.subaccount.SubAccountParamsDTO;
import cn.aposoft.ecommerce.wechat.beans.subaccount.SubAccountQueryParamsDTO;
import cn.aposoft.ecommerce.wechat.beans.subaccount.SubAccountReceiverParamsDTO;
import cn.aposoft.ecommerce.wechat.params.SubAccountFinishParams;
import cn.aposoft.ecommerce.wechat.params.SubAccountParams;
import cn.aposoft.ecommerce.wechat.params.SubAccountQueryParams;
import cn.aposoft.ecommerce.wechat.params.SubAccountReceiverParams;
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

    private String SUCCESS = "SUCCESS";

    @Test
    public void subAccount() throws Exception {
        SubAccountParams params = getSubAccountParams();
        WechatSubAccountResData result = paymentService.subAccount(params);
        System.out.println("请求分账返回：" + JSON.toJSONString(result));
        Assert.assertNotNull(result);
    }

    @Test
    public void multiSubAccount() throws Exception {
        SubAccountParams params = getMultiSubAccountParams();
        WechatSubAccountResData result = paymentService.multiSubAccount(params);
        System.out.println("请求分账返回：" + JSON.toJSONString(result));
        Assert.assertNotNull(result);
    }

    @Test
    public void subAccountQuery() throws Exception {
        SubAccountQueryParams params = getSubAccountQueryParams();
        WechatSubAccountQueryResData result = paymentService.subAccountQuery(params);
        System.out.println("分账查询返回：" + JSON.toJSONString(result));
        Assert.assertNotNull(result.getReturn_code());
    }

    @Test
    public void subAccountAddReceiver() throws Exception {
        SubAccountReceiverParams params = getSubAccountReceiverParams();
        WechatSubAccountReceiverData result = paymentService.subAccountAddReceiver(params);
        System.out.println("分账接收人添加结果返回：" + JSON.toJSONString(result));
    }

    @Test
    public void subAccountDelReceiver() throws Exception {
        SubAccountReceiverParams params = getSubAccountReceiverParams();
        WechatSubAccountReceiverData result = paymentService.subAccountDelReceiver(params);
        System.out.println("删除接收人添加结果返回：" + JSON.toJSONString(result));
    }

    @Test
    public void subAccountFinish() throws Exception {

        SubAccountFinishParams params = getSubAccountFinishParams();
        WechatSubAccountFinishResData result = paymentService.subAccountFinish(params);
        System.out.println("完结分账返回：" + JSON.toJSONString(result));
        Assert.assertTrue(SUCCESS.equals(result.getReturn_code()));

    }

    private SubAccountFinishParams getSubAccountFinishParams() {
        SubAccountFinishParamsDTO params = new SubAccountFinishParamsDTO();

        params.setAmount(1)
                .setDescription("完结分账测试")
                .setOut_order_no("service_1547800518882")
                .setTransaction_id("4200000253201901181546230059");

        return params;
    }

    private SubAccountReceiverParams getSubAccountReceiverParams() {
        SubAccountReceiverParamsDTO dto = new SubAccountReceiverParamsDTO();
        dto.setReceiver(getReceiver());

        return dto;
    }

    private String getReceiver() {
        class ReceiverParams {
            private String type;
            /**
             * 必填。分账接收方帐号
             */
            private String account;
            private String name;

            public String getType() {
                return type;
            }

            public ReceiverParams setType(String type) {
                this.type = type;
                return this;
            }

            public String getAccount() {
                return account;
            }

            public ReceiverParams setAccount(String account) {
                this.account = account;
                return this;
            }

            public String getName() {
                return name;
            }

            public ReceiverParams setName(String name) {
                this.name = name;
                return this;
            }
        }
        ReceiverParams params = new ReceiverParams();
        params.setAccount("codejiayou")
                .setType("PERSONAL_WECHATID")
                .setName("于津水");
        return JSON.toJSONString(params);
    }

    private SubAccountQueryParams getSubAccountQueryParams() {
        SubAccountQueryParamsDTO dto = new SubAccountQueryParamsDTO();
        dto.setOut_order_no("sub_20190102152248326VtH1GwXJ")
                .setTransaction_id("4200000244201901029687627369")
                .setSign_type(WechatConstant.HMACSHA256);
        return dto;
    }

    private SubAccountParams getSubAccountParams() {
        SubAccountParamsDTO dto = new SubAccountParamsDTO();
        dto.setOut_order_no("sub_" + System.currentTimeMillis())

                .setSign_type(WechatConstant.HMACSHA256)
                .setTransaction_id("4200000244201901029687627369")
                .setReceivers("[" +
                        "{" +
                        "\"account\":\"codejiayou\"," +
                        "\"amount\":1,\n" +
                        "\"description\":\"channel分账测试\"," +
                        "\"type\":\"PERSONAL_WECHATID\"" +
                        "}" +
                        "]");
        return dto;
    }

    private SubAccountParams getMultiSubAccountParams() {
        SubAccountParamsDTO dto = new SubAccountParamsDTO();
        dto.setOut_order_no("sub_" + System.currentTimeMillis())

                .setSign_type(WechatConstant.HMACSHA256)
                .setTransaction_id("4200000250201901184002407716")
                .setReceivers("[" +
                        "{" +
                        "\"account\":\"codejiayou\"," +
                        "\"amount\":1,\n" +
                        "\"description\":\"channel分账测试\"," +
                        "\"type\":\"PERSONAL_WECHATID\"" +
                        "}" +
                        "]");
        return dto;
    }
}
