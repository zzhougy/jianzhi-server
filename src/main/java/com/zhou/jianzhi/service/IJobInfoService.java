package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobInfoDTO;
import com.zhou.jianzhi.entity.po.JobInfo;

import java.util.List;




/**
 * JobInfoService接口
 *
 * @author zhou
 * @date 2021-01-09
 */
public interface IJobInfoService extends IService<JobInfo> {

    /**
    * 根据job_info表id查询
    *
    * @param id
    * @return
    */
    public JobInfo selectJobInfoById(Integer id) throws Exception;


    /**
     * 查询job_info列表
     *
     * @param jobInfoDTO
     * @return job_info集合
     */
    public Page<JobInfo> selectJobInfoList(JobInfoDTO jobInfoDTO) throws Exception;

    /**
    * 新增单个JobInfo
    *
    * @param jobInfoDTO
    * @return
    */
    public Integer addJobInfo(JobInfoDTO jobInfoDTO) throws Exception;

    /**
     * 批量新增job_info
     *
     * @param jobInfoDTOList
     * @return 结果
     */
    public AjaxResult addJobInfoList(List<JobInfoDTO> jobInfoDTOList) throws Exception;

    /**
     * 根据id修改job_info
     *
     * @param jobInfoDTO
     * @return 结果
     */
    public AjaxResult updateJobInfo(JobInfoDTO jobInfoDTO) throws Exception;

    /**
     * 删除job_info
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteJobInfoById(int id);


}
