package com.zhou.jianzhi.service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public interface IAliyunOssService1 {

    void downPic(String filePath, HttpServletResponse response) throws Exception;

    String toFileName(String filepath) throws Exception;

    InputStream getInputStream(String filePath) throws Exception;

    //public AjaxResult uploadFileToOSS(String originalName, ByteArrayOutputStream byteArrayOutputStream) throws FileNotFoundException;

}
