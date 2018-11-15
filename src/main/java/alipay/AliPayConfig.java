package alipay;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import utils.PropertiesUtils;


/**
 * 配置公共参数,配置文件路径 user.home/props/alipay_idauth.properties
 * @author Niu Li
 * @date 2016/10/29
 */
public final class AliPayConfig {
    /**
     * 应用号
     */
    public static String APP_ID = PropertiesUtils.getUserDirProperty("alipay_appid","alipay_idauth.properties");
    /**
     * 商户的私钥
     */
    public static String APP_PRIVATE_KEY = PropertiesUtils.getUserDirProperty("alipay_privateKey","alipay_idauth.properties");
    /**
     * 编码
     */
    public static String CHARSET = PropertiesUtils.getUserDirProperty("alipay_charset","alipay_idauth.properties");
    /**
     * 支付宝公钥
     */
    public static String ALIPAY_PUBLIC_KEY = PropertiesUtils.getUserDirProperty("alipay_publicKey","alipay_idauth.properties");
    /**
     * 支付宝网关地址
     */
    private static String GATEWAY = PropertiesUtils.getUserDirProperty("alipay_gateway","alipay_idauth.properties");
    /**
     * 成功付款回调
     */
    public static String PAY_NOTIFY = PropertiesUtils.getUserDirProperty("alipay_payNotify","alipay_idauth.properties");
    /**
     * 前台通知地址
     */
    public static String RETURN_URL = PropertiesUtils.getUserDirProperty("alipay_returnUrl","alipay_idauth.properties");
    /**
     * 支付宝收款账号
     */
    public static String SELLER_ID = PropertiesUtils.getUserDirProperty("alipay_seller_id","alipay_idauth.properties");
    /**
     * 支付宝收款账号
     */
    public static String TIMEOUT_EXPRESS = PropertiesUtils.getUserDirProperty("alipay_timeout_express","alipay_idauth.properties");
    /**
     * 参数类型
     */
    public static String PARAM_TYPE = "json";
    /**
     * 参数类型
     */
    public static String SIGN_TYPE = "RSA2";
    /**
     * 成功标识
     */
    public static final String SUCCESS_REQUEST = "TRADE_SUCCESS";
    /**
     * 交易关闭
     */
    public static final String CLOSE_REQUEST = "TRADE_CLOSED";
    /**
     * 支付宝请求客户端入口
     */
    private volatile static AlipayClient alipayClient = null;

    /**
     * 不可实例化
     */
    private AliPayConfig(){};

    /**
     * 双重锁单例
     * @return 支付宝请求客户端实例
     */
    public static AlipayClient getInstance(){
        if (alipayClient == null){
            synchronized (AliPayConfig.class){
                if (alipayClient == null){
                    alipayClient = new DefaultAlipayClient(GATEWAY,APP_ID,APP_PRIVATE_KEY,PARAM_TYPE,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
                   
                }
            }
        }
        return alipayClient;
    }

}
