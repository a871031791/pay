package utils;


import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Utils - Web
 */
public final class WebUtils {

    private static Logger logger = LoggerFactory.getLogger(WebUtils.class);

    /** PoolingHttpClientConnectionManager */
    private static final PoolingHttpClientConnectionManager HTTP_CLIENT_CONNECTION_MANAGER;

    /** CloseableHttpClient */
    private static final CloseableHttpClient HTTP_CLIENT;

    static {
        HTTP_CLIENT_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(RegistryBuilder
                .<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory()).build());
        HTTP_CLIENT_CONNECTION_MANAGER.setDefaultMaxPerRoute(100);
        HTTP_CLIENT_CONNECTION_MANAGER.setMaxTotal(200);
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000)
                .setSocketTimeout(60000).build();
        HTTP_CLIENT = HttpClientBuilder.create().setConnectionManager(HTTP_CLIENT_CONNECTION_MANAGER)
                .setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * 不可实例化
     */
    private WebUtils() {
    }

    /**
     * POST请求
     *
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     */
    public static String post(String url, Map<String, ?> parameterMap, String encoding) {
        String result = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null && !parameterMap.isEmpty()) {
                for (Map.Entry<String, ?> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = String.valueOf(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpPost);
            result = consumeResponse(httpResponse, encoding);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * post请求,请求体为json串
     *
     * @param url
     *            请求连接
     * @param json
     *            json串
     * @param encording
     *            编码
     * @return 结果
     */
    public static String post(String url, String json, String encording) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            entity.setContentEncoding(encording);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = consumeResponse(response, encording);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * POST请求
     *
     * @param url
     *            URL
     * @param inputStreamEntity
     *            请求体
     * @return 返回结果
     */
    public static String post(String url, InputStreamEntity inputStreamEntity, String encording) {

        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);

            inputStreamEntity.setContentEncoding(encording);
            httpPost.setEntity(inputStreamEntity);
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpPost);
            result = consumeResponse(httpResponse, encording);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * POST请求
     *
     * @param url
     *            URL
     * @param inputStreamEntity
     *            请求体
     * @return 返回结果
     */
    public static String post(String url, InputStreamEntity inputStreamEntity, String encording,
                              Map<String, String> headerMap) {

        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (headerMap != null && !headerMap.isEmpty()) {
                for (Iterator<Map.Entry<String, String>> its = headerMap.entrySet().iterator(); its.hasNext();) {
                    Map.Entry<String, String> entry = its.next();
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            inputStreamEntity.setContentEncoding(encording);
            httpPost.setEntity(inputStreamEntity);
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpPost);
            result = consumeResponse(httpResponse, encording);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * GET请求
     *
     * @param url
     *            URL
     * @param parameterMap
     *            请求参数
     * @return 返回结果
     */
    public static String get(String url, Map<String, Object> parameterMap) {

        logger.debug(String.format("httpclient get url ==> %s", url));
        String result = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = String.valueOf(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?")
                    + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpGet);
            result = consumeResponse(httpResponse, "UTF-8");
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 利用证书请求微信
     *
     * @param certPath
     *            证书路径
     * @param passwd
     *            证书密码
     * @param uri
     *            请求地址
     * @param entity
     *            请求体xml内容
     * @return 得到的结果
     */
    public static String post(String certPath, String passwd, String uri, InputStreamEntity entity, String encording)
            throws Exception {
        String result = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(certPath));
        try {
            keyStore.load(instream, passwd.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, passwd.toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpPost = new HttpPost(uri);
            entity.setContentEncoding(encording);
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
            result = consumeResponse(httpResponse, encording);
        } finally {
            httpclient.close();
        }
        return result;
    }

    /**
     * 处理返回的请求,拿到返回内容
     *
     * @param httpResponse
     *            要处理的返回
     * @return 返回的内容
     */
    private static String consumeResponse(CloseableHttpResponse httpResponse, String encording) {
        String result = null;
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, encording);
                EntityUtils.consume(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpResponse.close();
            } catch (IOException ignored) {
            }
        }
        return result;
    }

    public static InputStream postGetIs(String certPath, String passwd, String uri, InputStreamEntity entity,
                                        String encording) throws Exception {
        InputStream is = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(certPath));
        try {
            keyStore.load(instream, passwd.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, passwd.toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpPost = new HttpPost(uri);
            entity.setContentEncoding(encording);
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } finally {
        }
        return is;
    }

    public static InputStream postAndGetInput(String uri, InputStreamEntity entity, String encording) throws Exception {
        InputStream is = null;

        try {

            HttpPost httpPost = new HttpPost(uri);
            entity.setContentEncoding(encording);
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = HTTP_CLIENT.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {

        }
        return is;
    }

}
