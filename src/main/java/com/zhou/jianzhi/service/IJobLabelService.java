package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobLabelDTO;
import com.zhou.jianzhi.entity.po.JobLabel;

import java.util.List;


/**
 * JobLabelService接口
 *
 * @author zhou
 * @date 2021-02-08
 */
public interface IJobLabelService extends IService<JobLabel> {


    /**
    * 根据job_label表id查询
    *
    * @param id
    * @return
    */
    public AjaxResult selectJobLabelById(Integer id);

    /**
     * 根据条件查询返回一条JobLabel信息
     */
    public AjaxResult selectOneJobLabel(JobLabelDTO jobLabelDTO);

    /**
     * 查询JobLabel列表
     *
     * @param jobLabelDTO
     * @return job_label集合
     */
    public AjaxResult selectJobLabelList(JobLabelDTO jobLabelDTO) throws Exception;

    /**
    * 新增单个JobLabel
    *
    * @param jobLabelDTO
    * @return
    */
    public AjaxResult addJobLabel(JobLabelDTO jobLabelDTO);

    /**
     * 批量新增JobLabel
     *
     * @param jobLabelDTOList
     * @return 结果
     */
    public AjaxResult addJobLabelList(List<JobLabelDTO> jobLabelDTOList) throws Exception;

    /**
     * 根据id修改JobLabel
     *
     * @param jobLabelDTO
     * @return 结果
     */
    public AjaxResult updateJobLabelById(JobLabelDTO jobLabelDTO);

    /**
     * 删除JobLabel
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteJobLabelById(Integer id);


}
