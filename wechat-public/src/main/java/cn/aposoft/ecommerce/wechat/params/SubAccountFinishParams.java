package cn.aposoft.ecommerce.wechat.params;

import java.io.Serializable;

/**
 * 完结分账请求bean
 *
 * @author code
 * @Title: SubAccountFinishParams
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/5下午3:10
 */
public interface SubAccountFinishParams extends Serializable {


    /**
     * 选填.签名类型，目前只支持HMAC-SHA256
     */
    public String getSign_type();

    /**
     * 微信订单号
     */
    public String getTransaction_id();

    /**
     * 商户分账单号
     */
    public String getOut_order_no();

    /**
     * 商户分账金额
     */
    public int getAmount();

    /**
     * 分账完结描述
     */
    public String getDescription();
}
