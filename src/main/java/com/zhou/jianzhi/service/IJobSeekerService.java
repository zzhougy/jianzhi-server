package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobSeekerDTO;
import com.zhou.jianzhi.entity.po.JobSeeker;

import java.util.List;




/**
 * JobSeekerService接口
 *
 * @author zhou
 * @date 2021-01-09
 */
public interface IJobSeekerService extends IService<JobSeeker> {

    /**
    * 根据job_seeker表id查询
    *
    * @param id
    * @return
    */
    public JobSeeker selectJobSeekerById(Integer id);


    /**
     * 根据条件查询返回一条JobSeeker信息
     */
    public JobSeeker selectOneJobSeeker(JobSeekerDTO jobSeekerDTO);

    /**
     * 查询JobSeeker列表
     *
     * @param jobSeekerDTO
     * @return job_seeker集合
     */
    public Page<JobSeeker> selectJobSeekerList(JobSeekerDTO jobSeekerDTO) throws Exception;

    /**
    * 新增单个JobSeeker
    *
    * @param jobSeekerDTO
    * @return
    */
    public AjaxResult addJobSeeker(JobSeekerDTO jobSeekerDTO);

    /**
     * 批量新增JobSeeker
     *
     * @param jobSeekerDTOList
     * @return 结果
     */
    public AjaxResult addJobSeekerList(List<JobSeekerDTO> jobSeekerDTOList) throws Exception;

    /**
     * 根据id修改JobSeeker
     *
     * @param jobSeekerDTO
     * @return 结果
     */
    public AjaxResult updateJobSeeker(JobSeekerDTO jobSeekerDTO);

    /**
     * 删除JobSeeker
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteJobSeekerById(int id);


}
