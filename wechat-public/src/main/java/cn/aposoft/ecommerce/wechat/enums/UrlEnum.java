package cn.aposoft.ecommerce.wechat.enums;

/**
 * wechat Url Config
 * 不同接口进行不同的校验使用
 * @author code
 * @Title: UrlEnum
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/21下午8:37
 */
public enum UrlEnum {
    PAY_URL,NOTIFY_URL,REFUND_URL,
    ORDER_QUERY_URL,CLOSE_ORDER_URL,
    REFUND_QUERY_URL,DOWNLOAD_BILL_URL,

    //分账相关URL
    SUB_ACCOUNT_URL,
    SUB_ACCOUNT_MULTI_URL,
    SUB_ACCOUNT_QUERY_URL,
    SUB_ACCOUNT_ADD_RECEIVER_URL,
    SUB_ACCOUNT_DEL_RECEIVER_URL,
    SUB_ACCOUNT_FINISH_URL

}
