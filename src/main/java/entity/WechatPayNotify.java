package entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信支付回调通知
 * 
 * @author Niu Li
 * @date 2016/10/29
 */
@XmlRootElement(name = "xml")
public class WechatPayNotify {
	private String appid;
	private String mch_id;
	private String sub_appid;// 子商户公众账号ID sub_appid 否 String(32)
								// wx8888888888888888 微信分配的子商户公众账号ID
	private String sub_mch_id;// 子商户号 sub_mch_id 是 String(32) 1900000109
								// 微信支付分配的子商户号

	private String device_info;
	private String nonce_str;
	private String sign;// 签名 sign 是
	private String result_code;// 业务结果 result_code 是 SUCCESS/FAIL
	private String return_code;// 返回结果
	private String return_msg;//
	private String err_code;// 错误代码 err_code 否
	private String err_code_des;// 错误代码描述 err_code_des 否
	private String openid;// 用户标识 openid 是
	private String is_subscribe;// 是否关注公众账号 is_subscribe 否
								// 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	private String sub_openid;// 用户子标识 sub_openid 否 String(128)
								// wxd930ea5d5a258f4f 用户在子商户appid下的唯一标识
	private String sub_is_subscribe;// 是否关注子公众账号 sub_is_subscribe 否 String(1) Y
									// 用户是否关注子公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	private String trade_type; // 交易类型 trade_type
	private String bank_type;// 付款银行 银行类型，采用字符串类型的银行标识，银行类型见附表
	private Integer total_fee;// 总金额 total_fee
	private String fee_type;// 货币种类 fee_type
	private Integer cash_fee;// 现金支付金额 cash_fee 现金支付金额订单现金支付金额，详见支付金额
	private String cash_fee_type;// 现金支付货币类型 cash_fee_type
	private String settlement_total_fee; // 应结订单金额 settlement_total_fee 否 Int
											// 100
											// 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	private String coupon_fee;// 代金券或立减优惠金额 coupon_fee
	private String coupon_count;// 代金券或立减优惠使用数量 coupon_count
	private String coupon_type_0; //代金券类型coupon_type_$n
	private String coupon_id_0;// 代金券或立减优惠ID coupon_id_$n
	private String coupon_fee_0;// 单个代金券或立减优惠支付金额 coupon_fee_$n
	private String coupon_type_1;
	private String coupon_id_1;// 代金券或立减优惠ID coupon_id_$n
	private String coupon_fee_1;// 单个代金券或立减优惠支付金额 coupon_fee_$n
	private String coupon_type_2;
	private String coupon_id_2;// 代金券或立减优惠ID coupon_id_$n
	private String coupon_fee_2;// 单个代金券或立减优惠支付金额 coupon_fee_$n
	private String transaction_id;// 微信支付订单号 transaction_id
	private String out_trade_no;// 商户订单号 out_trade_no
	private String attach;// 商家数据包 attach
	private String time_end;// 支付完成时间 time_end

	
	public String getCoupon_type_0() {
		return coupon_type_0;
	}

	public void setCoupon_type_0(String coupon_type_0) {
		this.coupon_type_0 = coupon_type_0;
	}

	public String getCoupon_type_1() {
		return coupon_type_1;
	}

	public void setCoupon_type_1(String coupon_type_1) {
		this.coupon_type_1 = coupon_type_1;
	}

	public String getCoupon_type_2() {
		return coupon_type_2;
	}

	public void setCoupon_type_2(String coupon_type_2) {
		this.coupon_type_2 = coupon_type_2;
	}

	public String getSub_openid() {
		return sub_openid;
	}

	public void setSub_openid(String sub_openid) {
		this.sub_openid = sub_openid;
	}

	public String getSub_is_subscribe() {
		return sub_is_subscribe;
	}

	public void setSub_is_subscribe(String sub_is_subscribe) {
		this.sub_is_subscribe = sub_is_subscribe;
	}

	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public String getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public String getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getCoupon_id_0() {
		return coupon_id_0;
	}

	public void setCoupon_id_0(String coupon_id_0) {
		this.coupon_id_0 = coupon_id_0;
	}

	public String getCoupon_fee_0() {
		return coupon_fee_0;
	}

	public void setCoupon_fee_0(String coupon_fee_0) {
		this.coupon_fee_0 = coupon_fee_0;
	}

	public String getCoupon_id_1() {
		return coupon_id_1;
	}

	public void setCoupon_id_1(String coupon_id_1) {
		this.coupon_id_1 = coupon_id_1;
	}

	public String getCoupon_fee_1() {
		return coupon_fee_1;
	}

	public void setCoupon_fee_1(String coupon_fee_1) {
		this.coupon_fee_1 = coupon_fee_1;
	}

	public String getCoupon_id_2() {
		return coupon_id_2;
	}

	public void setCoupon_id_2(String coupon_id_2) {
		this.coupon_id_2 = coupon_id_2;
	}

	public String getCoupon_fee_2() {
		return coupon_fee_2;
	}

	public void setCoupon_fee_2(String coupon_fee_2) {
		this.coupon_fee_2 = coupon_fee_2;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
}
