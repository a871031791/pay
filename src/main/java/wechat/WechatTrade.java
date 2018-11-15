package wechat;

import com.alibaba.fastjson.JSON;
import entity.WechatPayNotify;
import entity.WechatRefund;
import entity.WechatRefundQuery;
import entity.WechatUnifiedOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SignUtil;
import utils.XmlUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

/**
 * 微信交易
 * 
 * @author Niu Li
 * @date 2016/10/29
 */
public class WechatTrade {

	private static Logger logger = LoggerFactory.getLogger(WechatTrade.class);

	/**
	 * 微信统一下单
	 * 
	 * @param unifiedOrder
	 *            要下单的内容
	 * @return 返回H5下单请求需要内容
	 *         备注:https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
	 */
	public TreeMap<String, String> unifiedOrderRequest(WechatUnifiedOrder unifiedOrder, WechatClient wechatClient) {
		WechatUnifiedOrder.Response response = wechatClient.unifiedOrder(unifiedOrder);
		if (response != null && response.getResult_code().equals(WechatConfig.SUCCESS_REQUEST)) {
			TreeMap<String, String> prepareH5Pay = new TreeMap<String, String>();
			prepareH5Pay.put("appid", WechatConfig.APP_ID);
			prepareH5Pay.put("partnerid", WechatConfig.MCH_ID);
			prepareH5Pay.put("noncestr", WechatConfig.getInstance().nonce_str(16));
			prepareH5Pay.put("package", "Sign=WXPay");
			prepareH5Pay.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
			prepareH5Pay.put("prepayid", response.getPrepay_id());
			prepareH5Pay.put("sign", WechatConfig.getInstance().sign(prepareH5Pay));
			prepareH5Pay.put("mweb_url", response.getMweb_url());
			prepareH5Pay.put("code_url", response.getCode_url());
			logger.debug("微信返回H5请求:" + prepareH5Pay);
			return prepareH5Pay;
		}
		return null;
	}

	/**
	 * 微信退款请求
	 * 
	 * @param refund
	 *            退款请求参数
	 * @return 返回参数(同步接口,直接返回),只有return_code和result_code都成功则退款成功
	 *         备注:https://pay.weixin
	 *         .qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=6
	 */
	public WechatRefund.Response refundRequest(WechatRefund refund, WechatClient wechatClient) {
		WechatRefund.Response response = wechatClient.refund(refund);
		return response;
	}

	/**
	 * 微信退款查询请求
	 * 
	 * @param refund
	 *            退款请求参数
	 * @return 返回参数(同步接口,直接返回),只有return_code和result_code都成功则查询成功
	 *         备注:https://pay.weixin
	 *         .qq.com/wiki/doc/api/app/app.php?chapter=9_5&index=7
	 */
	public WechatRefundQuery.Response refundQueryRequest(WechatRefundQuery refund, WechatClient wechatClient) {
		WechatRefundQuery.Response response = wechatClient.refundQuery(refund);
		return response;
	}

	/**
	 * 微信回调验签
	 * 
	 * @param request
	 *            回调请求
	 * @return true成功
	 */
	public boolean verifyNotify(HttpServletRequest request) {
		try {
			InputStream inputStream = request.getInputStream();
			WechatPayNotify notice = XmlUtil.xmlToBean(inputStream, WechatPayNotify.class);
			return this.verifyNotify(notice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 微信回调验签
	 * 
	 * @param notice
	 *            要验签的请求转换的实体
	 * @return true成功
	 */
	public boolean verifyNotify(WechatPayNotify notice) {
		if (notice == null)
			return false;
		logger.debug("微信回调参数:" + JSON.toJSONString(notice));
		// 交易成功则验签result_code
		if (notice.getResult_code().equals(WechatConfig.SUCCESS_REQUEST)) {
			String sign = WechatConfig.getInstance().sign(SignUtil.bean2TreeMap(notice)).toUpperCase();
			boolean ischeck = sign.equals(notice.getSign());
			logger.debug("微信验签结果:" + ischeck);
			return ischeck;
		}
		return false;
	}

}
