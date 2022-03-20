package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobseekerJobRelationDTO;
import com.zhou.jianzhi.entity.po.JobseekerJobRelation;

import java.util.List;




/**
 * JobseekerJobRelationService接口
 *
 * @author zhou
 * @date 2021-01-09
 */
public interface IJobseekerJobRelationService extends IService<JobseekerJobRelation> {

    /**
    * 根据jobseeker_job_relation表id查询
    *
    * @param id
    * @return
    */
    public JobseekerJobRelation selectJobseekerJobRelationById(Integer id);


    /**
     * 根据条件查询返回一条JobseekerJobRelation信息
     */
    public JobseekerJobRelation selectOneJobseekerJobRelation(JobseekerJobRelationDTO jobseekerJobRelationDTO);

    /**
     * 查询JobseekerJobRelation列表
     *
     * @param jobseekerJobRelationDTO
     * @return jobseeker_job_relation集合
     */
    public Page<JobseekerJobRelation> selectJobseekerJobRelationList(JobseekerJobRelationDTO jobseekerJobRelationDTO) throws Exception;

    /**
    * 新增单个JobseekerJobRelation
    *
    * @param jobseekerJobRelationDTO
    * @return
    */
    public AjaxResult addJobseekerJobRelation(JobseekerJobRelationDTO jobseekerJobRelationDTO) throws Exception;

    /**
     * 批量新增JobseekerJobRelation
     *
     * @param jobseekerJobRelationDTOList
     * @return 结果
     */
    public AjaxResult addJobseekerJobRelationList(List<JobseekerJobRelationDTO> jobseekerJobRelationDTOList) throws Exception;

    /**
     * 根据id修改JobseekerJobRelation
     *
     * @param jobseekerJobRelationDTO
     * @return 结果
     */
    public Integer updateJobseekerJobRelation(JobseekerJobRelationDTO jobseekerJobRelationDTO);

    /**
     * 删除JobseekerJobRelation
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteJobseekerJobRelationById(int id);


}
