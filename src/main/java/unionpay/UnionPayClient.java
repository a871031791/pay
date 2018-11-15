package unionpay;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sdk.AcpService;
import sdk.SDKConfig;
import sdk.SecureUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Niu Li
 * @date 2016/10/29
 */
public class UnionPayClient {

    private HashMap<String,String> commonMap;

    private static Logger logger = LoggerFactory.getLogger(UnionPayConfig.class);
    /**
     * 初始化公共参数
     * @param commonMap 公共参数,在UnionPayConfig中配置
     */
    public UnionPayClient(HashMap<String, String> commonMap) {
        this.commonMap = commonMap;
    }

    /**
     * 开通token请求
     * @param paramMap 要传送的参数
     * @return 生成的form表单
     */
    public String tokenOpen(Map<String, String> paramMap){
        paramMap.putAll(commonMap);
        paramMap.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        paramMap.put("txnType", "79");
        paramMap.put("txnSubType", "00");
        paramMap.put("frontUrl", UnionPayConfig.FRONTURL);
        paramMap.put("backUrl", UnionPayConfig.TOKEN_BACKURL);
        paramMap.put("tokenPayData", UnionPayConfig.TOKENPAYDATA);
        Map<String, String> reqData = AcpService.sign(paramMap,UnionPayConfig.ENCODING_UTF8);  			 //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();    						 //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
        String html = AcpService.createAutoFormHtml(requestFrontUrl,reqData,UnionPayConfig.ENCODING_UTF8);     //生成自动跳转的Html表单
        logger.debug("银联token请求:"+html);
        return html;
    }

    /**
     * 请求银联发送消费短信
     * @param paramMap 要传送的参数
     * @return 请求返回的结果
     */
    public Map<String, String> msgSend(Map<String, String> paramMap){
        paramMap.putAll(commonMap);
        paramMap.put("txnType", "77");
        paramMap.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        try {
            if (paramMap.get("customerInfo") != null
                    && !StringUtils.isEmpty(paramMap.get("customerInfo"))) {
                paramMap.put("customerInfo", new String(
                        SecureUtil.base64Encode(
                                paramMap.get("customerInfo").getBytes(
                                        UnionPayConfig.ENCODING_UTF8))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> reqData = AcpService.sign(paramMap,UnionPayConfig.ENCODING_UTF8);			 //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestBackUrl = SDKConfig.getConfig().getBackRequestUrl();   								 //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
        Map<String, String> rspData = AcpService.post(reqData,requestBackUrl,UnionPayConfig.ENCODING_UTF8);
        logger.debug("银联短信请求结果:"+rspData.toString());
        return rspData;
    }

    /**
     * 消费请求
     * @param paramMap 需要传送的参数
     * @return 请求结果,消费结果返回异步通知
     */
    public Map<String,String> consume(Map<String, String> paramMap){
        paramMap.putAll(commonMap);
        paramMap.put("txnType", "01");
        paramMap.put("frontUrl", UnionPayConfig.FRONTURL);
        paramMap.put("backUrl", UnionPayConfig.CONSUME_BACKURL);
        paramMap.put("customerIp", "127.0.0.1");
        paramMap.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        try {
            if (paramMap.get("customerInfo") != null
                    && !StringUtils.isEmpty(paramMap.get("customerInfo"))) {
                paramMap.put("customerInfo", new String(
                        SecureUtil.base64Encode(
                                paramMap.get("customerInfo").getBytes(
                                        UnionPayConfig.ENCODING_UTF8))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> reqData = AcpService.sign(paramMap,UnionPayConfig.ENCODING_UTF8);				//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestBackUrl = SDKConfig.getConfig().getBackRequestUrl();   							//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
        Map<String, String> rspData = AcpService.post(reqData,requestBackUrl,UnionPayConfig.ENCODING_UTF8);	//发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
        logger.debug("银联消费请求结果:"+rspData.toString());
        return rspData;
    }

    /**
     * 交易退货类请求
     * @param paramMap 退货请求参数
     * @return 退货结果
     */
    public Map<String,String> refund(Map<String, String> paramMap){
        paramMap.putAll(commonMap);
        paramMap.put("txnType", "04");
        paramMap.put("txnSubType", "00");
        paramMap.put("frontUrl", UnionPayConfig.FRONTURL);
        paramMap.put("backUrl", UnionPayConfig.REFUND_BACKURL);
        paramMap.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        Map<String, String> reqData  = AcpService.sign(paramMap,UnionPayConfig.ENCODING_UTF8);
        String url = SDKConfig.getConfig().getBackRequestUrl();
        Map<String, String> rspData = AcpService.post(reqData, url,UnionPayConfig.ENCODING_UTF8);
        logger.debug("银行卡退款结果:"+rspData.toString());
        return rspData;
    }
    /**
     * 消费撤销请求
     * @param paramMap 消费撤销请求参数
     * @return 撤销结果
     */
    public Map<String,String> cancel(Map<String, String> paramMap){
        paramMap.putAll(commonMap);
        paramMap.put("txnType", "31");
        paramMap.put("txnSubType", "00");
        paramMap.put("backUrl", UnionPayConfig.CANCEL_BACKURL);
        paramMap.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        Map<String, String> reqData  = AcpService.sign(paramMap,UnionPayConfig.ENCODING_UTF8);
        String url = SDKConfig.getConfig().getBackRequestUrl();
        Map<String, String> rspData = AcpService.post(reqData, url,UnionPayConfig.ENCODING_UTF8);
        logger.debug("银行卡撤销结果:"+rspData.toString());
        return rspData;
    }

	/**
	 *
	 * 
	 * 重要：联调测试时请仔细阅读注释！
	 * 
	 * 产品：代收产品<br>
	 * 交易：文件传输类接口：后台获取对账文件交易，只有同步应答 <br>
	 * 日期： 2015-09<br>
	 * 版本： 1.0.0 版权： 中国银联<br>
	 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码性能规范性等方面的保障<br>
	 * 交易说明： 对账文件的格式请参考《全渠道平台接入接口规范 第3部分 文件接口》
	 * 对账文件示例见目录file下的802310048993424_20150905.zip
	 * 解析落地后的对账文件可以参考BaseDemo.java中的parseZMFile();parseZMEFile();方法
	 * 
	 * @param paramMap
	 * @return
	 */
	public File fileTransfer(Map<String, String> paramMap,String dir) {
		paramMap.putAll(commonMap);
		paramMap.put("txnType", "76");
		paramMap.put("txnSubType", "01");
		paramMap.put("orderId", System.currentTimeMillis() + "");

		/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/

		paramMap.put("bizType", "000000"); // 业务类型，固定

		/*** 商户接入参数 ***/
		paramMap.put("accessType", "0"); // 接入类型，商户接入填0，不需修改
		// paramMap.put("settleDate", settleDate);
		// //清算日期，如果使用正式商户号测试则要修改成自己想要获取对账文件的日期，
		// 测试环境如果使用700000000000001商户号则固定填写0119
		paramMap.put("txnTime", getCurrentTime()); // 订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
		paramMap.put("fileType", "00"); // 文件类型，一般商户填写00即可

		/** 请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文-------------> **/

		Map<String, String> reqData = AcpService.sign(paramMap,
				UnionPayConfig.ENCODING_UTF8); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		String url = SDKConfig.getConfig().getFileTransUrl(); // 获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.fileTransUrl
		Map<String, String> rspData = AcpService.post(reqData, url,
				UnionPayConfig.ENCODING_UTF8);

		/** 对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考-------------> **/

		// 应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录》
		if (!rspData.isEmpty()) {
			if (AcpService.validate(rspData, UnionPayConfig.ENCODING_UTF8)) {
				logger.info("验证签名成功");
				String respCode = rspData.get("respCode");
				if ("00".equals(respCode)) {
					// 交易成功，解析返回报文中的fileContent并落地
					File file=AcpService.deCodeFileContent(rspData,
							dir,
							UnionPayConfig.ENCODING_UTF8);
					return file;
				} else {
					// 其他应答码为失败请排查原因
					// TODO
				}
			} else {
				logger.error("验证签名失败");
				// TODO 检查验证签名失败的原因
			}
		} else {
			// 未返回正确的http状态
			logger.error("未获取到返回报文或返回http状态码非200");
		}
		return null;

	}
    
    /**
     * 获取当前消费时间
     * @return yyyyMMddHHmmss格式时间
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
