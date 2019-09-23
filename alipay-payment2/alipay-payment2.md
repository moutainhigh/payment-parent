### FAQ

1. 支付宝对接依赖包：

 ```xml
 <dependency>
                <groupId>com.alipay.sdk</groupId>
                <artifactId>alipay-sdk-java</artifactId>
                <version>4.5.0.ALL</version>
            </dependency>
 ```
 
2. 关单接口提示订单不存在
 
 1）生成二维码其实是没有生成订单的。   
 2）用户扫码之前二维码有效期2小时(没找到地方配置)，过期二维码失效。   
 3）扫码之后有效期根据timeout_express时间指定，如果用户扫码了，但是没有支付，这时候timeout_express决定二维码失效时间。  
 4) 如果你调用的是当面付预下单接口(alipay.trade.precreate)，调用成功后订单实际上是没有生成，因为创建一笔订单要买家、卖家、金额三要素。预下单并没有创建订单，所以根据商户订单号操作订单，比如查询或者关闭，会报错订单不存在。当用户扫码后订单才会创建，用户扫码之前二维码有效期2小时，扫码之后有效期根据timeout_express时间指定。