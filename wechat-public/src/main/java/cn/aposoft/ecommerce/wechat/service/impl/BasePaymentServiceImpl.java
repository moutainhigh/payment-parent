package cn.aposoft.ecommerce.wechat.service.impl;

import cn.aposoft.ecommerce.wechat.beans.invoke.HttpInvokeParams;
import cn.aposoft.ecommerce.wechat.beans.protocol.close_protocol.WechatCloseReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.close_protocol.WechatCloseResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.downloadbill_protocol.WechatDownloadBillReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.downloadbill_protocol.WechatDownloadBillResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.pay_protocol.WeChatPayResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.pay_query_protocol.WechatPayQueryReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.pay_query_protocol.WechatPayQueryResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.refund_protocol.WeChatRefundReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.refund_protocol.WeChatRefundResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.refund_query_protocol.WechatRefundQueryReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.refund_query_protocol.WechatRefundQueryResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_finish_protocol.WechatSubAccountFinishReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_finish_protocol.WechatSubAccountFinishResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_protocol.WechatSubAccountReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_protocol.WechatSubAccountResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_query_protocol.WechatSubAccountQueryReqData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_query_protocol.WechatSubAccountQueryResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_receiver_protocol.WechatSubAccountReceiverData;
import cn.aposoft.ecommerce.wechat.config.BaseWechatConfig;
import cn.aposoft.ecommerce.wechat.enums.SignTypeEnum;
import cn.aposoft.ecommerce.wechat.httpclient.HttpRequestUtil;
import cn.aposoft.ecommerce.wechat.params.*;
import cn.aposoft.ecommerce.wechat.tencent.WechatUtil;
import cn.aposoft.ecommerce.wechat.util.FileUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * 调用HTTP接口进行访问操作
 *
 * @author code
 * @Title: BasePaymentServiceImpl
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/21下午8:57
 */
public class BasePaymentServiceImpl extends AbstractBasePaymentService {

    public BasePaymentServiceImpl(HttpRequestUtil httpRequestUtil) {
        this.httpRequestUtil = httpRequestUtil;
    }

    @Override
    public WeChatPayResData pay(OrderParams orderParams, BaseWechatConfig config) throws Exception {

        String xml = createPayRequest(orderParams, config);
        String response = httpRequestUtil.post(xml, config, config.getPayUrl());
        //签名校验
        checkVerify(response, config.getKey(), StringUtils.isEmpty(orderParams.getSign_type())?SignTypeEnum.MD5:SignTypeEnum.getEnum(orderParams.getSign_type()));
        return WechatUtil.getObjectFromXML(response, WeChatPayResData.class);
    }

    @Override
    public WechatPayQueryResData query(OrderQueryParams params, BaseWechatConfig config) throws Exception {

        HttpInvokeParams<WechatPayQueryResData> invokeParams = convertInvoke(params, config,
                WechatPayQueryReqData.class, WechatPayQueryResData.class, config.getOrderQueryUrl(), SignTypeEnum.MD5);

        return httpInvoke(invokeParams);
    }


    @Override
    public WechatCloseResData closeOrder(CloseOrderParams params, BaseWechatConfig config) throws Exception {
        HttpInvokeParams<WechatCloseResData> invokeParams = convertInvoke(params, config,
                WechatCloseReqData.class, WechatCloseResData.class, config.getCloseOrderUrl(), SignTypeEnum.MD5);

        return httpInvoke(invokeParams);
    }

    @Override
    public WeChatRefundResData refund(RefundParams params, BaseWechatConfig config) throws Exception {

        HttpInvokeParams<WeChatRefundResData> invokeParams = convertInvoke(params, config,
                WeChatRefundReqData.class, WeChatRefundResData.class, config.getRefundUrl(), SignTypeEnum.MD5);

        return httpsInvoke(invokeParams);

    }

    @Override
    public WechatRefundQueryResData refundQuery(RefundQueryParams params, BaseWechatConfig config) throws Exception {

        HttpInvokeParams<WechatRefundQueryResData> invokeParams = convertInvoke(params, config,
                WechatRefundQueryReqData.class, WechatRefundQueryResData.class, config.getRefundQueryUrl(), SignTypeEnum.MD5);

        return httpInvoke(invokeParams);


    }

    @Override
    public List<WechatDownloadBillResData> downloadBill(DownloadBillParams params, BaseWechatConfig config) throws Exception {

        HttpInvokeParams<Object> invokeParams = convertInvoke(params, config,
                WechatDownloadBillReqData.class, null, config.getDownloadBillUrl(), SignTypeEnum.MD5);
        String xml = createXmlRequest(invokeParams);
        String response = httpRequestUtil.post(xml, invokeParams.getConfig(), invokeParams.getUrl());
        //默认检测对账单路径是否存在，存在，则生成文件记录，不存在，则不进行文件数据记录
        if (StringUtils.isNotEmpty(config.getStatementPath())) {
            String path = config.getStatementPath();
            String pathName = null;
            if (path.lastIndexOf("/") > 0) {//判断路径是否以左斜线结尾，并生成相应的路径
                pathName = config.getStatementPath() + params.getBill_date() + File.separator + params.getBill_type() + ".csv";
            } else {
                pathName = config.getStatementPath() + File.separator + params.getBill_date() + File.separator + params.getBill_type() + ".csv";
            }
            FileUtil.save2Local(response, pathName);
        }
        //解析对账单数据信息
        List<WechatDownloadBillResData> list = convertWechatDownloadBillDataList(response, params);
        return list;
    }

    @Override
    public void close() throws Exception {
        httpRequestUtil.close();
    }

    @Override
    public WechatSubAccountResData subAccount(SubAccountParams params, BaseWechatConfig config) throws Exception {
        HttpInvokeParams<WechatSubAccountResData> invokeParams = convertInvoke(params, config,
                WechatSubAccountReqData.class, WechatSubAccountResData.class, config.getSubAccountUrl(), SignTypeEnum.HMACSHA256);

        return httpsInvoke(invokeParams);

    }

    @Override
    public WechatSubAccountResData multiSubAccount(SubAccountParams params, BaseWechatConfig config) throws Exception {
        HttpInvokeParams<WechatSubAccountResData> invokeParams = convertInvoke(params, config,
                WechatSubAccountReqData.class, WechatSubAccountResData.class, config.getSubAccountMultiUrl(), SignTypeEnum.HMACSHA256);

        return httpsInvoke(invokeParams);
    }

    @Override
    public WechatSubAccountQueryResData subAccountQuery(SubAccountQueryParams params, BaseWechatConfig config) throws Exception {
        HttpInvokeParams<WechatSubAccountQueryResData> invokeParams = convertInvoke(params, config,
                WechatSubAccountQueryReqData.class, WechatSubAccountQueryResData.class, config.getSubAccountQueryUrl(), SignTypeEnum.HMACSHA256);

        return httpInvoke(invokeParams);
    }

    @Override
    public WechatSubAccountReceiverData subAccountAddReceiver(SubAccountReceiverParams params, BaseWechatConfig config) throws Exception {
        HttpInvokeParams<WechatSubAccountReceiverData> invokeParams = convertInvoke(params, config,
                WechatSubAccountReceiverData.class, WechatSubAccountReceiverData.class, config.getSubAccountAddReceiverUrl(), SignTypeEnum.HMACSHA256);

        return httpInvoke(invokeParams);
    }

    @Override
    public WechatSubAccountReceiverData subAccountDelReceiver(SubAccountReceiverParams params, BaseWechatConfig config) throws Exception {
        HttpInvokeParams<WechatSubAccountReceiverData> invokeParams = convertInvoke(params, config,
                WechatSubAccountReceiverData.class, WechatSubAccountReceiverData.class, config.getSubAccountDelReceiverUrl(), SignTypeEnum.HMACSHA256);

        return httpInvoke(invokeParams);
    }

    @Override
    public WechatSubAccountFinishResData subAccountFinish(SubAccountReceiverParams params, BaseWechatConfig config) throws Exception {
        HttpInvokeParams<WechatSubAccountFinishResData> invokeParams = convertInvoke(params, config,
                WechatSubAccountFinishReqData.class, WechatSubAccountFinishResData.class, config.getSubAccountFinishUrl(), SignTypeEnum.HMACSHA256);

        return httpsInvoke(invokeParams);
    }


}
