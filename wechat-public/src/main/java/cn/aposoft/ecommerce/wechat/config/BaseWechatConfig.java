package cn.aposoft.ecommerce.wechat.config;

import java.io.InputStream;

/**
 * @author code
 * @Title: BaseWechatConfig
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/18下午12:18
 */
public interface BaseWechatConfig {
    public String getAppID();

    public String getMchID();

    public String getSubAppId();

    public String getSubMchId();

    public String getKey();


    /**
     * 微信支付URL
     */

    public String getPayUrl();

    /**
     * 订单交易返回URL
     */

    public String getNotifyUrl();

    /**
     * 退款-请求URL
     */

    public String getRefundUrl();

    /**
     * 订单查询-请求URL
     */

    public String getOrderQueryUrl();

    public String getStatementPath();


    public InputStream getCertStream() throws Exception;
    /**
     * 关闭订单URL
     */

    public String getCloseOrderUrl();

    /**
     * 下载订单对账单地址
     */

    public String getDownloadBillUrl();

    /**
     * 退款查询地址
     */

    public String getRefundQueryUrl();

    /**
     * 退款通知地址
     * @return
     */
    public String getRefundNotifyUrl();

    /**
     * 单次分账请求URL
     *
     * @return
     */
    public String getSubAccountUrl();

    /**
     * 多次分账请求URL
     *
     * @return
     */
    public String getSubAccountMultiUrl();

    /**
     * 分账查询URL
     *
     * @return
     */
    public String getSubAccountQueryUrl();

    /**
     * 添加分账接收方URL
     *
     * @return
     */
    public String getSubAccountAddReceiverUrl();

    /**
     * 删除分账接收方URL
     *
     * @return
     */
    public String getSubAccountDelReceiverUrl();

    /**
     * 完成分账URL
     *
     * @return
     */
    public String getSubAccountFinishUrl();



    public String connectionsPerRoute();

    /**
     * 连接超时时间，毫秒
     *
     * @return
     */

    public int getHttpConnectTimeoutMs();

    /**
     * 读取超时时间，毫秒
     *
     * @return
     */

    public int getHttpReadTimeoutMs();


}
