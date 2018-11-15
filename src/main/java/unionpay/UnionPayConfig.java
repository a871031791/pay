package unionpay;

import sdk.SDKConfig;
import utils.PropertiesUtils;

import java.util.HashMap;

/**
 * 配置公共参数,配置文件路径 user.home/props/union.properties
 * @author Niu Li
 * @date 2016/10/29
 */
public final class UnionPayConfig {

    //默认配置的是UTF-8
    public static String ENCODING_UTF8 = PropertiesUtils.getUserDirProperty("union_encoding","union.properties");

    //全渠道固定值
    public static String VERSION = PropertiesUtils.getUserDirProperty("union_version","union.properties");

    //前台通知地址
    public static String FRONTURL = PropertiesUtils.getUserDirProperty("union_fontUrl","union.properties");

    /**
     * token通知地址
     */
    public static String TOKEN_BACKURL = PropertiesUtils.getUserDirProperty("union_tokenBackUrl","union.properties");
    /**
     * 消费通知地址
     */
    public static String CONSUME_BACKURL = PropertiesUtils.getUserDirProperty("union_consumeBackUrl","union.properties");
    /**
     * 退款通知地址
     */
    public static String REFUND_BACKURL = PropertiesUtils.getUserDirProperty("union_refundBackUrl","union.properties");
    /**
     * 撤销通知接口
     */
    public static String CANCEL_BACKURL = PropertiesUtils.getUserDirProperty("union_cancelBackUrl","union.properties");
    /**
     * 签名方法 01 RSA
     */
    public static String SIGNMETHOD = PropertiesUtils.getUserDirProperty("union_SignMethod","union.properties");
    /**
     * 业务类型 token支付
     */
    public static String BIZTYPE = PropertiesUtils.getUserDirProperty("union_bizType","union.properties");
    /**
     * 交易通道互联网
     */
    public static String CHANNELTYPE = "07";
    /**
     * 商户号
     */
    public static String MERID = PropertiesUtils.getUserDirProperty("union_merId","union.properties");
    /**
     * 商户接入类型
     */
    public static String ACCESSTYPE = PropertiesUtils.getUserDirProperty("union_accessType","union.properties");
    /**
     * 账号类型
     */
    public static String ACCTYPE = PropertiesUtils.getUserDirProperty("union_accType","union.properties");
    /**
     *标记化支付信息域
     */
    public static String TOKENPAYDATA = PropertiesUtils.getUserDirProperty("union_tokenPayData","union.properties");
    /**
     * SDK位置
     */
    public static String SDKCONFIGPATH = PropertiesUtils.getUserDirProperty("union_sdkpath","union.properties");

    /**
     * 成功标识
     */
    public static String SUCCESS = "00";

    /**
     * 不可实例化
     */
    private UnionPayConfig(){}

    private volatile static UnionPayClient unionPayClient = null;

    /**
     * 加载银联证书
     */
    static {
        SDKConfig.getConfig().loadPropertiesFromPath(SDKCONFIGPATH);
    }

    /**
     * 双重检验单例
     * @return 银联pay客户端
     */
    public static UnionPayClient getInstance(){
        if (unionPayClient == null){
            synchronized (UnionPayConfig.class){
                if (unionPayClient == null){
                    HashMap<String,String> initMap = new HashMap<>();
                    initMap.put("version", VERSION);                   //版本号
                    initMap.put("encoding", ENCODING_UTF8);            //字符集编码 可以使用UTF-8,GBK两种方式
                    initMap.put("signMethod", SIGNMETHOD);                           //签名方法 目前只支持01-RSA方式证书加密
                    initMap.put("bizType", BIZTYPE);                          //业务类型 token支付
                    initMap.put("channelType", CHANNELTYPE);
                    initMap.put("merId", MERID);                   			   //商户号码（本商户号码仅做为测试调通交易使用，该商户号配置了需要对敏感信息加密）测试时请改成自己申请的商户号，【自己注册的测试777开头的商户号不支持代收产品】
                    initMap.put("accessType", ACCESSTYPE);
                    initMap.put("accType", ACCTYPE);
                    return new UnionPayClient(initMap);
                }
            }
        }
        return unionPayClient;
    }

}
