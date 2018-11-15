package entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信退款请求
 * 
 * @author Niu Li
 * @date 2016/10/29
 */
@XmlRootElement(name = "xml")
public class WechatRefund {
	private String appid;
	private String mch_id;
	private String sub_appid;// 子公众号 APPid
	private String sub_mch_id;// 子商户号
	private String nonce_str;
	private String transaction_id;// 微信流水号
	private String out_trade_no;// 商户流水号
	private String out_refund_no;// 商户退款号
	private Integer total_fee;// 总金额
	private Integer refund_fee;// 退款金额
	private String refund_fee_type;// 货币种类 refund_fee_type 否 String(8) CNY
									// 货币类型，符合ISO
									// 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String op_user_id;// 操作员
	private String sign;// 签名
	private String refund_account;// REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款
									// 仅针对老资金流商户使用
	// REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
	// REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	private String refund_desc;// 退款原因 refund_desc 否 String(80) 商品已售完
								// 若商户传入，会在下发给用户的退款消息中体现退款原因

	@XmlRootElement(name = "xml")
	public static class Response {
		private String return_code;
		private String return_msg;
		private String result_code;
		private String err_code;// 错误代码
		private String err_code_des;// 错误代码描述
		private String appid;
		private String mch_id;
		private String sub_appid;// 子公众号 APPid
		private String sub_mch_id;// 子商户号
		private String device_info;// 设备信息
		private String nonce_str;
		private String sign;
		private String transaction_id;// 微信订单号
		private String out_trade_no;// 商户系统内部的订单号
		private String out_refund_no;// 商户退款单号
		private String refund_id;// 微信退款单号
		private String refund_fee;// 退款总金额,单位为分,可以做部分退款
		private String settlement_refund_fee;// 去掉非充值代金券退款金额后的退款金额
		private String total_fee;// 订单金额
		private String settlement_total_fee;// 去掉非充值代金券后订单金额
		private String fee_type;
		private String cash_fee;// 现金支付金额
		private String cash_refund_fee;// 现金退款金额
		private String coupon_type_0;
		private String coupon_refund_fee_0;
		private String coupon_refund_id_0;
		private String coupon_type_1;
		private String coupon_refund_fee_1;
		private String coupon_refund_id_1;
		private String coupon_type_2;
		private String coupon_refund_fee_2;
		private String coupon_refund_id_2;
		// private String coupon_refund_count_1;
		// private String coupon_refund_fee_$n_$m;
		private String refund_channel;

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

		public String getCoupon_type_0() {
			return coupon_type_0;
		}

		public void setCoupon_type_0(String coupon_type_0) {
			this.coupon_type_0 = coupon_type_0;
		}

		public String getCoupon_refund_fee_0() {
			return coupon_refund_fee_0;
		}

		public void setCoupon_refund_fee_0(String coupon_refund_fee_0) {
			this.coupon_refund_fee_0 = coupon_refund_fee_0;
		}

		public String getCoupon_refund_id_0() {
			return coupon_refund_id_0;
		}

		public void setCoupon_refund_id_0(String coupon_refund_id_0) {
			this.coupon_refund_id_0 = coupon_refund_id_0;
		}

		public String getCoupon_type_1() {
			return coupon_type_1;
		}

		public void setCoupon_type_1(String coupon_type_1) {
			this.coupon_type_1 = coupon_type_1;
		}

		public String getCoupon_refund_fee_1() {
			return coupon_refund_fee_1;
		}

		public void setCoupon_refund_fee_1(String coupon_refund_fee_1) {
			this.coupon_refund_fee_1 = coupon_refund_fee_1;
		}

		public String getCoupon_refund_id_1() {
			return coupon_refund_id_1;
		}

		public void setCoupon_refund_id_1(String coupon_refund_id_1) {
			this.coupon_refund_id_1 = coupon_refund_id_1;
		}

		public String getCoupon_type_2() {
			return coupon_type_2;
		}

		public void setCoupon_type_2(String coupon_type_2) {
			this.coupon_type_2 = coupon_type_2;
		}

		public String getCoupon_refund_fee_2() {
			return coupon_refund_fee_2;
		}

		public void setCoupon_refund_fee_2(String coupon_refund_fee_2) {
			this.coupon_refund_fee_2 = coupon_refund_fee_2;
		}

		public String getCoupon_refund_id_2() {
			return coupon_refund_id_2;
		}

		public void setCoupon_refund_id_2(String coupon_refund_id_2) {
			this.coupon_refund_id_2 = coupon_refund_id_2;
		}

		public Response(String return_code) {
			this.return_code = return_code;
		}

		public Response() {
		}

		public String getCash_refund_fee() {
			return cash_refund_fee;
		}

		public void setCash_refund_fee(String cash_refund_fee) {
			this.cash_refund_fee = cash_refund_fee;
		}

		public String getResult_code() {
			return result_code;
		}

		public void setResult_code(String result_code) {
			this.result_code = result_code;
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

		public String getDevice_info() {
			return device_info;
		}

		public void setDevice_info(String device_info) {
			this.device_info = device_info;
		}

		public String getSettlement_refund_fee() {
			return settlement_refund_fee;
		}

		public void setSettlement_refund_fee(String settlement_refund_fee) {
			this.settlement_refund_fee = settlement_refund_fee;
		}

		public String getTotal_fee() {
			return total_fee;
		}

		public void setTotal_fee(String total_fee) {
			this.total_fee = total_fee;
		}

		public String getSettlement_total_fee() {
			return settlement_total_fee;
		}

		public void setSettlement_total_fee(String settlement_total_fee) {
			this.settlement_total_fee = settlement_total_fee;
		}

		public String getFee_type() {
			return fee_type;
		}

		public void setFee_type(String fee_type) {
			this.fee_type = fee_type;
		}

		public String getCash_fee() {
			return cash_fee;
		}

		public void setCash_fee(String cash_fee) {
			this.cash_fee = cash_fee;
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

		public String getOut_refund_no() {
			return out_refund_no;
		}

		public void setOut_refund_no(String out_refund_no) {
			this.out_refund_no = out_refund_no;
		}

		public String getRefund_id() {
			return refund_id;
		}

		public void setRefund_id(String refund_id) {
			this.refund_id = refund_id;
		}

		public String getRefund_channel() {
			return refund_channel;
		}

		public void setRefund_channel(String refund_channel) {
			this.refund_channel = refund_channel;
		}

		public String getRefund_fee() {
			return refund_fee;
		}

		public void setRefund_fee(String refund_fee) {
			this.refund_fee = refund_fee;
		}
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

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}

	public String getRefund_desc() {
		return refund_desc;
	}

	public void setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
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

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
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

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRefund_account() {
		return refund_account;
	}

	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}
}
