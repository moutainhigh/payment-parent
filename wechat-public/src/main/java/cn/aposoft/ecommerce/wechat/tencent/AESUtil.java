package cn.aposoft.ecommerce.wechat.tencent;


import cn.aposoft.ecommerce.wechat.util.LogPortal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Security;

/**
 * 参考链接：https://blog.csdn.net/firas/article/details/47043335
 *
 * @author code
 * @Title: AESUtil
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/10/22下午6:10
 */
public class AESUtil {

    private AESUtil() {
    }

    private static boolean INITIALIZED = false;

    /**
     * The Advanced Encryption Standard (AES) encryption algorithm can be used with various modes. Some combinations are not secured:
     * <p>
     * Electronic Codebook (ECB) mode: Under a given key, any given plaintext block always gets encrypted to the same ciphertext block. Thus, it does not hide data patterns well. In some senses, it doesn't provide serious message confidentiality, and it is not recommended for use in cryptographic protocols at all.
     * Cipher Block Chaining (CBC) with PKCS#5 padding (or PKCS#7) is susceptible to padding oracle attacks.
     * In both cases, Galois/Counter Mode (GCM) with no padding should be preferred.
     * <p>
     * This rule raises an issue when a Cipher instance is created with either ECB or CBC/PKCS5Padding mode.
     */
    public static final String ALGORITHM = "AES/ECB/PKCS7Padding";// 算法/模式/补码方式

//    /**
////     * @param str 要被加密的字符串
////     * @param key 加/解密要用的长度为32的字节数组（256位）密钥
////     * @return byte[]  加密后的字节数组
////     */
////    public static byte[] Aes256Encode(String str, byte[] key) {
////        initialize();
////        byte[] result = null;
////        try {
////            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
////            SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); //生成加密解密需要的Key
////            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
////            result = cipher.doFinal(str.getBytes("UTF-8"));
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return result;
////    }

    /**
     * @param bytes 要被解密的字节数组
     * @param key   加/解密要用的长度为32的字节数组（256位）密钥
     * @return String  解密后的字符串
     */
    public static String Aes256Decode(byte[] bytes, String key) {
        initialize();
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //生成加密解密需要的Key
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(WechatConstant.UTF8), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = cipher.doFinal(bytes);
            result = new String(decoded, WechatConstant.UTF8);
        } catch (Exception e) {
            LogPortal.error("aes解密异常", e);
        }
        return result;
    }

    /**
     * Android的Java运行环境中包含了"AES/ECB/PKCS7Padding"算法，但一般的JRE（如Oracle JRE、OpenJRE）里面只有"AES/ECB/PKCS5Padding"算法，
     * 没有"AES/ECB/PKCS7Padding"算法。故我们需要引入BouncyCastle的库，并给Cipher.getInstance方法传入参数"BC"来指定Java使用这个库里的加/解密算法。
     * BouncyCastle的加/解密类库在Maven仓库中的位置：<groupId>org.bouncycastle</groupId><artifactId>bcprov-jdk15on</artifactId>，
     * 其Jar包的下载地址：http://www.bouncycastle.org/latest_releases.html
     * <p>
     * 在这段代码可以运行之前，还有一个问题需要解决。Java本身限制密钥的长度最多128位，而AES256需要的密钥长度是256位，
     * 因此需要到Java官网上下载一个Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files。
     * 在Java SE的下载页面下面的Additional Resources那里会有下载链接。下载后打开压缩包，里面有两个jar文件。
     * 把这两个jar文件解压到JRE目录下的lib/security文件夹，覆盖原来的文件。这样Java就不再限制密钥的长度了。
     */
    public static void initialize() {
        if (INITIALIZED) {
            return;
        }
        Security.addProvider(new BouncyCastleProvider());
        INITIALIZED = true;
    }

    /**
     * 解密步骤如下：
     * （1）对加密串A做base64解码，得到加密串B
     * （2）对商户key做md5，得到32位小写key* ( key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置 )
     * （3）用key*对加密串B做AES-256-ECB解密（PKCS7Padding）
     *
     * @param data 待解密数据
     * @param key  商户原始key
     * @return
     */
    public static String decodeXmlData(String data, String key) throws UnsupportedEncodingException {
        byte[] req_info = Base64Utils.decode(data.getBytes(WechatConstant.UTF8));
        String md5Key = MD5Util.md5(key);
        return Aes256Decode(req_info, md5Key);
    }

}
