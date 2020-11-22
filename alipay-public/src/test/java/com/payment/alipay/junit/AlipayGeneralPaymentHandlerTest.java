package com.payment.alipay.junit;

import com.alibaba.fastjson.JSON;
import com.payment.alipay.beans.downloadprotocol.AlipayDownloadBillResData;
import com.payment.alipay.dto.*;
import com.payment.alipay.enums.*;
import com.payment.alipay.model.result.*;
import com.payment.alipay.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author code
 * @Title: AlipayGeneralPaymentHandlerTest
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/9/92:03 PM
 */
public class AlipayGeneralPaymentHandlerTest extends AppJunitGeneralBaseConfig {
    @Before
    public void testReady() {
        initAlipayConfig();
    }


    @Test
    public void order() throws Exception {
        QdsOrderDTO qdsOrderDTO = getOrderDTO();
        AlipayF2FPrecreateResult result = alipayPaymentServiceHandler.pay(qdsOrderDTO, AlipayF2FPrecreateResult.class);
        System.out.println("返回结果：" + JSON.toJSONString(result));
        System.out.println("qr_code=" + result.getResponse().getQrCode());
        Assert.assertEquals(true, result.isTradeSuccess());
    }

    @Test
    public void query() throws Exception {
        QdsQueryDTO queryDTO = getQueryDTO();
        AlipayF2FQueryResult result = alipayPaymentServiceHandler.query(queryDTO, AlipayF2FQueryResult.class);
        System.out.println("查询返回结果：" + JSON.toJSONString(result));
        Assert.assertEquals("10000", result.getResponse().getCode());
    }

    @Test
    public void refund() throws Exception {
        RefundOrder refund = getRefundOrder();
        AlipayF2FRefundResult result = alipayPaymentServiceHandler.refund(refund, AlipayF2FRefundResult.class);
        System.out.println("返回结果：" + JSON.toJSONString(result));
        Assert.assertEquals("ACQ.TRADE_HAS_CLOSE", result.getResponse().getSubCode());
    }


    @Test
    public void downloadBill() throws Exception {
        List<AlipayDownloadBillResData> result = alipayPaymentServiceHandler.downloadBillFromChannel("20190916", BillTypeEnum.TRADE);
    }

    @Test
    public void closeOrder() throws Exception {
        QdsOrderDTO qdsOrderDTO = getOrderDTO();
        AlipayF2FPrecreateResult result = alipayPaymentServiceHandler.pay(qdsOrderDTO, AlipayF2FPrecreateResult.class);
        System.out.println("下单返回结果：" + JSON.toJSONString(result));
        System.out.println("qr_code=" + result.getResponse().getQrCode());


        CloseDTO closeOrderDTO = getCloseDTO(qdsOrderDTO.getOrderNo());
        AlipayF2FCloseOrderResult closeResult = alipayPaymentServiceHandler.closeOrder(closeOrderDTO, AlipayF2FCloseOrderResult.class);
        System.out.println("关单返回结果：" + JSON.toJSONString(closeResult));


        Assert.assertEquals("ACQ.TRADE_NOT_EXIST", closeResult.getResponse().getSubCode());
    }

    private CloseDTO getCloseDTO(String orderNo) {
        CloseDTO dto = new CloseDTO();
        dto.setQdsOrderNo(orderNo);
        return dto;
    }


    private QdsQueryDTO getQueryDTO() {
        QdsQueryDTO dto = new QdsQueryDTO();
        dto.setQdsOrderNo("11");
        dto.setChannelOrderNo("2019091022001458861053413899");
        dto.setChannelType(ChannelTypeEnum.ALIPAY);
        return dto;
    }

    private RefundOrder getRefundOrder() {
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setChannelOrderNo("2019091122001458861053645725")
                .setQdsOrderNo("qd_20190911161813324ir4mrNy3")
                .setChannelType(ChannelTypeEnum.ALIPAY)
                .setOrderAmt(1)
                .setRefundAmt(1)
                .setRefundNotifyUrl("")
                .setRefundOrderNo("re_channel_" + System.currentTimeMillis())
                .setRefundReason("支付宝退款");

        return refundOrder;
    }

    private QdsOrderDTO getOrderDTO() {

        QdsOrderDTO order = new QdsOrderDTO();

        String tradeType = TradeTypeEnum.NATIVE.name();
        order.setIsPub(2)
                .setBody("下单测试-channel模块")//
                .setOrderAmt(1)//
                .setPaymentType(1)//
                .setMerchantOrderNo(PaymentUtil.generateString(25))//
                .setCurrency("CNY")//
                .setIp("123.32.15.22")
                .setSignType(EncryptContant.RSA)
                .setTradeType(tradeType)
                .setOrderNo("channel_" + System.currentTimeMillis())
                .setTimeExpire(2400)
                .setOrderStatus(OrderStatusEnum.PROCESSING)
                .setOrderGenerateTime(System.currentTimeMillis())
                .setChannelType(ChannelTypeEnum.ALIPAY)//
        ;

        return order;
    }


    private String alipayBill() {
        return "#支付宝业务明细查询\n" +
                "#账号：[20880211096256330156]\n" +
                "#起始日期：[2019年09月16日 00:00:00]   终止日期：[2019年09月17日 00:00:00]\n" +
                "#-----------------------------------------业务明细列表----------------------------------------\n" +
                "支付宝交易号,商户订单号,业务类型,商品名称,创建时间,完成时间,门店编号,门店名称,操作员,终端号,对方账户,订单金额（元）,商家实收（元）,支付宝红包（元）,集分宝（元）,支付宝优惠（元）,商家优惠（元）,券核销金额（元）,券名称,商家红包消费金额（元）,卡消费金额（元）,退款批次号/请求号,服务费（元）,分润（元）,备注\n" +
                "2019091622001458860515315453\t,service_1568601259302\t,交易\t,下单测试,2019-09-16 10:55:16,2019-09-16 10:55:23,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,0.01,0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,\t,0.00,0.00,下单测试\n" +
                "2019091622001458860515315453\t,service_1568601259302\t,退款\t,下单测试,2019-09-16 10:55:16,2019-09-16 10:56:48,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,-0.01,-0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,re_test_1568602607356\t,0.00,0.00,下单测试\n" +
                "2019091622001458860513822558\t,service_1568603173853\t,交易\t,下单测试,2019-09-16 11:07:03,2019-09-16 11:07:09,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,0.01,0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,\t,0.00,0.00,下单测试\n" +
                "2019091622001458860513822558\t,service_1568603173853\t,退款\t,下单测试,2019-09-16 11:07:03,2019-09-16 11:26:55,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,-0.01,-0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,re_test_1568604414433\t,0.00,0.00,下单测试\n" +
                "2019091622001458860516395930\t,service_1568613406000\t,交易\t,下单测试,2019-09-16 13:57:42,2019-09-16 13:57:52,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,0.01,0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,\t,0.00,0.00,下单测试\n" +
                "2019091622001458860516395930\t,service_1568613406000\t,退款\t,下单测试,2019-09-16 13:57:42,2019-09-16 14:15:12,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,-0.01,-0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,re_test_1568614511552\t,0.00,0.00,下单测试\n" +
                "2019091622001458860516993488\t,qd_201909161455139634Sjad2vy\t,交易\t,RPC_GENERAL接口测试,2019-09-16 14:56:35,2019-09-16 14:57:19,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,0.01,0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,\t,0.00,0.00,RPC_GENERAL接口测试\n" +
                "2019091622001458860516993488\t,qd_201909161455139634Sjad2vy\t,退款\t,RPC_GENERAL接口测试,2019-09-16 14:56:35,2019-09-16 15:00:38,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,-0.01,-0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,re_20190916150216125q8Lmiodp\t,0.00,0.00,RPC_GENERAL接口测试\n" +
                "2019091622001465870515233238\t,20190916165659224L3VUQRL9\t,交易\t,千丁订单INSURANCE88100011909161658423172,2019-09-16 16:58:56,2019-09-16 16:58:58,\t,\t,\t,\t,*庚辰(182****01)\t,0.01,0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,\t,0.00,0.00,\n" +
                "2019091622001458860517860493\t,qd_201909161844073417CB0wPzR\t,交易\t,RPC_GENERAL接口测试,2019-09-16 18:43:06,2019-09-16 18:43:34,alipay_store\t,alipay_store\t,alipay\t,\t,*津水(602***@qq.com)\t,0.01,0.01,0.00,0.00,0.00,0.00,0.00,,0.00\t,0.00,\t,0.00,0.00,RPC_GENERAL接口测试\n" +
                "#-----------------------------------------业务明细列表结束------------------------------------\n" +
                "#交易合计：6笔，商家实收共0.06元，商家优惠共0.00元\n" +
                "#退款合计：4笔，商家实收退款共-0.04元，商家优惠退款共0.00元\n" +
                "#导出时间：[2019年09月17日 05:46:49]\n";
    }
}
