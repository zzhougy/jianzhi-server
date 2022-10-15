package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.AliyunOssDTO;
import com.zhou.jianzhi.entity.vo.OssVO;
import com.zhou.jianzhi.service.IAliyunOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* AliyunOssController
*
* @author zhou
* @date 2021-01-30
*/
@RestController
@RequestMapping("/aliyunOss")
public class AliyunOssController {

    @Value("${aliyun.oss.endpoint}")
    public String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    public String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    public String accessKeySecret;
    @Value("${aliyun.oss.region}")
    public String region;
    @Value("${aliyun.oss.bucket}")
    public String bucket;


    @Autowired
    private IAliyunOssService aliyunOssService;


    /**
     * 根据aliyun_oss表id查询AliyunOss
     */
    @GetMapping("/getById")
    public AjaxResult selectById(@RequestParam Integer id) {
        return aliyunOssService.selectAliyunOssById(id);
    }

    /**
     * 根据条件查询返回一条AliyunOss信息
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody AliyunOssDTO aliyunOssDTO) throws Exception {
        return aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
    }

    /**
     * 查询AliyunOss列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody AliyunOssDTO aliyunOssDTO) throws Exception {
        return aliyunOssService.selectAliyunOssList(aliyunOssDTO);
    }

    /**
    * 新增单个AliyunOss
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody AliyunOssDTO aliyunOssDTO) throws Exception {
        return aliyunOssService.addAliyunOss(aliyunOssDTO);
    }

    /**
     * 批量新增AliyunOss
     */
    @PostMapping("/addList")
    public AjaxResult addAliyunOssList(@RequestBody List<AliyunOssDTO> aliyunOssDTOList) throws Exception {
        return AjaxResult.success(aliyunOssService.addAliyunOssList(aliyunOssDTOList));
    }

    /**
     * 根据id修改AliyunOss
     */
    @PostMapping("/edit")
    public AjaxResult editById(@RequestBody AliyunOssDTO aliyunOssDTO) {
        return aliyunOssService.updateAliyunOssById(aliyunOssDTO);
    }

    /**
     * 根据id删除AliyunOss
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        return AjaxResult.success(aliyunOssService.deleteAliyunOssById(id));
    }


//    @GetMapping("/toFileName")
//    public String toFileName(@RequestParam(value = "filepath") String filepath) throws Exception {
//        return aliyunOssService.toFileName(filepath);
//    }


    @ResponseBody
    @RequestMapping("/getOssData")
    public AjaxResult getOssData() {
        OssVO ossVO = new OssVO();
        ossVO.setAccessKeyId(accessKeyId);
        ossVO.setEndpoint(endpoint);
        ossVO.setAccessKeySecret(accessKeySecret);
        ossVO.setRegion(region);
        ossVO.setBucket(bucket);
        return AjaxResult.success(ossVO);
    }



}
