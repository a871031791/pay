package entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Niu Li
 * @date 2016/10/29
 */
@XmlRootElement(name = "xml")
public class WechatRefundQuery {
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String sub_appid;// 子公众号 APPid
	private String sub_mch_id;// 子商户号

    //四选一
    private String transaction_id;//微信订单号
    private String out_trade_no;//商户系统内部的订单号
    private String out_refund_no;//商户侧传给微信的退款单号
    private String refund_id;//微信生成的退款单号，在申请退款接口有返回

@XmlRootElement(name = "xml")
public static class Response {
    private String return_code;
    private String return_msg;
    private String result_code;
    private String err_code;//错误代码
    private String err_code_des;//错误代码描述
    private String appid;
    private String mch_id;
    private String sub_appid;// 子公众号 APPid
	private String sub_mch_id;// 子商户号
    private String device_info;//设备信息
    private String nonce_str;
    private String sign;
    private String transaction_id;//微信订单号
    private String out_trade_no;//商户系统内部的订单号
    private String total_fee;//订单金额
    private String settlement_total_fee ;//去掉非充值代金券后订单金额
    private String fee_type;
    private String cash_fee;//现金支付金额
    private String refund_count;//退款记录数
    private String refund_account;//退款资金来源
    
    private String out_refund_no_0;//商户退款单号
    private String refund_id_0;//微信退款单号
    private String refund_channel_0;//退款渠道
    private String refund_fee_0;//申请退款金额
    private String settlement_refund_fee_0;//退款金额
    
    private String coupon_type_0 ;//代金券类型
    private String coupon_refund_fee_0;
    private String coupon_refund_count_0;
    private String coupon_refund_batch_id_0_0;
    private String coupon_refund_id_0_0;
    private String coupon_refund_fee_0_0;
    private String coupon_refund_batch_id_0_1;
    private String coupon_refund_id_0_1;
    private String coupon_refund_fee_0_1;
    private String coupon_refund_batch_id_0_2;
    private String coupon_refund_id_0_2;
    private String coupon_refund_fee_0_2;
    private String refund_status_0;//退款状态SUCCESS—退款成功FAIL—退款失败PROCESSING—退款处理中CHANGE—转入代发
    private String refund_recv_accout_0;
    
    private String out_refund_no_1;//商户退款单号
    private String refund_id_1;//微信退款单号
    private String refund_channel_1;//退款渠道
    private String refund_fee_1;//申请退款金额
    private String settlement_refund_fee_1;//退款金额
    
    private String coupon_type_1 ;//代金券类型
    private String coupon_refund_fee_1;
    private String coupon_refund_count_1;
    private String coupon_refund_batch_id_1_0;
    private String coupon_refund_id_1_0;
    private String coupon_refund_fee_1_0;
    private String coupon_refund_batch_id_1_1;
    private String coupon_refund_id_1_1;
    private String coupon_refund_fee_1_1;
    private String coupon_refund_batch_id_1_2;
    private String coupon_refund_id_1_2;
    private String coupon_refund_fee_1_2;
    private String refund_status_1;//退款状态SUCCESS—退款成功FAIL—退款失败PROCESSING—退款处理中CHANGE—转入代发
    private String refund_recv_accout_1;

    public Response(String return_code) {
        this.return_code = return_code;
    }
    public Response() {
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

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
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
	public String getOut_refund_no_0() {
		return out_refund_no_0;
	}
	public void setOut_refund_no_0(String out_refund_no_0) {
		this.out_refund_no_0 = out_refund_no_0;
	}
	public String getRefund_id_0() {
		return refund_id_0;
	}
	public void setRefund_id_0(String refund_id_0) {
		this.refund_id_0 = refund_id_0;
	}
	public String getRefund_channel_0() {
		return refund_channel_0;
	}
	public void setRefund_channel_0(String refund_channel_0) {
		this.refund_channel_0 = refund_channel_0;
	}
	public String getRefund_fee_0() {
		return refund_fee_0;
	}
	public void setRefund_fee_0(String refund_fee_0) {
		this.refund_fee_0 = refund_fee_0;
	}
	public String getSettlement_refund_fee_0() {
		return settlement_refund_fee_0;
	}
	public void setSettlement_refund_fee_0(String settlement_refund_fee_0) {
		this.settlement_refund_fee_0 = settlement_refund_fee_0;
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
	public String getCoupon_refund_count_0() {
		return coupon_refund_count_0;
	}
	public void setCoupon_refund_count_0(String coupon_refund_count_0) {
		this.coupon_refund_count_0 = coupon_refund_count_0;
	}
	public String getCoupon_refund_batch_id_0_0() {
		return coupon_refund_batch_id_0_0;
	}
	public void setCoupon_refund_batch_id_0_0(String coupon_refund_batch_id_0_0) {
		this.coupon_refund_batch_id_0_0 = coupon_refund_batch_id_0_0;
	}
	public String getCoupon_refund_id_0_0() {
		return coupon_refund_id_0_0;
	}
	public void setCoupon_refund_id_0_0(String coupon_refund_id_0_0) {
		this.coupon_refund_id_0_0 = coupon_refund_id_0_0;
	}
	public String getCoupon_refund_fee_0_0() {
		return coupon_refund_fee_0_0;
	}
	public void setCoupon_refund_fee_0_0(String coupon_refund_fee_0_0) {
		this.coupon_refund_fee_0_0 = coupon_refund_fee_0_0;
	}
	public String getCoupon_refund_batch_id_0_1() {
		return coupon_refund_batch_id_0_1;
	}
	public void setCoupon_refund_batch_id_0_1(String coupon_refund_batch_id_0_1) {
		this.coupon_refund_batch_id_0_1 = coupon_refund_batch_id_0_1;
	}
	public String getCoupon_refund_id_0_1() {
		return coupon_refund_id_0_1;
	}
	public void setCoupon_refund_id_0_1(String coupon_refund_id_0_1) {
		this.coupon_refund_id_0_1 = coupon_refund_id_0_1;
	}
	public String getCoupon_refund_fee_0_1() {
		return coupon_refund_fee_0_1;
	}
	public void setCoupon_refund_fee_0_1(String coupon_refund_fee_0_1) {
		this.coupon_refund_fee_0_1 = coupon_refund_fee_0_1;
	}
	public String getCoupon_refund_batch_id_0_2() {
		return coupon_refund_batch_id_0_2;
	}
	public void setCoupon_refund_batch_id_0_2(String coupon_refund_batch_id_0_2) {
		this.coupon_refund_batch_id_0_2 = coupon_refund_batch_id_0_2;
	}
	public String getCoupon_refund_id_0_2() {
		return coupon_refund_id_0_2;
	}
	public void setCoupon_refund_id_0_2(String coupon_refund_id_0_2) {
		this.coupon_refund_id_0_2 = coupon_refund_id_0_2;
	}
	public String getCoupon_refund_fee_0_2() {
		return coupon_refund_fee_0_2;
	}
	public void setCoupon_refund_fee_0_2(String coupon_refund_fee_0_2) {
		this.coupon_refund_fee_0_2 = coupon_refund_fee_0_2;
	}
	public String getRefund_status_0() {
		return refund_status_0;
	}
	public void setRefund_status_0(String refund_status_0) {
		this.refund_status_0 = refund_status_0;
	}
	public String getRefund_recv_accout_0() {
		return refund_recv_accout_0;
	}
	public void setRefund_recv_accout_0(String refund_recv_accout_0) {
		this.refund_recv_accout_0 = refund_recv_accout_0;
	}
	public String getOut_refund_no_1() {
		return out_refund_no_1;
	}
	public void setOut_refund_no_1(String out_refund_no_1) {
		this.out_refund_no_1 = out_refund_no_1;
	}
	public String getRefund_id_1() {
		return refund_id_1;
	}
	public void setRefund_id_1(String refund_id_1) {
		this.refund_id_1 = refund_id_1;
	}
	public String getRefund_channel_1() {
		return refund_channel_1;
	}
	public void setRefund_channel_1(String refund_channel_1) {
		this.refund_channel_1 = refund_channel_1;
	}
	public String getRefund_fee_1() {
		return refund_fee_1;
	}
	public void setRefund_fee_1(String refund_fee_1) {
		this.refund_fee_1 = refund_fee_1;
	}
	public String getSettlement_refund_fee_1() {
		return settlement_refund_fee_1;
	}
	public void setSettlement_refund_fee_1(String settlement_refund_fee_1) {
		this.settlement_refund_fee_1 = settlement_refund_fee_1;
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
	public String getCoupon_refund_count_1() {
		return coupon_refund_count_1;
	}
	public void setCoupon_refund_count_1(String coupon_refund_count_1) {
		this.coupon_refund_count_1 = coupon_refund_count_1;
	}
	public String getCoupon_refund_batch_id_1_0() {
		return coupon_refund_batch_id_1_0;
	}
	public void setCoupon_refund_batch_id_1_0(String coupon_refund_batch_id_1_0) {
		this.coupon_refund_batch_id_1_0 = coupon_refund_batch_id_1_0;
	}
	public String getCoupon_refund_id_1_0() {
		return coupon_refund_id_1_0;
	}
	public void setCoupon_refund_id_1_0(String coupon_refund_id_1_0) {
		this.coupon_refund_id_1_0 = coupon_refund_id_1_0;
	}
	public String getCoupon_refund_fee_1_0() {
		return coupon_refund_fee_1_0;
	}
	public void setCoupon_refund_fee_1_0(String coupon_refund_fee_1_0) {
		this.coupon_refund_fee_1_0 = coupon_refund_fee_1_0;
	}
	public String getCoupon_refund_batch_id_1_1() {
		return coupon_refund_batch_id_1_1;
	}
	public void setCoupon_refund_batch_id_1_1(String coupon_refund_batch_id_1_1) {
		this.coupon_refund_batch_id_1_1 = coupon_refund_batch_id_1_1;
	}
	public String getCoupon_refund_id_1_1() {
		return coupon_refund_id_1_1;
	}
	public void setCoupon_refund_id_1_1(String coupon_refund_id_1_1) {
		this.coupon_refund_id_1_1 = coupon_refund_id_1_1;
	}
	public String getCoupon_refund_fee_1_1() {
		return coupon_refund_fee_1_1;
	}
	public void setCoupon_refund_fee_1_1(String coupon_refund_fee_1_1) {
		this.coupon_refund_fee_1_1 = coupon_refund_fee_1_1;
	}
	public String getCoupon_refund_batch_id_1_2() {
		return coupon_refund_batch_id_1_2;
	}
	public void setCoupon_refund_batch_id_1_2(String coupon_refund_batch_id_1_2) {
		this.coupon_refund_batch_id_1_2 = coupon_refund_batch_id_1_2;
	}
	public String getCoupon_refund_id_1_2() {
		return coupon_refund_id_1_2;
	}
	public void setCoupon_refund_id_1_2(String coupon_refund_id_1_2) {
		this.coupon_refund_id_1_2 = coupon_refund_id_1_2;
	}
	public String getCoupon_refund_fee_1_2() {
		return coupon_refund_fee_1_2;
	}
	public void setCoupon_refund_fee_1_2(String coupon_refund_fee_1_2) {
		this.coupon_refund_fee_1_2 = coupon_refund_fee_1_2;
	}
	public String getRefund_status_1() {
		return refund_status_1;
	}
	public void setRefund_status_1(String refund_status_1) {
		this.refund_status_1 = refund_status_1;
	}
	public String getRefund_recv_accout_1() {
		return refund_recv_accout_1;
	}
	public void setRefund_recv_accout_1(String refund_recv_accout_1) {
		this.refund_recv_accout_1 = refund_recv_accout_1;
	}

    
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
    
}
