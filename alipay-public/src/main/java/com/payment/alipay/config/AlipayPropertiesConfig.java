package com.payment.alipay.config;

import com.payment.alipay.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;


/**
 * 支付宝账户配置部分
 */
@Slf4j
public class AlipayPropertiesConfig extends AbstractPropertiesConfig {


    private static String openApiDomain;   // 支付宝openapi域名
    private static String mcloudApiDomain;  // 支付宝mcloudmonitor域名
    private static String pid;             // 商户partner id
    private static String appid;           // 商户应用id

    private static String privateKey;      // RSA私钥，用于对商户请求报文加签
    private static String publicKey;       // RSA公钥，仅用于验证开发者网关
    private static String alipayPublicKey; // 支付宝RSA公钥，用于验签支付宝应答
    private static String signType;     // 签名类型  

    private static String NOTIFY_URL;     // 支付通知地址
    private static String STATEMENT_FILE_PATH;     // 对账单存放地址

    private static int maxQueryRetry;   // 最大查询次数
    private static long queryDuration;  // 查询间隔（毫秒）

    private static int maxCancelRetry;  // 最大撤销次数
    private static long cancelDuration; // 撤销间隔（毫秒）

    private static long heartbeatDelay; // 交易保障线程第一次调度延迟（秒）
    private static long heartbeatDuration; // 交易保障线程调度间隔（秒）

    private AlipayPropertiesConfig() {
        getProperties("alipay.properties");
    }

    public AlipayPropertiesConfig(String filePath) {
        getFileProperties(filePath, ConstantUtil.UTF8);
    }


    /**
     * 读取项目配置文件参数
     * <p>
     * 采用ISO-8859-1默认字符集
     *
     * @param fileName
     * @author Yujinshui
     */
    private void getProperties(String fileName) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("初始化微信支付过程中,读取配置文件失败,配置文件[classpath:" + fileName + "]，请检查.", e);
        }
        setPropertiesValues(p);
    }


    @Override
    protected void setPropertiesValues(Properties p) {
        openApiDomain = p.getProperty("open_api_domain");
        mcloudApiDomain = p.getProperty("mcloud_api_domain");

        pid = p.getProperty("pid");
        appid = p.getProperty("appid");

        // RSA
        privateKey = p.getProperty("private_key");
        publicKey = p.getProperty("public_key");
        alipayPublicKey = p.getProperty("alipay_public_key");
        signType = p.getProperty("sign_type");

        // 查询参数
        maxQueryRetry = Integer.valueOf(p.getProperty("max_query_retry"));
        queryDuration = Long.valueOf(p.getProperty("query_duration"));
        maxCancelRetry = Integer.valueOf(p.getProperty("max_cancel_retry"));
        cancelDuration = Long.valueOf(p.getProperty("cancel_duration"));

        // 交易保障调度线程
        heartbeatDelay = Long.valueOf(p.getProperty("heartbeat_delay"));
        heartbeatDuration = Long.valueOf(p.getProperty("heartbeat_duration"));
        NOTIFY_URL = p.getProperty("NOTIFY_URL");
        STATEMENT_FILE_PATH = p.getProperty("STATEMENT_FILE_PATH");
        log.info(description());
    }

    @Override
    protected String exceptionString(String fileName) {
        return null;
    }

    public static String description() {
        StringBuilder sb = new StringBuilder("AlipayPropertiesConfig{");
        sb.append("支付宝openapi网关: ").append(openApiDomain).append("\n");
        if (StringUtils.isNotEmpty(mcloudApiDomain)) {
            sb.append(", 支付宝mcloudapi网关域名: ").append(mcloudApiDomain).append("\n");
        }
        if (StringUtils.isNotEmpty(NOTIFY_URL)) {
            sb.append(", 支付宝支付通知地址: ").append(NOTIFY_URL).append("\n");
        }
        if (StringUtils.isNotEmpty(STATEMENT_FILE_PATH)) {
            sb.append(", 支付宝对账单地址: ").append(STATEMENT_FILE_PATH).append("\n");
        }

        if (StringUtils.isNotEmpty(pid)) {
            sb.append(", pid: ").append(pid).append("\n");
        }
        sb.append(", appid: ").append(appid).append("\n");

        sb.append(", 商户RSA私钥: ").append(getKeyDescription(privateKey)).append("\n");
        sb.append(", 商户RSA公钥: ").append(getKeyDescription(publicKey)).append("\n");
        sb.append(", 支付宝RSA公钥: ").append(getKeyDescription(alipayPublicKey)).append("\n");
        sb.append(", 签名类型: ").append(signType).append("\n");

        sb.append(", 查询重试次数: ").append(maxQueryRetry).append("\n");
        sb.append(", 查询间隔(毫秒): ").append(queryDuration).append("\n");
        sb.append(", 撤销尝试次数: ").append(maxCancelRetry).append("\n");
        sb.append(", 撤销重试间隔(毫秒): ").append(cancelDuration).append("\n");

        sb.append(", 交易保障调度延迟(秒): ").append(heartbeatDelay).append("\n");
        sb.append(", 交易保障调度间隔(秒): ").append(heartbeatDuration).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private static String getKeyDescription(String key) {
        int showLength = 6;
        if (StringUtils.isNotEmpty(key) &&
                key.length() > showLength) {
            return new StringBuilder(key.substring(0, showLength))
                    .append("******")
                    .append(key.substring(key.length() - showLength))
                    .toString();
        }
        return null;
    }


    public static String getOpenApiDomain() {
        return openApiDomain;
    }

    public static String getMcloudApiDomain() {
        return mcloudApiDomain;
    }

    public static void setMcloudApiDomain(String mcloudApiDomain) {
        AlipayPropertiesConfig.mcloudApiDomain = mcloudApiDomain;
    }

    public static String getPid() {
        return pid;
    }

    public static String getAppid() {
        return appid;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public static String getSignType() {
        return signType;
    }

    public static int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public static long getQueryDuration() {
        return queryDuration;
    }

    public static int getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public static long getCancelDuration() {
        return cancelDuration;
    }

    public static void setOpenApiDomain(String openApiDomain) {
        AlipayPropertiesConfig.openApiDomain = openApiDomain;
    }

    public static void setPid(String pid) {
        AlipayPropertiesConfig.pid = pid;
    }

    public static void setAppid(String appid) {
        AlipayPropertiesConfig.appid = appid;
    }

    public static void setPrivateKey(String privateKey) {
        AlipayPropertiesConfig.privateKey = privateKey;
    }

    public static void setPublicKey(String publicKey) {
        AlipayPropertiesConfig.publicKey = publicKey;
    }

    public static void setAlipayPublicKey(String alipayPublicKey) {
        AlipayPropertiesConfig.alipayPublicKey = alipayPublicKey;
    }

    public static void setSignType(String signType) {
        AlipayPropertiesConfig.signType = signType;
    }

    public static void setMaxQueryRetry(int maxQueryRetry) {
        AlipayPropertiesConfig.maxQueryRetry = maxQueryRetry;
    }

    public static void setQueryDuration(long queryDuration) {
        AlipayPropertiesConfig.queryDuration = queryDuration;
    }

    public static void setMaxCancelRetry(int maxCancelRetry) {
        AlipayPropertiesConfig.maxCancelRetry = maxCancelRetry;
    }

    public static void setCancelDuration(long cancelDuration) {
        AlipayPropertiesConfig.cancelDuration = cancelDuration;
    }

    public static long getHeartbeatDelay() {
        return heartbeatDelay;
    }

    public static void setHeartbeatDelay(long heartbeatDelay) {
        AlipayPropertiesConfig.heartbeatDelay = heartbeatDelay;
    }

    public static long getHeartbeatDuration() {
        return heartbeatDuration;
    }

    public static void setHeartbeatDuration(long heartbeatDuration) {
        AlipayPropertiesConfig.heartbeatDuration = heartbeatDuration;
    }

    public static String getNotifyUrl() {
        return NOTIFY_URL;
    }

    public static void setNotifyUrl(String notifyUrl) {
        AlipayPropertiesConfig.NOTIFY_URL = notifyUrl;
    }


    public static String getStatementFilePath() {
        return STATEMENT_FILE_PATH;
    }

    public static void setStatementFilePath(String statementFilePath) {
        STATEMENT_FILE_PATH = statementFilePath;
    }
}

