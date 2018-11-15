package entity;

public class WechatBill {
	private String trans_time;// (yyyy-MM-dd HH:mm:ss) 交易时间,
	private String app_id;// 公众账号ID,
	private String mch_id;// 商户号,
	private String sub_mch_id;// 子商户号,
	private String device_info;// 设备号,
	private String transaction_id;// 微信订单号
	private String out_trade_no;// ,商户订单号,
	private String openid;// 用户标识,
	private String trade_type;// 交易类型,
	private String trade_state;// 交易状态,
	private String bank_type;// 付款银行
	private String fee_type;// ,货币种类
	private String total_fee;// ,总金额,
	private String enterprise_red_packet;// 企业红包金额,
	private String body;// 商品名称,
	private String detail;// 商户数据包
	private String service_fee;// ,手续费
	private String service_fee_rate;// ,费率

	public String getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getEnterprise_red_packet() {
		return enterprise_red_packet;
	}

	public void setEnterprise_red_packet(String enterprise_red_packet) {
		this.enterprise_red_packet = enterprise_red_packet;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getService_fee() {
		return service_fee;
	}

	public void setService_fee(String service_fee) {
		this.service_fee = service_fee;
	}

	public String getService_fee_rate() {
		return service_fee_rate;
	}

	public void setService_fee_rate(String service_fee_rate) {
		this.service_fee_rate = service_fee_rate;
	}

}
