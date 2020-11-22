package com.payment.alipay.enums;

/**
 * wechat Url Config
 * 不同接口进行不同的校验使用
 *
 * @author code
 * @Title: UrlEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/21下午8:37
 */
public enum UrlEnum {
    /**
     * 支付URL
     */
    PAY_URL
    /**
     * 企业付款URL
     */
    , BIZ_PAY_URL
    /**
     * 支付通知URL
     */
    , NOTIFY_URL
    /**
     * 退款申请URL
     */
    , REFUND_URL
    /**
     * 订单查询URL
     */
    , ORDER_QUERY_URL
    /**
     * 关闭订单URL
     */
    , CLOSE_ORDER_URL
    /**
     * 退款查询URL
     */
    , REFUND_QUERY_URL
    /**
     * 对账单下载URL
     */
    , DOWNLOAD_BILL_URL
    /**
     * 退款查询URL
     */
    , REFUND_NOTIFY_URL

    /**
     * 单次分账请求URL
     */
    , SUB_ACCOUNT_URL

    /**
     * 多次分账请求URL
     */
    , SUB_ACCOUNT_MULTI_URL
    /**
     * 分账查询URL
     */
    , SUB_ACCOUNT_QUERY_URL
    /**
     * 添加分账接收方URL
     */
    , SUB_ACCOUNT_ADD_RECEIVER_URL
    /**
     * 删除分账接收方URL
     */
    , SUB_ACCOUNT_DEL_RECEIVER_URL
    /**
     * 完成分账URL
     */
    , SUB_ACCOUNT_FINISH_URL

}
