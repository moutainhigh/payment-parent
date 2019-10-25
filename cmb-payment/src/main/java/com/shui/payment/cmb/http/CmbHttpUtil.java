package com.shui.payment.cmb.http;

import com.shui.payment.cmb.constant.ConstantUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by minsiqiang on 2017/11/17 15:52.
 */
public class CmbHttpUtil {
    private static final Log log = LogFactory.getLog(CmbHttpUtil.class);

    /**
     * 连接前置机，发送请求报文，获得返回报文
     *
     * @param data
     * @return
     * @throws MalformedURLException
     */
    public static String httpPost(String url, String data) {
        String result = "";
        BufferedReader br = null;

        try {
            URL httpUrl = new URL(url);

            HttpURLConnection conn;
            conn = (HttpURLConnection) httpUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os;
            os = conn.getOutputStream();
            os.write(data.getBytes(ConstantUtil.GBK));
            os.close();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), ConstantUtil.GBK));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        log.info("【招行银企直连HTTP工具类】--->httpPost, 请求返回内容为："+result);

        return result;
    }

}
