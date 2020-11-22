package com.payment.shui.webank.channel.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author code
 * @Title: WebankConfig
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 15:31
 */
@Component
public class WebankConfig {
    /**
     * appid，微众分配
     */
    @Value("${webank.app_id}")
    public String appId;

    /**
     * secret,微众分配
     */
    @Value("${webank.secret}")
    public String secret;
    /**
     * 合作方调用微众公共url地址
     */
    @Value("${f.webank.http.url}")
    public String httpUrl;

    @Value("${access_token.path.url}")
    public String accessTokenUrl;

    /**
     * 获取ticket的访问路径
     */
    @Value("${ticket.path.url}")
    public String apiTicketUrl;

    /**
     * 后台文件接口
     */
    @Value("${f.file.webank.http.url}")
    public String httpFileUrl;
    /**
     * 文件上传 方法：POST
     */
    @Value("${upload.path.url}")
    public String uploadFileUrl;
    /**
     * 件下载 方法：GET
     */
    @Value("${download.path.url}")
    public String downloadFileUrl;

    @Value("${version.1.0}")
    public String version_1;
    @Value("${version.2.0}")
    public String version_2;
    /**
     * 文件上传带通知接口，(包含变量信息,后期使用需要替换)  方法：POST
     * application/json格式提交，只支持单文件，base64文件
     */
    @Value("${upload.uploadJson.path.url}")
    public String uploadJsonFileUrl;
    /**
     * 文件上传带通知接口，(包含变量信息,后期使用需要替换) 方法：POST
     * form-data格式提交，支持多文件，二进制流文件
     */
    @Value("${upload.uploadForm.path.url}")
    public String uploadFormFileUrl;

    public String getAppId() {
        return appId;
    }

    public WebankConfig setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public WebankConfig setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public WebankConfig setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
        return this;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public WebankConfig setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
        return this;
    }

    public String getApiTicketUrl() {
        return apiTicketUrl;
    }

    public WebankConfig setApiTicketUrl(String apiTicketUrl) {
        this.apiTicketUrl = apiTicketUrl;
        return this;
    }

    public String getHttpFileUrl() {
        return httpFileUrl;
    }

    public WebankConfig setHttpFileUrl(String httpFileUrl) {
        this.httpFileUrl = httpFileUrl;
        return this;
    }

    public String getUploadFileUrl() {
        return uploadFileUrl;
    }

    public WebankConfig setUploadFileUrl(String uploadFileUrl) {
        this.uploadFileUrl = uploadFileUrl;
        return this;
    }

    public String getDownloadFileUrl() {
        return downloadFileUrl;
    }

    public WebankConfig setDownloadFileUrl(String downloadFileUrl) {
        this.downloadFileUrl = downloadFileUrl;
        return this;
    }

    public String getVersion_1() {
        return version_1;
    }

    public WebankConfig setVersion_1(String version_1) {
        this.version_1 = version_1;
        return this;
    }

    public String getVersion_2() {
        return version_2;
    }

    public WebankConfig setVersion_2(String version_2) {
        this.version_2 = version_2;
        return this;
    }

    public String getUploadJsonFileUrl() {
        return uploadJsonFileUrl;
    }

    public WebankConfig setUploadJsonFileUrl(String uploadJsonFileUrl) {
        this.uploadJsonFileUrl = uploadJsonFileUrl;
        return this;
    }

    public String getUploadFormFileUrl() {
        return uploadFormFileUrl;
    }

    public WebankConfig setUploadFormFileUrl(String uploadFormFileUrl) {
        this.uploadFormFileUrl = uploadFormFileUrl;
        return this;
    }
}
