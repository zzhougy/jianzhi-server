package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.service.IAliyunOssService1;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/aliyun")
public class AliyunOssController1 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Value("${aliyun.oss.endpoint}")
//    public String endpoint;
//    @Value("${aliyun.oss.accessKeyId}")
//    public String AccessKeyId;
//    @Value("${aliyun.oss.accessKeySecret}")
//    public String accessKeySecret;
//    @Value("${aliyun.oss.region}")
//    public String region;
//    @Value("${aliyun.oss.bucket}")
//    public String bucket;
//    @Value("${aliyun.oss.roleArn}")
//    public String roleArn;
//    @Value("${aliyun.oss.roleSessionName}")
//    public String roleSessionName;

    @Autowired
    private IAliyunOssService1 aliyunOssService;


//    @ResponseBody
//    @RequestMapping("/sts")
//    public OssVO Credentials(HttpServletRequest req) {
//
//    }

//    @GetMapping("/download")
//    public void download(@RequestParam(value = "filepath") String filepath, HttpServletResponse response) throws Exception {
//        //加密
//        String s = MatDrawingServiceImpl.signFilePath(filepath);
//        downloadPicService.downPic(s,response);
//    }

    @GetMapping("/toFileName")
    public String toFileName(@RequestParam(value = "filepath") String filepath) throws Exception {
        return aliyunOssService.toFileName(filepath);
    }
}
