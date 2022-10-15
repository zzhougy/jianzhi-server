package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.base.ActiveUser;
import com.zhou.jianzhi.entity.dto.JobSeekerDTO;
import com.zhou.jianzhi.entity.po.BaseUser;
import com.zhou.jianzhi.entity.po.JobSeeker;
import com.zhou.jianzhi.mapper.JobSeekerMapper;
import com.zhou.jianzhi.service.BaseUserService;
import com.zhou.jianzhi.service.ICommonService;
import com.zhou.jianzhi.service.IJobSeekerService;
import com.zhou.jianzhi.service.ISchoolService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * job_seekerService业务层处理
 *
 * @author zhou
 * @date 2021-01-09
 */
@Service
@Transactional
public class JobSeekerServiceImpl extends ServiceImpl<JobSeekerMapper, JobSeeker> implements IJobSeekerService {

    @Autowired
    private JobSeekerMapper jobSeekerMapper;

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private BaseUserService baseUserService;


    /**
     * 根据job_seeker表id查询
     *
     * @param id
     * @return
     */
    @Override
    public JobSeeker selectJobSeekerById(Integer id) {
        JobSeeker jobSeeker = this.jobSeekerMapper.selectById(id);

        BaseUser baseUser = baseUserService.selectById(jobSeeker.getUserId());
        if (baseUser!=null){
            jobSeeker.setUsername(baseUser.getUsername());
        }
        return jobSeeker;
    }
	
	
	/**
     * 根据条件查询返回一条JobSeeker信息
     * @param jobSeekerDTO
     * @return
     */
    @Override
    public JobSeeker selectOneJobSeeker(JobSeekerDTO jobSeekerDTO) {
        ActiveUser activeUser = null;
        JobSeeker jobSeeker = new JobSeeker();
        BeanUtils.copyProperties(jobSeekerDTO,jobSeeker);
        QueryWrapper<JobSeeker> wrapper = new QueryWrapper<>();
        if(jobSeeker.getId() != null){
            wrapper.eq(!StrUtil.isBlank(jobSeeker.getId().toString()),"id", jobSeeker.getId());
        }
        if (jobSeekerDTO.getId()==null&&jobSeekerDTO.getUserId()==null){
            activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            Long userId = activeUser.getUser().getId();
            wrapper.eq(!StrUtil.isBlank(userId.toString()),"user_id", userId);
        }
        if(jobSeeker.getUserId() != null){
            wrapper.eq(!StrUtil.isBlank(jobSeeker.getUserId().toString()),"user_id", jobSeeker.getUserId());
        }
        if(jobSeeker.getSchoolId() != null){
            wrapper.eq(!StrUtil.isBlank(jobSeeker.getSchoolId().toString()),"school_id", jobSeeker.getSchoolId());
        }
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getName()),"name",jobSeeker.getName());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getSex()),"sex",jobSeeker.getSex());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getGrade()),"grade",jobSeeker.getGrade());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getPhone()),"phone",jobSeeker.getPhone());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getWechat()),"wechat",jobSeeker.getWechat());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getQq()),"qq",jobSeeker.getQq());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getEmail()),"email",jobSeeker.getEmail());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getImg()),"img",jobSeeker.getImg());
        JobSeeker jobSeeker1 = this.jobSeekerMapper.selectOne(wrapper);
        if (activeUser!=null){
            jobSeeker1.setUsername(activeUser.getUser().getUsername());
            jobSeeker1.setSchoolName(schoolService.selectSchoolById(jobSeeker1.getSchoolId()).getName());
        }



        return jobSeeker1;
    }
	

    /**
     * 查询JobSeeker列表
     *
     * @param jobSeekerDTO
     * @return
     */
    @Override
    public Page<JobSeeker> selectJobSeekerList(JobSeekerDTO jobSeekerDTO) throws Exception {
        Page<JobSeeker> page = new Page<>(jobSeekerDTO.getPageCurrent(),jobSeekerDTO.getPageSize());
        JobSeeker jobSeeker = new JobSeeker();
        BeanUtils.copyProperties(jobSeekerDTO,jobSeeker);
        QueryWrapper<JobSeeker> wrapper = new QueryWrapper<>();
        if(jobSeeker.getId() != null){
            wrapper.eq(!StrUtil.isBlank(jobSeeker.getId().toString()),"id", jobSeeker.getId());
        }
        if(jobSeeker.getUserId() != null){
            wrapper.eq(!StrUtil.isBlank(jobSeeker.getUserId().toString()),"user_id", jobSeeker.getUserId());
        }
        if(jobSeeker.getSchoolId() != null){
            wrapper.eq(!StrUtil.isBlank(jobSeeker.getSchoolId().toString()),"school_id", jobSeeker.getSchoolId());
        }
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getName()),"name",jobSeeker.getName());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getSex()),"sex",jobSeeker.getSex());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getGrade()),"grade",jobSeeker.getGrade());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getPhone()),"phone",jobSeeker.getPhone());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getWechat()),"wechat",jobSeeker.getWechat());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getQq()),"qq",jobSeeker.getQq());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getEmail()),"email",jobSeeker.getEmail());
        wrapper.eq(!StrUtil.isBlank(jobSeeker.getImg()),"img",jobSeeker.getImg());
        Page<JobSeeker> Ipage = this.jobSeekerMapper.selectPage(page, wrapper);
        List<JobSeeker> records = Ipage.getRecords();
        if(records.size()!=0){
            for (JobSeeker jobSeeker1 : records){
                jobSeeker1.setSchoolName(schoolService.selectSchoolById(jobSeeker1.getSchoolId()).getName());
                BaseUser baseUser = baseUserService.selectById(jobSeeker1.getUserId());
                jobSeeker1.setUsername(baseUser.getUsername());
                jobSeeker1.setCreateTime(baseUser.getCreateTime());
            }
        }
        return Ipage;
    }

    /**
    * 新增单个JobSeeker
    *
    * @param jobSeekerDTO
    * @return
    */
    @Override
    public AjaxResult addJobSeeker(JobSeekerDTO jobSeekerDTO) {
        JobSeeker jobSeeker = new JobSeeker();
        BeanUtils.copyProperties(jobSeekerDTO,jobSeeker);
        int insert = this.jobSeekerMapper.insert(jobSeeker);
        if (insert==1){
        return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 批量新增JobSeeker
     *
     * @param jobSeekerDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addJobSeekerList(List<JobSeekerDTO> jobSeekerDTOList) throws Exception {
        //添加操作
        for (JobSeekerDTO jobSeekerDTO : jobSeekerDTOList){
            JobSeeker jobSeeker = new JobSeeker();
            BeanUtils.copyProperties(jobSeekerDTO,jobSeeker);
            int insert = this.jobSeekerMapper.insert(jobSeeker);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改JobSeeker
     *
     * @param jobSeekerDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateJobSeeker(JobSeekerDTO jobSeekerDTO){
        JobSeeker jobSeeker1 = selectJobSeekerById(jobSeekerDTO.getId());
        if (jobSeekerDTO.getPhone()!=null){
            jobSeeker1.setPhone(jobSeekerDTO.getPhone());
        }
        if (jobSeekerDTO.getEmail()!=null){
            jobSeeker1.setEmail(jobSeekerDTO.getEmail());
        }
        if (jobSeekerDTO.getSno()!=null){
            jobSeeker1.setSno(jobSeekerDTO.getSno());
        }
        if(jobSeekerDTO.getId() != null){
            jobSeeker1.setId(jobSeekerDTO.getId());
        }
        if(jobSeekerDTO.getUserId() != null){
            jobSeeker1.setUserId(jobSeekerDTO.getUserId());
        }
        if(jobSeekerDTO.getSex() != null && !jobSeekerDTO.getSex().isEmpty()){
            jobSeeker1.setSex(jobSeekerDTO.getSex());
        }
        if(jobSeekerDTO.getSchoolId() != null){
            jobSeeker1.setSchoolId(jobSeekerDTO.getSchoolId());
        }
        if(jobSeekerDTO.getName() != null && !jobSeekerDTO.getName().isEmpty()){
            jobSeeker1.setName(jobSeekerDTO.getName());
        }
        if(jobSeekerDTO.getSno() != null && !jobSeekerDTO.getSno().isEmpty()){
            jobSeeker1.setSno(jobSeekerDTO.getSno());
        }
        if(jobSeekerDTO.getGrade() != null && !jobSeekerDTO.getGrade().isEmpty()){
            jobSeeker1.setGrade(jobSeekerDTO.getGrade());
        }
        if(jobSeekerDTO.getPhone() != null && !jobSeekerDTO.getPhone().isEmpty()){
            jobSeeker1.setPhone(jobSeekerDTO.getPhone());
        }
        if(jobSeekerDTO.getWechat() != null && !jobSeekerDTO.getWechat().isEmpty()){
            jobSeeker1.setWechat(jobSeekerDTO.getWechat());
        }
        if(jobSeekerDTO.getQq() != null && !jobSeekerDTO.getQq().isEmpty()){
            jobSeeker1.setQq(jobSeekerDTO.getQq());
        }
        if(jobSeekerDTO.getEmail() != null && !jobSeekerDTO.getEmail().isEmpty()){
            jobSeeker1.setEmail(jobSeekerDTO.getEmail());
        }
        if(jobSeekerDTO.getImg() != null && !jobSeekerDTO.getImg().isEmpty()){
            jobSeeker1.setImg(jobSeekerDTO.getImg());
        }
        int update = this.jobSeekerMapper.updateById(jobSeeker1);
        if(update > 0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    /**
     * 根据id删除JobSeeker
     *
     * @param id 需要删除的job_seekerID
     * @return 结果
     */
    @Override
    public AjaxResult deleteJobSeekerById(int id)
    {
        int delete = this.jobSeekerMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}








