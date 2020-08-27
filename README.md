# payment-parent
支付项目

* 模块说明

|模块名称|说明|
|---|---|
|alipay-payment | 旧版支付宝扫码支付|
|alipay-payment2 | 未完成|
|csh-payment |未完成 |
| DimensionalCode| 二维码创建与解析模块|
|wechat-demo | 微信扫码支付测试demo|
| wechat-payment|微信普通商户版扫码支付 |
| wechat-public| 微信服务商版支付 |
|cmb-payment|招商银行，银企直联模块|
|webank| 微众银行模块  |


子项:微信支付项目 V1.0
系统主要由 cn.aposoft.ecommerce.payment.wechat.PaymentService接口的具体实现来完成微信支付相关的主要功能.
当前提供6个基本方法:

1. 统一下单  https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
2. 查询订单  https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_2
3. 关闭订单  https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_3
4. 申请退款  https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4
5. 查询退款 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_5
6. 下载对账单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_6

使用说明:

1. 调用本工具包:需要根据示例配置wechatpay.properties内部相关的配置项,并放到classpath下面
2. 所有服务调用的入参都采用接口形式,因此在调用本服务包时,需要独立实现请求接口,也可以直接拷贝使用测试包中对应的默认对象.
3. 因为微信服务器的每次请求响应时间大概为0.5s~~1.5s,处于性能考虑,在本系统中放开了单一client<-->server的最大连接数到200,因此,在单一实例上,本服务器能够同时并发完成的最大https请求数为200个,退款走独立的client,因此最大退款请求数为200个且独立计算.有需要修改配置的请在cn.aposoft.ecommerce.payment.wechat.util.SingletonHttpClientUtil中将对应的值修改为期望值.
4. 在PayService接口继承了Closeable接口,在销毁PayService对象时,应主动调用close()方法,或在spring的bean配置时添加destroy-method=close


####Update 记录

##### 2018-08-29

新增`wechat-public`，**<font color=blue>微信公众号服务商版</font>**支付模块。

当前提供6个基本方法:

1. 统一下单  https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_1
2. 查询订单  https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_2
3. 关闭订单  https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_3
4. 申请退款  https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_4
5. 查询退款  https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_5
6. 下载对账单  https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_6
7. 验签接口

http资源关闭采用1.8方式的自动关闭方式操作，参见单元测试中的`cn.aposoft.ecommerce.wechat.service.PaymentServiceTest#payTest()`方法


##### 2019-01-18

新增服务商模式的分账功能(该功能微信尚处于内测阶段)

地址：https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=24_1&index=1


**如果对您有帮助，请捐赠以表支持，code在此非常感谢您的慷慨。**<br><br>

<img src="https://images.gitee.com/uploads/images/2019/0122/104135_26cef806_2275632.jpeg" width="300">

##### 2019-10-15

新增招商银行银企直联模块，文档地址：https://u.ebank.cmbchina.com/CmbBank_GenShell/UI/Help/DCBank2/DownLoad.aspx