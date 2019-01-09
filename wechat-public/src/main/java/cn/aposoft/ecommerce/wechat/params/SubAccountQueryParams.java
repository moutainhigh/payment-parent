package cn.aposoft.ecommerce.wechat.params;

import java.io.Serializable;

/**
 * 分账查询接口bean
 *
 * @author code
 * @Title: SubAccountQueryParams
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2019/1/5下午3:08
 */
public interface SubAccountQueryParams extends Serializable {


    /**
     * 微信订单号
     */
    public String getTransaction_id();

    /**
     * 商户分账单号
     */
    public String getOut_order_no();

    /**
     * 签名类型,目前只支持HMAC-SHA256
     */
    public String getSign_type();
}
