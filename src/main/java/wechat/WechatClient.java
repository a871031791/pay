package wechat;

import entity.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SignUtil;
import utils.WebUtils;
import utils.XmlUtil;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微信交易客户端
 * 
 * @author Niu Li
 * @date 2016/10/29
 */
public class WechatClient {
	private String app_id;
	private String mch_id;
	private String app_secret;
	private String trade_type;

	private static Logger logger = LoggerFactory.getLogger(WechatClient.class);

	public WechatClient(String app_id, String mch_id, String app_secret,
			String trade_type) {
		this.app_id = app_id;
		this.mch_id = mch_id;
		this.app_secret = app_secret;
		this.trade_type = trade_type;
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



	public String getApp_secret() {
		return app_secret;
	}



	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}



	public String getTrade_type() {
		return trade_type;
	}



	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}



	/**
	 * 统一下单,会自动签名和补上noce_str
	 * 
	 * @return 下单后返回信息
	 */
	public WechatUnifiedOrder.Response unifiedOrder(
			WechatUnifiedOrder unifiedOrder) {
		unifiedOrder.setAppid(this.app_id);
		unifiedOrder.setMch_id(this.mch_id);
		unifiedOrder.setTrade_type(this.trade_type);
		unifiedOrder.setNotify_url(WechatConfig.NOTIFY_PAY);
		unifiedOrder.setNonce_str(nonce_str(16));
		unifiedOrder.setSign(sign(SignUtil.bean2TreeMap(unifiedOrder))
				.toUpperCase());
		String tempXmlStr = XmlUtil.beanToXml(new ByteArrayOutputStream(),
				unifiedOrder);
		String requestXml = tempXmlStr != null ? tempXmlStr.substring(55) : "";
		logger.debug("xml转义后内容:" + requestXml);
		try {
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Referer", WechatConfig.REFER_URL);
			InputStreamEntity inputStreamEntity = new InputStreamEntity(
					new ByteArrayInputStream(requestXml.getBytes("UTF-8")),
					ContentType.APPLICATION_XML);
			String resultXml = WebUtils.post(WechatConfig.UNIFIEDORDER_URL,
					inputStreamEntity, "UTF-8", headerMap);
			logger.debug("微信返回内容:" + resultXml);
			return resultXml != null ? XmlUtil.xmlToBean(resultXml,
					WechatUnifiedOrder.Response.class)
					: new WechatUnifiedOrder.Response("FAIL");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new WechatUnifiedOrder.Response("FAIL");
	}

	/**
	 * 微信退货请求
	 * 
	 * @param wechatRefund
	 *            退货请求参数
	 * @return 请求返回内容
	 */
	public WechatRefund.Response refund(WechatRefund wechatRefund) {
		wechatRefund.setAppid(this.app_id);
		wechatRefund.setMch_id(this.mch_id);
		wechatRefund.setNonce_str(nonce_str(16));
		wechatRefund.setSign(sign(SignUtil.bean2TreeMap(wechatRefund))
				.toUpperCase());
		String tempXmlStr = XmlUtil.beanToXml(new ByteArrayOutputStream(),
				wechatRefund);
		String requestXml = tempXmlStr != null ? tempXmlStr.substring(55) : "";
		try {
			InputStreamEntity inputStreamEntity = new InputStreamEntity(
					new ByteArrayInputStream(requestXml.getBytes("UTF-8")),
					ContentType.APPLICATION_XML);
			String resultXml = WebUtils.post(WechatConfig.CERT_PATH,
					WechatConfig.MCH_ID, WechatConfig.REFUND_URL,
					inputStreamEntity, "UTF-8");
			logger.debug("微信退货返回内容:" + resultXml);
			return resultXml != null ? XmlUtil.xmlToBean(resultXml,
					WechatRefund.Response.class) : new WechatRefund.Response(
					"FAIL");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new WechatRefund.Response("FAIL");
	}

	/**
	 * 微信退款查询
	 * 
	 * @param wechatRefundQuery
	 *            要查询的请求
	 * @return 查询结果
	 */
	public WechatRefundQuery.Response refundQuery(
			WechatRefundQuery wechatRefundQuery) {
		wechatRefundQuery.setAppid(this.app_id);
		wechatRefundQuery.setMch_id(this.mch_id);
		wechatRefundQuery.setNonce_str(nonce_str(16));
		wechatRefundQuery
				.setSign(sign(SignUtil.bean2TreeMap(wechatRefundQuery))
						.toUpperCase());
		String tempXmlStr = XmlUtil.beanToXml(new ByteArrayOutputStream(),
				wechatRefundQuery);
		String requestXml = tempXmlStr != null ? tempXmlStr.substring(55) : "";
		try {
			InputStreamEntity inputStreamEntity = new InputStreamEntity(
					new ByteArrayInputStream(requestXml.getBytes("UTF-8")),
					ContentType.APPLICATION_XML);
			String resultXml = WebUtils.post(WechatConfig.REFUND_QUERY,
					inputStreamEntity, "UTF-8");
			logger.debug("微信退货查询内容:" + resultXml);
			return resultXml != null ? XmlUtil.xmlToBean(resultXml,
					WechatRefundQuery.Response.class)
					: new WechatRefundQuery.Response("FAIL");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new WechatRefundQuery.Response("FAIL");
	}

	/**
	 * 微信退款查询
	 * 
	 * @param wechatRefundQuery
	 *            要查询的请求
	 * @return 查询结果
	 */
	public WechatOrderQuery.Response orderQuery(
			WechatOrderQuery wechatRefundQuery) {
		wechatRefundQuery.setAppid(this.app_id);
		wechatRefundQuery.setMch_id(this.mch_id);
		wechatRefundQuery.setNonce_str(nonce_str(16));
		wechatRefundQuery
				.setSign(sign(SignUtil.bean2TreeMap(wechatRefundQuery))
						.toUpperCase());
		String tempXmlStr = XmlUtil.beanToXml(new ByteArrayOutputStream(),
				wechatRefundQuery);
		String requestXml = tempXmlStr != null ? tempXmlStr.substring(55) : "";
		try {
			InputStreamEntity inputStreamEntity = new InputStreamEntity(
					new ByteArrayInputStream(requestXml.getBytes("UTF-8")),
					ContentType.APPLICATION_XML);
			String resultXml = WebUtils.post(WechatConfig.ORDER_QUERY,
					inputStreamEntity, "UTF-8");
			logger.debug("微信订单查询内容:" + resultXml);
			return resultXml != null ? XmlUtil.xmlToBean(resultXml,
					WechatOrderQuery.Response.class)
					: new WechatOrderQuery.Response("FAIL");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new WechatOrderQuery.Response("FAIL");
	}

	/**
	 * 对请求进行签名
	 * 
	 * @param param
	 *            要签名的参数
	 * @return
	 */
	public String sign(TreeMap<String, ?> param) {
		String paramUrl = SignUtil.joinKeyValue(new TreeMap<String, Object>(
				param), null, "&key=" + this.app_secret, "&", true,
				"sign_type", "sign");

		try {
			logger.debug("微信待签名串:" + paramUrl);
			MessageDigest digestUtils = DigestUtils.getMd5Digest();
			digestUtils.update(paramUrl.getBytes("UTF-8"));
			byte[] sign = digestUtils.digest();
			String result = Hex.encodeHexString(sign);
			logger.debug("签名结果:" + result);
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 微信nonce_str生成算法
	 * 
	 * @param bits
	 *            生成位数,选择64bit
	 * @return 生成后的nonce_str
	 */
	public String nonce_str(int bits) {
		final byte[] bytes;
		try {
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			if ((bits % 8) != 0) {
				throw new IllegalArgumentException(
						"Size is not divisible by 8!");
			}
			bytes = new byte[bits / 8];
			secureRandom.nextBytes(bytes);
			return Hex.encodeHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis() + "";
	}

	public void billDown(WechatBillDown wechatBillDown) {
		wechatBillDown.setAppid(this.app_id);
		wechatBillDown.setMch_id(this.mch_id);
		// wechatBillDown.setSub_mch_id(WechatConfig.SUB_MCH_ID);
		wechatBillDown.setNonce_str(nonce_str(16));
		wechatBillDown.setSign(sign(SignUtil.bean2TreeMap(wechatBillDown))
				.toUpperCase());
		String tempXmlStr = XmlUtil.beanToXml(new ByteArrayOutputStream(),
				wechatBillDown);
		String requestXml = tempXmlStr != null ? tempXmlStr.substring(55) : "";
		try {
			InputStreamEntity inputStreamEntity = new InputStreamEntity(
					new ByteArrayInputStream(requestXml.getBytes("UTF-8")),
					ContentType.APPLICATION_XML);
			InputStream inputStream = WebUtils.postAndGetInput(
					WechatConfig.BILL_DOWN, inputStreamEntity, "UTF-8");
			FileOutputStream fos = new FileOutputStream(wechatBillDown
					.getBill_date().concat(wechatBillDown.getNonce_str())
					.concat(".gzip"));
			byte[] bs = new byte[1024];
			int rs = 0;
			while ((rs = inputStream.read(bs)) != -1) {
				fos.write(bs, 0, rs);
				fos.flush();
			}
			fos.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return new WechatBillDown.Response("FAIL");
	}
}
