package cn.aposoft.ecommerce.wechat.beans.protocol.close_protocol;

import cn.aposoft.ecommerce.wechat.beans.protocol.BaseResponseBeans;

/**
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_3
 * 关闭订单返回参数
 * User: rizenguo
 * Date: 2014/10/25
 * Time: 16:43
 */
public class WechatCloseResData extends BaseResponseBeans {


    private String result_msg;


    public String getResult_msg() {
        return result_msg;
    }

    public WechatCloseResData setResult_msg(String result_msg) {
        this.result_msg = result_msg;
        return this;
    }


}
