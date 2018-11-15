package wechat;

import utils.PropertiesUtils;

/**
 * 配置公共参数,配置文件路径 user.home/props/wechat-idauth.properties
 * 
 * @author Niu Li
 * @date 2016/10/29
 */
public final class WechatConfig {
	/**
	 * 用户的id
	 */
	public static final String APP_ID = PropertiesUtils.getUserDirProperty(
			"wechat_appId", "wechat-idauth.properties");
	/**
	 * 身份密钥
	 */
	public static final String APP_SECRET = PropertiesUtils.getUserDirProperty(
			"wechat_appSecret", "wechat-idauth.properties");

	public static final String OFFICIAL_APP_SECRET = PropertiesUtils
			.getUserDirProperty("wechat_official_secret",
					"wechat-idauth.properties");
	/**
	 * 商户id
	 */
	public static final String MCH_ID = PropertiesUtils.getUserDirProperty(
			"wechat_mchId", "wechat-idauth.properties");
	/**
	 * 统一下单地址
	 */
	public static final String UNIFIEDORDER_URL = PropertiesUtils
			.getUserDirProperty("wechat_unifiedorder", "wechat-idauth.properties");
	/**
	 * 交易退款地址
	 */
	public static final String REFUND_URL = PropertiesUtils.getUserDirProperty(
			"wechat_refund", "wechat-idauth.properties");
	/**
	 * 交易退款查询地址
	 */
	public static final String REFUND_QUERY = PropertiesUtils
			.getUserDirProperty("wechat_refund_query", "wechat-idauth.properties");
	/**
	 * 交易订单查询地址
	 */
	public static final String ORDER_QUERY = PropertiesUtils
			.getUserDirProperty("wechat_order_query", "wechat-idauth.properties");

	/**
	 * 交易退款查询地址
	 */
	public static final String BILL_DOWN = PropertiesUtils.getUserDirProperty(
			"wechat_bill_down", "wechat-idauth.properties");
	/**
	 * 支付成功回调
	 */
	public static final String NOTIFY_PAY = PropertiesUtils.getUserDirProperty(
			"wechat_payNotify", "wechat-idauth.properties");

	public static final String REFER_URL = PropertiesUtils.getUserDirProperty(
			"wechat_refer", "wechat-idauth.properties");
	/**
	 * 支付类型
	 */
	public static final String TRADE_TYPE = PropertiesUtils.getUserDirProperty(
			"wechat_tradeType", "wechat-idauth.properties");
	/**
	 * 证书地址
	 */
	public static final String CERT_PATH = PropertiesUtils.getUserDirProperty(
			"wechat_certPath", "wechat-idauth.properties");

	/** 子商户号 */
	public static final String SUB_MCH_ID = PropertiesUtils.getUserDirProperty(
			"wechat_subMchId", "wechat-idauth.properties");

	/** 小程序APP_ID */
	public static final String MINI_PROGRAMS_APP_ID = PropertiesUtils
			.getUserDirProperty("wechat_mini_programs_app_id",
					"wechat-idauth.properties");
	public static final String MINI_PROGRAMS_APP_SECRET = PropertiesUtils
			.getUserDirProperty("wechat_mini_programs_app_secret",
					"wechat-idauth.properties");
	/**
	 * 成功标识
	 */
	public static final String SUCCESS_REQUEST = "SUCCESS";

	/**
	 * 不可实例化
	 */
	private WechatConfig() {
	}

	private volatile static WechatClient wechatClient = null;

	/**
	 * 双重锁单例
	 * 
	 * @return WechatClient实例
	 */
	public static WechatClient getInstance() {
		if (wechatClient == null) {
			synchronized (WechatConfig.class) {
				if (wechatClient == null) {
					return new WechatClient(APP_ID, MCH_ID, APP_SECRET,
							TRADE_TYPE);
				}
			}
		}
		return wechatClient;
	}

}
