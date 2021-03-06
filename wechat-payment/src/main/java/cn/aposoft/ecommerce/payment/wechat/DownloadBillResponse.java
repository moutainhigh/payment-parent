/**
 * 
 */
package cn.aposoft.ecommerce.payment.wechat;

import java.util.List;

import cn.aposoft.ecommerce.payment.wechat.util.DownloadBillResultParserFactory;

/**
 * 下载对账单响应内容
 * <p>
 * 当请求失败时: "返回状态码" return_code 会有值,和 "错误消息" return_msg 可能会有值,
 * 
 * <pre>
 *  错误码 名称 描述 原因 解决方案
 * 	<p> SYSTEMERROR 接口返回错误 系统超时 请尝试再次查询。
 *  <p> INVALID_TRANSACTIONID 无效transaction_id 请求参数未按指引进行填写 参数错误，请重新检查
 *  <p> PARAM_ERROR 参数错误 请求参数未按指引进行填写 参数错误，请重新检查
 * </pre>
 * <p>
 * 请求成功时: "返回状态码" return_code ==null data对象保留了原始的返回内容报文
 * 
 * <pre>
\ufeff交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,企业红包金额,微信退款单号,商户退款单号,退款金额,企业红包退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率
`2015-10-28 13:52:05,`123,`123,`0,`,`123,`123,`123,`NATIVE,`SUCCESS,`CFT,`CNY,`0.88,`0.00,`0,`0,`0,`0,`,`,`Hermes Perfume,`,`0.01000,`0.60%
`2015-10-28 14:26:35,`123,`123,`0,`,`123,`124,`124,`NATIVE,`SUCCESS,`CFT,`CNY,`0.17,`0.00,`0,`0,`0,`0,`,`,`Hermes Perfume,`,`0.00000,`0.80%
`2015-10-28 14:31:57,`123,`123,`0,`,`123,`125,`125,`NATIVE,`SUCCESS,`CFT,`CNY,`0.17,`0.00,`0,`0,`0,`0,`,`,`Hermes Perfume,`,`0.00000,`0.80%
`2015-10-28 14:32:37,`123,`123,`0,`,`123,`126,`126,`NATIVE,`SUCCESS,`CFT,`CNY,`0.17,`0.00,`0,`0,`0,`0,`,`,`Hermes Perfume,`,`0.00000,`0.80%
`2015-10-28 14:33:10,`123,`123,`0,`,`123,`127,`127,`NATIVE,`SUCCESS,`CFT,`CNY,`0.17,`0.00,`0,`0,`0,`0,`,`,`Hermes Perfume,`,`0.00000,`0.80%
`2015-10-28 14:34:34,`123,`123,`0,`,`123,`128,`128,`NATIVE,`SUCCESS,`CFT,`CNY,`0.17,`0.00,`0,`0,`0,`0,`,`,`Hermes Perfume,`,`0.00000,`0.80%
总交易单数,总交易额,总退款金额,总企业红包退款金额,手续费总金额
`6,`1.73,`0.00,`0.00,`0.01000
 * </pre>
 * 
 * @author Jann Liu
 */
public class DownloadBillResponse extends ResponseBase implements DownloadBillResult {
	private static final long serialVersionUID = 6027312164496808849L;
	/***************************************************************/

	/*
	 * 返回信息 return_msg
	 * 
	 * 否 String(128) 签名失败
	 * 
	 * 返回信息，如非空，为错误原因
	 * 
	 * 签名失败
	 * 
	 * 参数格式校验错误
	 * 
	 * 该日期订单未生成
	 */
	/***************************************************************/



	/***************************************************************/
	// 成功时返回:
	/*
	 * 成功时，数据以文本表格的方式返回，第一行为表头，后面各行为对应的字段内容，字段内容跟查询订单或退款结果一致，具体字段说明可查阅相应接口。
	 * 
	 * 第一行为表头，根据请求下载的对账单类型不同而不同(由bill_type决定),目前有： 当日所有订单
	 * 
	 * 交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,
	 * 代金券或立减优惠金额,微信退款单号,商户退款单号,退款金额,代金券或立减优惠退款金额，退款类型，退款状态,商品名称,商户数据包,手续费,费率
	 * 当日成功支付的订单
	 * 
	 * 交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,
	 * 代金券或立减优惠金额,商品名称,商户数据包,手续费,费率 当日退款的订单
	 * 
	 * 交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,
	 * 代金券或立减优惠金额,退款申请时间,退款成功时间,微信退款单号,商户退款单号,退款金额,代金券或立减优惠退款金额,退款类型,退款状态,商品名称,
	 * 商户数据包,手续费,费率
	 * 
	 * 从第二行起，为数据记录，各参数以逗号分隔，参数前增加`符号，为标准键盘1左边键的字符，字段顺序与表头一致。
	 * 
	 * 倒数第二行为订单统计标题，最后一行为统计数据
	 * 
	 * 总交易单数，总交易额，总退款金额，总代金券或立减优惠退款金额，手续费总金额
	 * 
	 * 举例如下：
	 * 
	 * 交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,
	 * 代金券或立减优惠金额,微信退款单号,商户退款单号,退款金额,代金券或立减优惠退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率
	 * `2014-11-1016：33：45,`wx2421b1c4370ec43b,`10000100,`0,`1000,`
	 * 1001690740201411100005734289,`1415640626,`085e9858e3ba5186aafcbaed1,`
	 * MICROPAY,`SUCCESS,`CFT,`CNY,`0.01,`0.0,`0,`0,`0,`0,`,`,`被扫支付测试,`订单额外描述,`
	 * 6e-05,`0.60% `2014-11-1016：46：14,`wx2421b1c4370ec43b,`10000100,`0,`1000,`
	 * 1002780740201411100005729794,`1415635270,`085e9858e90ca40c0b5aee463,`
	 * MICROPAY,`SUCCESS,`CFT,`CNY,`0.01,`0.0,`0,`0,`0,`0,`,`,`被扫支付测试,`订单额外描述,`
	 * 6e-05,`0.60% 总交易单数,总交易额,总退款金额,总代金券或立减优惠退款金额,手续费总金额
	 * `2,`0.02,`0.0,`0.0,`0.00012
	 */
	/***************************************************************/
	/**
	 * 请求成功后,返回的对账单数据
	 */
	private String data;

	private List<String> headers;

	private List<String> totalHeaders;

	private List<String> totalItems;

	private List<String[]> billItems;
	private static final DownloadBillResultParser parser = DownloadBillResultParserFactory.getParser();

	/**
	 * 解析原始报文,分解为拆分后的对账单内容
	 * 
	 * @param data
	 *            待解析的原始数据
	 */
	private void parseData(String data) {
		DownloadBillResult result = parser.parse(data);
		this.headers = result.getHeaders();
		this.billItems = result.getBillItems();
		this.totalHeaders = result.getTotalHeaders();
		this.totalItems = result.getTotalItems();
	}

	/**
	 * 读取对账单数据
	 * 
	 * @return
	 */
	public String getData() {
		return data;
	}

	/**
	 * 对账单数据设置
	 * 
	 * @param data
	 */
	public void setData(final String data) {
		this.data = data;
		if (data != null && !data.isEmpty()) {
			parseData(data);
		}
	}

	/**
	 * @return the headers
	 */
	public List<String> getHeaders() {
		return headers;
	}

	/**
	 * @return the totalHeaders
	 */
	public List<String> getTotalHeaders() {
		return totalHeaders;
	}

	/**
	 * @return the totalItems
	 */
	public List<String> getTotalItems() {
		return totalItems;
	}

	/**
	 * @return the billItems
	 */
	public List<String[]> getBillItems() {
		return billItems;
	}

}
