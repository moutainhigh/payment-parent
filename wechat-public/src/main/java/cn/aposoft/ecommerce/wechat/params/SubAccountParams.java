package cn.aposoft.ecommerce.wechat.params;

import java.io.Serializable;

/**
 * 分账请求接口bean
 *
 * @author code
 * @Title: SubAccountParams
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/5下午3:07
 */
public interface SubAccountParams extends Serializable {


    /**
     * 选填。签名类型，目前只支持HMAC-SHA256
     */
    public String getSign_type();

    /**
     * 必填。微信支付订单号
     */
    public String getTransaction_id();

    /**
     * 必填。商户系统内部的分账单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一分账单号多次请求等同一次。
     */
    public String getOut_order_no();

    /**
     * 必填。分账接收方列表 不超过50个 json对象
     */
    public String getReceivers();
}
