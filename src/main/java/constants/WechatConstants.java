package constants;

import utils.PropertiesUtils;

import java.util.Properties;

public class WechatConstants {

	public static class TradeType {
		public static final String JSAPI = "JSAPI";// --公众号支付
		public static final String NATIVE = "NATIVE";// NATIVE--原生扫码支付
		public static final String APP = "APP";// 、APP--app支付
		public static final String MICROPAY = "MICROPAY";// ，统一下单接口trade_type的传参可参考这里MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
		public static final String MWEB = "MWEB";// H5支付

	}

	public static class FeeType {
		public static final String CNY = "CNY";// --人民币
	}

	public static class BankType {
		public static final Properties ps = PropertiesUtils.loadClassPathProperty("/wechat.properties");
	}

}
