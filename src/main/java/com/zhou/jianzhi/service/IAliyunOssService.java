package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.AliyunOssDTO;
import com.zhou.jianzhi.entity.po.AliyunOss;

import java.util.List;


/**
 * AliyunOssService接口
 *
 * @author zhou
 * @date 2021-01-30
 */
public interface IAliyunOssService extends IService<AliyunOss> {


    /**
    * 根据aliyun_oss表id查询
    *
    * @param id
    * @return
    */
    public AjaxResult selectAliyunOssById(Integer id);

    /**
     * 根据条件查询返回一条AliyunOss信息
     */
    public AjaxResult selectOneAliyunOss(AliyunOssDTO aliyunOssDTO) throws Exception;

    /**
     * 查询AliyunOss列表
     *
     * @param aliyunOssDTO
     * @return aliyun_oss集合
     */
    public AjaxResult selectAliyunOssList(AliyunOssDTO aliyunOssDTO) throws Exception;

    /**
    * 新增单个AliyunOss
    *
    * @param aliyunOssDTO
    * @return
    */
    public AjaxResult addAliyunOss(AliyunOssDTO aliyunOssDTO) throws Exception;

    /**
     * 批量新增AliyunOss
     *
     * @param aliyunOssDTOList
     * @return 结果
     */
    public AjaxResult addAliyunOssList(List<AliyunOssDTO> aliyunOssDTOList) throws Exception;

    /**
     * 根据id修改AliyunOss
     *
     * @param aliyunOssDTO
     * @return 结果
     */
    public AjaxResult updateAliyunOssById(AliyunOssDTO aliyunOssDTO);

    /**
     * 删除AliyunOss
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteAliyunOssById(Integer id);


    //String toFileName(String filepath) throws Exception;
}
