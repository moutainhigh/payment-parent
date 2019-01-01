package cn.aposoft.ecommerce.wechat.beans.protocol.sub_account_receiver_protocol;

import cn.aposoft.ecommerce.wechat.beans.protocol.BaseResponseBeans;

/**
 * 分账接收人信息，添加、删除分账接收人接口的请求与返回bean使用
 *
 * @author code
 * @Title: WechatSubAccountReceiverData
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/12/21下午12:07
 */
public class WechatSubAccountReceiverData extends BaseResponseBeans {

    /**
     * 分账接收方对象，json格式
     */
    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public WechatSubAccountReceiverData setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }
}
