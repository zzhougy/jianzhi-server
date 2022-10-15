package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.ResumeDTO;
import com.zhou.jianzhi.entity.po.Resume;

import java.util.List;




/**
 * ResumeService接口
 *
 * @author zhou
 * @date 2021-01-03
 */
public interface IResumeService extends IService<Resume> {


    /**
     * 查询resume
     *
     * @param resumeDTO
     * @return resume
     */
    public Resume selectOneResume(ResumeDTO resumeDTO) throws Exception;



    /**
     * 查询resume列表
     *
     * @param resumeDTO
     * @return resume集合
     */
    public Page<Resume> selectResumeList(ResumeDTO resumeDTO) throws Exception;


    /**
     * 批量新增resume
     *
     * @param resumeDTOList
     * @return 结果
     */
    public AjaxResult insertResumeList(List<ResumeDTO> resumeDTOList) throws Exception;

    /**
     * 修改resume
     *
     * @param resumeDTO
     * @return 结果
     */
    public AjaxResult updateResume(ResumeDTO resumeDTO);

    /**
     * 删除resume
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteResumeById(int id);


    /**
     *
     * @param id
     * @return
     */
    public Resume selectById(Integer id) throws Exception;


    /**
     * 新增单个resume
     *
     * @param resumeDTO
     * @return
     */
    public AjaxResult addResume(ResumeDTO resumeDTO);
}
