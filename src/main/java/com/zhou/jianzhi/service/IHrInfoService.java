package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.HrInfoDTO;
import com.zhou.jianzhi.entity.po.HrInfo;

import java.util.List;




/**
 * HrInfoService接口
 *
 * @author zhou
 * @date 2021-01-09
 */
public interface IHrInfoService extends IService<HrInfo> {

    /**
    * 根据hr_info表id查询
    *
    * @param id
    * @return
    */
    public HrInfo selectHrInfoById(Long id);


    /**
     * 查询hr_info列表
     *
     * @param hrInfoDTO
     * @return hr_info集合
     */
    public AjaxResult selectHrInfoList(HrInfoDTO hrInfoDTO) throws Exception;

    /**
    * 新增单个HrInfo
    *
    * @param hrInfoDTO
    * @return
    */
    public AjaxResult addHrInfo(HrInfoDTO hrInfoDTO);

    /**
     * 批量新增hr_info
     *
     * @param hrInfoDTOList
     * @return 结果
     */
    public AjaxResult addHrInfoList(List<HrInfoDTO> hrInfoDTOList) throws Exception;

    /**
     * 根据id修改hr_info
     *
     * @param hrInfoDTO
     * @return 结果
     */
    public AjaxResult updateHrInfo(HrInfoDTO hrInfoDTO);

    /**
     * 删除hr_info
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteHrInfoById(int id);


    /**
     * 根据条件查询返回一条hr_info
     */
    HrInfo selectOneHrInfo(HrInfoDTO hrInfoDTO);
}
