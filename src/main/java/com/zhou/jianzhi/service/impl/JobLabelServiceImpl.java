package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobLabelDTO;
import com.zhou.jianzhi.entity.po.JobLabel;
import com.zhou.jianzhi.mapper.JobLabelMapper;
import com.zhou.jianzhi.service.IJobLabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * job_labelService业务层处理
 *
 * @author zhou
 * @date 2021-02-08
 */
@Service
@Transactional
public class JobLabelServiceImpl extends ServiceImpl<JobLabelMapper, JobLabel> implements IJobLabelService {

    @Autowired
    private JobLabelMapper jobLabelMapper;


    /**
     * 根据job_label表id查询
     *
     * @param id
     * @return
     */
    @Override
    public AjaxResult selectJobLabelById(Integer id) {
        JobLabel jobLabel = this.jobLabelMapper.selectById(id);
        if (jobLabel==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(jobLabel);
    }

	/**
     * 根据条件查询返回一条JobLabel信息
     * @param jobLabelDTO
     * @return
     */
    @Override
    public AjaxResult selectOneJobLabel(JobLabelDTO jobLabelDTO) {
        JobLabel jobLabel = new JobLabel();
        BeanUtils.copyProperties(jobLabelDTO,jobLabel);
        QueryWrapper<JobLabel> wrapper = new QueryWrapper<>();
        if(jobLabel.getId() != null){
            wrapper.eq(!StrUtil.isBlank(jobLabel.getId().toString()),"id", jobLabel.getId());
        }
        wrapper.eq(!StrUtil.isBlank(jobLabel.getName()),"name",jobLabel.getName());
        jobLabel = this.jobLabelMapper.selectOne(wrapper);
        if (jobLabel==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(jobLabel);
    }

    /**
     * 查询JobLabel列表
     *
     * @param jobLabelDTO
     * @return
     */
    @Override
    public AjaxResult selectJobLabelList(JobLabelDTO jobLabelDTO) throws Exception {
        Page<JobLabel> page = new Page<>(jobLabelDTO.getPageCurrent(),jobLabelDTO.getPageSize());
        JobLabel jobLabel = new JobLabel();
        BeanUtils.copyProperties(jobLabelDTO,jobLabel);
        QueryWrapper<JobLabel> wrapper = new QueryWrapper<>();
        if(jobLabel.getId() != null){
            wrapper.eq(!StrUtil.isBlank(jobLabel.getId().toString()),"id", jobLabel.getId());
        }
        wrapper.eq(!StrUtil.isBlank(jobLabel.getName()),"name",jobLabel.getName());
        Page<JobLabel> Ipage = this.jobLabelMapper.selectPage(page, wrapper);
        if (Ipage==null){
            return AjaxResult.error();
        }
        return AjaxResult.success(Ipage);
    }

    /**
    * 新增单个JobLabel
    *
    * @param jobLabelDTO
    * @return
    */
    @Override
    public AjaxResult addJobLabel(JobLabelDTO jobLabelDTO) {
        JobLabel jobLabel = new JobLabel();
        BeanUtils.copyProperties(jobLabelDTO,jobLabel);
        int insertedNum = this.jobLabelMapper.insert(jobLabel);
        if (insertedNum > 0){
            return AjaxResult.success("新增成功");
        }
        return AjaxResult.error("新增失败");
    }

    /**
     * 批量新增JobLabel
     *
     * @param jobLabelDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addJobLabelList(List<JobLabelDTO> jobLabelDTOList) throws Exception {
        //添加操作
        for (JobLabelDTO jobLabelDTO : jobLabelDTOList){
            JobLabel jobLabel = new JobLabel();
            BeanUtils.copyProperties(jobLabelDTO,jobLabel);
            int insert = this.jobLabelMapper.insert(jobLabel);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改JobLabel
     *
     * @param jobLabelDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateJobLabelById(JobLabelDTO jobLabelDTO){
        AjaxResult ajaxResult = selectJobLabelById(jobLabelDTO.getId());
        if ((Integer) ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
            JobLabel jobLabel = (JobLabel)ajaxResult.get("data");
            if(jobLabel.getId() != null){
                jobLabel.setId(jobLabelDTO.getId());
            }
            if(jobLabel.getName() != null && !jobLabel.getName().isEmpty()){
                jobLabel.setName(jobLabelDTO.getName());
            }
            int updatedNum = this.jobLabelMapper.updateById(jobLabel);
            if(updatedNum > 0){
                return AjaxResult.success("修改成功");
            }else{
                return AjaxResult.error("修改失败");
            }
        }else {
            return AjaxResult.error("数据不存在");
        }
    }

    /**
     * 根据id删除JobLabel
     *
     * @param id 需要删除的job_labelID
     * @return 结果
     */
    @Override
    public AjaxResult deleteJobLabelById(Integer id)
    {
        int delete = this.jobLabelMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}








