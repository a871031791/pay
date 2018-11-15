package alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SignUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 支付宝交易类
 * 
 * @author Niu Li
 * @date 2016/10/29
 */
public class AlipayTrade {

	private Logger logger = LoggerFactory.getLogger(AlipayTrade.class);

	/**
	 * web支付下单并支付(web支付在安卓中是可以直接唤醒支付宝APP的) url
	 * https://doc.open.alipay.com/doc2/detail
	 * .htm?treeId=203&articleId=105463&docType=1#s1
	 * 
	 * @return web支付的表单
	 */
	public String TradeWapPayRequest(Map<String, ?> sParaTemp) {
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		Object userAgent = sParaTemp.get("userAgent");
		if (userAgent != null && "android".equals(userAgent)) {
			alipayRequest.setReturnUrl("#");
		} else {
			alipayRequest.setReturnUrl(AliPayConfig.RETURN_URL);
		}
		sParaTemp.remove("userAgent");

		alipayRequest.setNotifyUrl(AliPayConfig.PAY_NOTIFY);
		// 待请求参数数组
		alipayRequest.setBizContent(JSON.toJSONString(sParaTemp));
		String form = "";
		try {
			form = AliPayConfig.getInstance().pageExecute(alipayRequest)
					.getBody();
		} catch (AlipayApiException e) {
			logger.error("支付宝构造表单失败", e);
		}
		logger.debug("支付宝支付表单构造:" + form);
		return form;
	}

	/**
	 * 申请退款
	 * 
	 * @param sParaTemp
	 *            退款参数
	 * @return true成功,回调中处理
	 *         备注:https://doc.open.alipay.com/docs/api.htm?spm=a219a
	 *         .7629065.0.0.3RjsEZ&apiId=759&docType=4
	 */
	public boolean tradeRefundRequest(Map<String, ?> sParaTemp)
			throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		// 待请求参数数组
		request.setBizContent(JSON.toJSONString(sParaTemp));
		AlipayTradeRefundResponse response = AliPayConfig.getInstance()
				.execute(request);
		logger.debug("支付宝退货结果:" + response.isSuccess());
		return response.isSuccess();
	}

	/**
	 * 申请撤销交易
	 * 
	 * @param sParaTemp
	 *            撤销参数
	 * @return true成功
	 *         备注:https://doc.open.alipay.com/docs/api.htm?spm=a219a.7629065
	 *         .0.0.ZzmNYx&apiId=866&docType=4
	 */
	public boolean tradeCancelRequest(Map<String, ?> sParaTemp)
			throws AlipayApiException {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		// 待请求参数数组
		request.setBizContent(JSON.toJSONString(sParaTemp));
		AlipayTradeCancelResponse response = AliPayConfig.getInstance()
				.execute(request);
		logger.debug("支付宝撤销结果:" + response.isSuccess());
		return response.isSuccess();
	}

	/**
	 * 支付宝回调验签
	 * 
	 * @param request
	 *            回调请求
	 * @return true成功 备注:验签成功后，按照支付结果异步通知中的描述(二次验签接口,貌似称为历史接口了)
	 */
	public boolean verifyNotify(HttpServletRequest request)
			throws AlipayApiException {
		Map<String, String> paranMap = SignUtil.request2Map(request);
		logger.info("支付宝回调参数:" + paranMap.toString());
		// "TRADE_CLOSED".equals(paranMap.get("trade_status"),交易关闭不验签
		boolean isVerify = false;
		String trade_status = paranMap.get("trade_status");
		if (AliPayConfig.SUCCESS_REQUEST.equals(trade_status)) {

			// || AliPayConfig.CLOSE_REQUEST.equals(trade_status)
//			isVerify = AlipaySignature.rsaCheckV1(paranMap,
//					AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET); // 调用SDK验证签名
			isVerify = AlipaySignature.rsaCheckV1(paranMap,
					AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET,paranMap.get("sign_type")); // 调用SDK验证签名
		}
		logger.debug("支付宝验签结果" + isVerify);
		return isVerify;
	}
	/**
	 * 支付宝回调验签
	 *
	 *            回调请求
	 * @return true成功 备注:验签成功后，按照支付结果异步通知中的描述(二次验签接口,貌似称为历史接口了)
	 */
	public boolean verifyNotify(Map<String,String> paranMap)
			throws AlipayApiException {
//		Map<String, String> paranMap = SignUtil.request2Map(request);
		logger.info("支付宝回调参数:" + paranMap.toString());
		// "TRADE_CLOSED".equals(paranMap.get("trade_status"),交易关闭不验签
		boolean isVerify = false;
		String trade_status = paranMap.get("trade_status");
		if (AliPayConfig.SUCCESS_REQUEST.equals(trade_status)) {

			// || AliPayConfig.CLOSE_REQUEST.equals(trade_status)
			isVerify = AlipaySignature.rsaCheckV1(paranMap,
					AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET,paranMap.get("sign_type")); // 调用SDK验证签名
		}
		logger.debug("支付宝验签结果" + isVerify);
		return isVerify;
	}

	/**
	 * 支付宝回调验签
	 * 
	 * @param request
	 *            回调请求
	 * @return true成功 备注:验签成功后，按照支付结果异步通知中的描述(二次验签接口,貌似称为历史接口了)
	 */
	public boolean verifyNotifyTradeClose(HttpServletRequest request)
			throws AlipayApiException {
		Map<String, String> paranMap = SignUtil.request2Map(request);
		logger.info("支付宝回调参数:" + paranMap.toString());
		// "TRADE_CLOSED".equals(paranMap.get("trade_status"),交易关闭不验签
		boolean isVerify = false;
		String trade_status = paranMap.get("trade_status");
		if (AliPayConfig.CLOSE_REQUEST.equals(trade_status)) {

			// || AliPayConfig.CLOSE_REQUEST.equals(trade_status)
//			isVerify = AlipaySignature.rsaCheckV1(paranMap,
//					AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET); // 调用SDK验证签名
			isVerify = AlipaySignature.rsaCheckV1(paranMap,
					AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET,paranMap.get("sign_type")); // 调用SDK验证签名
		}
		logger.debug("支付宝验签结果" + isVerify);
		return isVerify;
	}

	/**
	 * @param
	 * @return
	 * @throws AlipayApiException
	 *             https://doc.open.alipay.com/doc2/apiDetail.htm?apiId=1054&
	 *             docType=4
	 */
	public String tradeDownloadBillRequest(String bill_date)
			throws AlipayApiException {
		// signcustomer
		// trade
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
		JSONObject bizContent = new JSONObject();
		bizContent.put("bill_type", "signcustomer");
		bizContent.put("bill_date", bill_date);

		request.setBizContent(bizContent.toJSONString());
		AlipayDataDataserviceBillDownloadurlQueryResponse response = AliPayConfig
				.getInstance().execute(request);
		System.out.println(response.getBillDownloadUrl());
		String url = response.getBillDownloadUrl();
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");

		}
		return url;
	}

	public String tradeQuery(String out_trade_no, String trade_no)
			throws AlipayApiException {

		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

		JSONObject bizContent = new JSONObject();
		bizContent.put("out_trade_no", "148765850372867152647815758389");
		// bizContent.put("trade_no", "2017022121001004930218685113");

		request.setBizContent(bizContent.toJSONString());
		AlipayTradeQueryResponse response = AliPayConfig.getInstance().execute(
				request);
		System.out.println(JSONObject.toJSONString(response));

		if (response.isSuccess()) {
			System.out.println("调用成功");
			return response.getSubMsg();
		} else {
			System.out.println("调用失败");
		}
		return response.getCode();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws AlipayApiException {
		// Map<String, Object> sParaTemp = new HashMap<String, Object>();
		// sParaTemp.put("method",
		// "alipay.data.dataservice.bill.downloadurl.query");
		AlipayTrade trade = new AlipayTrade();
		System.out.println(trade.tradeQuery("", ""));
		// trade.tradeDownloadBillRequest("2017-04-17");
	}

}
