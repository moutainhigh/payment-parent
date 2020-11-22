package com.payment.alipay.util;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件保存类
 *
 * @author code
 * @Title: FileUtils
 * @Copyright: Copyright (c) 2017
 * @Description: <br>
 * @Company: www.qdingnet.com
 * @Created on 2018/8/16下午5:20
 */
@Slf4j
public class FileUtils {


    private FileUtils() {

    }

    /**
     * 把文件流写入本地
     *
     * @param data     要保存的内容数据
     * @param pathName 文件名称，包含路径
     */
    public static void write2Local(String data, String pathName) throws UnsupportedEncodingException {
        InputStream in = new ByteArrayInputStream(data.getBytes(ConstantUtil.UTF8));
        File file = new File(pathName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (FileOutputStream fileout = new FileOutputStream(file)) {

            /**
             * 根据实际运行效果 设置缓冲区大小
             */
            byte[] buffer = new byte[1024];
            int ch = 0;
            while ((ch = in.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            fileout.flush();
        } catch (Exception e) {
            log.error("获取文件时发生异常!错误信息是:{}", e.getMessage(),
                    e);
        }
        log.info("文件流写入成功。文件路径名是:{}", pathName);
    }


    /**
     * 把文件流写入本地
     *
     * @param data     要保存的内容数据
     * @param pathName 文件名称，包含路径
     */
    public static void save2Local(String data, String pathName) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(data.getBytes(ConstantUtil.UTF8));
        File dir = new File(pathName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Path localFilePath = Paths.get(pathName);
        File localFile = localFilePath.toFile();

        FileChannel fileChannel = null;
        try {
            if (localFile.exists()) {
                Files.delete(localFile.toPath());

            }
            fileChannel = FileChannel.open(localFilePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);
            fileChannel.transferFrom(Channels.newChannel(inputStream), 0, Long.MAX_VALUE);
        } catch (Exception e) {
            log.error("文件写入异常", e);
            throw e;
        } finally {
            //关闭文件操作
            if (fileChannel != null) {
                fileChannel.close();
            }

            inputStream.close();

        }

        log.info("对账单数据文件流写入成功，路径：{}", pathName);
    }


}
