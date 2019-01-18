package cn.aposoft.ecommerce.wechat.service.middle;

import cn.aposoft.ecommerce.wechat.beans.protocol.close_protocol.WechatCloseResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.downloadbill_protocol.WechatDownloadBillResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.pay_protocol.WeChatPayResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.pay_query_protocol.WechatPayQueryResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.refund_protocol.WeChatRefundResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.refund_query_protocol.WechatRefundQueryResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_finish_protocol.WechatSubAccountFinishResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_protocol.WechatSubAccountResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_query_protocol.WechatSubAccountQueryResData;
import cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_receiver_protocol.WechatSubAccountReceiverData;
import cn.aposoft.ecommerce.wechat.config.BaseWechatConfig;
import cn.aposoft.ecommerce.wechat.config.WechatPubPropertiesConfig;
import cn.aposoft.ecommerce.wechat.enums.SignTypeEnum;
import cn.aposoft.ecommerce.wechat.enums.UrlEnum;
import cn.aposoft.ecommerce.wechat.exceptions.VerifySignFailException;
import cn.aposoft.ecommerce.wechat.exceptions.WechatConfigNullException;
import cn.aposoft.ecommerce.wechat.params.*;
import cn.aposoft.ecommerce.wechat.service.BasePaymentService;
import cn.aposoft.ecommerce.wechat.service.PaymentService;
import cn.aposoft.ecommerce.wechat.tencent.WechatSignature;
import cn.aposoft.ecommerce.wechat.tencent.WechatUtil;
import cn.aposoft.ecommerce.wechat.util.LogPortal;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 支付接口的实现类
 *
 * @author code
 * @Title: PaymentServiceImpl
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/19下午6:01
 */
public class PaymentServiceImpl implements PaymentService, AutoCloseable {
    BaseWechatConfig config;
    BasePaymentService basePaymentService;

    public PaymentServiceImpl(BaseWechatConfig config, BasePaymentService basePaymentService) {
        this.config = config;
        this.basePaymentService = basePaymentService;
    }

    @Override
    public WeChatPayResData pay(OrderParams orderParams) throws Exception {
        checkWechatConfig(config, UrlEnum.PAY_URL);
        //检查完成没有问题，开始发起HTTP请求
        return basePaymentService.pay(orderParams, config);
    }

    protected void checkWechatConfig(BaseWechatConfig config, UrlEnum urlEnum) throws Exception {
        if (config == null) {
            config = new WechatPubPropertiesConfig();
        }
        if (StringUtils.isEmpty(config.getAppID())) {
            throw new WechatConfigNullException("appId is null");
        }
        if (StringUtils.isEmpty(config.getMchID())) {
            throw new WechatConfigNullException("mchId is null");
        }
        if (StringUtils.isEmpty(config.getKey())) {
            throw new WechatConfigNullException("key is null");
        }
        if (StringUtils.isEmpty(config.getSubMchId())) {
            throw new WechatConfigNullException("subMchId is null");
        }

        switch (urlEnum) {
            case PAY_URL:
                if (StringUtils.isEmpty(config.getPayUrl())
                        || StringUtils.isEmpty(config.getNotifyUrl())) {
                    throw new WechatConfigNullException("PAY_URL is null");
                }
                break;
            case REFUND_URL:
                if (StringUtils.isEmpty(config.getRefundUrl())) {
                    throw new WechatConfigNullException("REFUND_URL is null");
                }
                break;
            case CLOSE_ORDER_URL:
                if (StringUtils.isEmpty(config.getCloseOrderUrl())) {
                    throw new WechatConfigNullException("CLOSE_ORDER_URL is null");
                }
                break;
            case REFUND_QUERY_URL:
                if (StringUtils.isEmpty(config.getOrderQueryUrl())) {
                    throw new WechatConfigNullException("REFUND_QUERY_URL is null");
                }
                break;
            case DOWNLOAD_BILL_URL:
                if (StringUtils.isEmpty(config.getOrderQueryUrl())) {
                    throw new WechatConfigNullException("DOWNLOAD_BILL_URL is null");
                }
                break;
            case ORDER_QUERY_URL:
                if (StringUtils.isEmpty(config.getOrderQueryUrl())) {
                    throw new WechatConfigNullException("ORDER_QUERY_URL is null");
                }
                break;
            case NOTIFY_URL:
                if (StringUtils.isEmpty(config.getOrderQueryUrl())) {
                    throw new WechatConfigNullException("NOTIFY_URL is null");
                }
                break;
            case SUB_ACCOUNT_URL:
                if (StringUtils.isEmpty(config.getSubAccountUrl())) {
                    throw new WechatConfigNullException("SUB_ACCOUNT_URL is null");
                }
                break;


            case SUB_ACCOUNT_MULTI_URL:

                if (StringUtils.isEmpty(config.getSubAccountMultiUrl())) {
                    throw new WechatConfigNullException("SUB_ACCOUNT_MULTI_URL is null");
                }
                break;


            case SUB_ACCOUNT_QUERY_URL:
                if (StringUtils.isEmpty(config.getSubAccountQueryUrl())) {
                    throw new WechatConfigNullException("SUB_ACCOUNT_QUERY_URL is null");
                }
                break;
            case SUB_ACCOUNT_ADD_RECEIVER_URL:
                if (StringUtils.isEmpty(config.getSubAccountAddReceiverUrl())) {
                    throw new WechatConfigNullException("SUB_ACCOUNT_ADD_RECEIVER_URL is null");
                }
                break;
            case SUB_ACCOUNT_DEL_RECEIVER_URL:
                if (StringUtils.isEmpty(config.getSubAccountDelReceiverUrl())) {
                    throw new WechatConfigNullException("SUB_ACCOUNT_DEL_RECEIVER_URL is null");
                }
                break;
            case SUB_ACCOUNT_FINISH_URL:
                if (StringUtils.isEmpty(config.getSubAccountFinishUrl())) {
                    throw new WechatConfigNullException("SUB_ACCOUNT_FINISH_URL is null");
                }
                break;

            default:

        }
    }

    @Override
    public WechatPayQueryResData query(OrderQueryParams orderQueryParams) throws Exception {
        checkWechatConfig(config, UrlEnum.ORDER_QUERY_URL);
        //检查完成没有问题，开始发起HTTP请求
        return basePaymentService.query(orderQueryParams, config);
    }

    @Override
    public WechatCloseResData closeOrder(CloseOrderParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.CLOSE_ORDER_URL);
        //检查完成没有问题，开始发起HTTP请求
        return basePaymentService.closeOrder(params, config);
    }

    @Override
    public WeChatRefundResData refund(RefundParams refund) throws Exception {
        checkWechatConfig(config, UrlEnum.REFUND_URL);
        //检查完成没有问题，开始发起HTTP请求
        return basePaymentService.refund(refund, config);
    }

    @Override
    public WechatRefundQueryResData refundQuery(RefundQueryParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.REFUND_QUERY_URL);
        //检查完成没有问题，开始发起HTTP请求
        return basePaymentService.refundQuery(params, config);
    }

    @Override
    public List<WechatDownloadBillResData> downloadBill(DownloadBillParams params) throws Exception {
        if (config == null) {
            config = new WechatPubPropertiesConfig();
        } else {
            checkWechatConfig(config, UrlEnum.DOWNLOAD_BILL_URL);
        }
        //检查完成没有问题，开始发起HTTP请求
        return basePaymentService.downloadBill(params, config);
    }

    @Override
    public boolean verifySign(String xml, SignTypeEnum signType) {
        try {
            return WechatSignature.verifySign(WechatUtil.xmlToMap(xml), config.getKey(), signType);
        } catch (Exception e) {
            LogPortal.error("签名校验失败,xml=[{}],[{}]", xml, e);
            return false;
        }
    }

    @Override
    public WechatSubAccountResData subAccount(SubAccountParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.SUB_ACCOUNT_URL);

        return basePaymentService.subAccount(params,config);
    }
@Override
    public WechatSubAccountResData multiSubAccount(SubAccountParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.SUB_ACCOUNT_MULTI_URL);//TODO
        return basePaymentService.multiSubAccount(params,config);
    }

    @Override
    public WechatSubAccountQueryResData subAccountQuery(SubAccountQueryParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.SUB_ACCOUNT_QUERY_URL);//TODO
        return basePaymentService.subAccountQuery(params,config);
    }

    @Override
    public WechatSubAccountReceiverData subAccountAddReceiver(SubAccountReceiverParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.SUB_ACCOUNT_ADD_RECEIVER_URL);//TODO
        return basePaymentService.subAccountAddReceiver(params,config);
    }

    @Override
    public WechatSubAccountReceiverData subAccountDelReceiver(SubAccountReceiverParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.SUB_ACCOUNT_DEL_RECEIVER_URL);//TODO
        return basePaymentService.subAccountDelReceiver(params,config);
    }

    @Override
    public WechatSubAccountFinishResData subAccountFinish(SubAccountFinishParams params) throws Exception {
        checkWechatConfig(config, UrlEnum.SUB_ACCOUNT_FINISH_URL);//TODO
        return basePaymentService.subAccountFinish(params,config);
    }

    @Override
    public void close() throws Exception {
        basePaymentService.close();
    }
}
