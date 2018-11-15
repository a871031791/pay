package unionpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sdk.AcpService;
import utils.SignUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 银联交易入口,只为了和支付宝,微信调用统一
 * @author Niu Li
 * @date 2016/10/31
 */
public class UnionPayTrade {

    private static Logger logger = LoggerFactory.getLogger(UnionPayTrade.class);

    /**
     * 开通token支付
     * @param paramMap 请求参数
     * @return 请求返回结果
     * 我们是商户侧
     * https://open.unionpay.com/ajweb/help/file/toDetailPage?id=500&flag=2
     */
    public String tokenOpen(Map<String, String> paramMap){
        return UnionPayConfig.getInstance().tokenOpen(paramMap);
    }

    /**
     * 发送短信
     * @param paramMap 请求参数
     * @return 返回结果
     */
    public Map<String,String> msg(Map<String, String> paramMap){
        return UnionPayConfig.getInstance().msgSend(paramMap);
    }

    /**
     * 消费
     * @param paramMap 请求参数
     * @return 请求结果
     */
    public Map<String,String> consume(Map<String, String> paramMap){
        return  UnionPayConfig.getInstance().consume(paramMap);
    }

    /**
     * 退款
     * @param paramMap 请求参数
     * @return 请求结果
     */
    public Map<String,String> refund(Map<String, String> paramMap){
        return  UnionPayConfig.getInstance().refund(paramMap);
    }
    /**
     * 交易撤销请求
     * @param paramMap 请求参数
     * @return 请求结果
     */
    public Map<String,String> cancel(Map<String, String> paramMap){
        return  UnionPayConfig.getInstance().cancel(paramMap);
    }

    /**
     * 银行卡回调验签
     * @param request  回调请求
     * @return true成功
     */
    public boolean verifyNotify(HttpServletRequest request){
        Map<String,String> paranMap = SignUtil.request2Map(request);
        logger.debug("银行卡回调参数:"+paranMap.toString());
        boolean ischeck = false;
        if (paranMap.get("respCode").equals(UnionPayConfig.SUCCESS)){
            ischeck = AcpService.validate(paranMap, UnionPayConfig.ENCODING_UTF8);
        }
        logger.debug("银行卡回调验签结果:"+ischeck);
        return ischeck;
    }
}
