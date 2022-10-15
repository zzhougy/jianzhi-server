package com.zhou.jianzhi.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.zhou.jianzhi.common.util.EncryptUtils;
import com.zhou.jianzhi.service.IAliyunOssService1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description :
 * @Author : zhihao
 * @Date : 2020/12/17 16:33
 */
@Service
public class AliyunOssServiceImpl1 implements IAliyunOssService1 {

    @Value("${aliyun.oss.endpoint}")
    public String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    public String AccessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    public String accessKeySecret;
    @Value("${aliyun.oss.region}")
    public String region;
    @Value("${aliyun.oss.bucket}")
    public String bucket;
//    @Value("${aliyun.oss.roleArn}")
//    public String roleArn;
//    @Value("${aliyun.oss.roleSessionName}")
//    public String roleSessionName;
    @Value("${aliyun.oss.downloadEndPoint}")
    public String downloadEndPoint;


    @Override
    public void downPic(String path, HttpServletResponse response) throws Exception {
        //传进来的path是aes解密过的，需要先解密
        String filePath = toFilePath(path);
        //oss用法看阿里云官方文档：https://help.aliyun.com/document_detail/111371.html?spm=a2c4g.11186623.6.1429.20791500Ww073V
        OSS ossClient = new OSSClientBuilder().build(downloadEndPoint, AccessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucket, filePath);
        InputStream inputStream = ossObject.getObjectContent();
        int buffer = 1024 * 10;
        byte data[] = new byte[buffer];
        try {
            response.setContentType("application/octet-stream");
            // 文件名可以任意指定   将文件名转为ASCLL编码
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filePath, "UTF-8"));
            int read;
            while ((read = inputStream.read(data)) != -1) {
                response.getOutputStream().write(data, 0, read);
            }
            response.getOutputStream().flush();
            response.getOutputStream().close();
            ossObject.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @Description : 数据库存的路径是加密的，传回来解密
     */
    @Override
    public String toFileName(String filepath) throws Exception {
        System.out.println(toFilePath(filepath));
        return toFilePath(filepath);
    }

    @Override
    public InputStream getInputStream(String filePath) throws Exception {
        //传进来的path是aes解密过的，需要先解密
//        String filePath1 = toFilePath(filePath);
//        String filePath1 = "Template/供客户报价单-模板.xlsx";
        //oss用法看阿里云官方文档：https://help.aliyun.com/document_detail/111371.html?spm=a2c4g.11186623.6.1429.20791500Ww073V
        OSS ossClient = new OSSClientBuilder().build(downloadEndPoint, AccessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucket, filePath);
        InputStream inputStream = ossObject.getObjectContent();
        return inputStream;
    }

    /**
     * @Description : 解密
     */
    public String toFilePath(String path) throws Exception {
        byte[] decode = EncryptUtils.parseHexStr2Byte(path);
        byte[] decryptResult = EncryptUtils.decrypt(decode);
        return new String(decryptResult, "UTF-8");
    }

}
