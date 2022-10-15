package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.base.ActiveUser;
import com.zhou.jianzhi.entity.dto.AliyunOssDTO;
import com.zhou.jianzhi.entity.dto.JobseekerJobRelationDTO;
import com.zhou.jianzhi.entity.dto.ResumeDTO;
import com.zhou.jianzhi.entity.po.*;
import com.zhou.jianzhi.entity.vo.LoginUserInfoVO;
import com.zhou.jianzhi.mapper.JobseekerJobRelationMapper;
import com.zhou.jianzhi.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * jobseeker_job_relationService业务层处理
 *
 * @author zhou
 * @date 2021-01-09
 */
@Service
@Transactional
public class JobseekerJobRelationServiceImpl extends ServiceImpl<JobseekerJobRelationMapper, JobseekerJobRelation> implements IJobseekerJobRelationService {

    @Autowired
    private JobseekerJobRelationMapper jobseekerJobRelationMapper;

    @Autowired
    private IHrInfoService hrInfoService;

    @Autowired
    private IJobInfoService jobInfoService;

    @Autowired
    private IJobSeekerService jobSeekerService;

    @Autowired
    private IResumeService resumeService;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private IRecruitUnitService recruitUnitService;

    @Autowired
    private IAliyunOssService aliyunOssService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 根据jobseeker_job_relation表id查询
     *
     * @param id
     * @return
     */
    @Override
    public JobseekerJobRelation selectJobseekerJobRelationById(Integer id) {
        return this.jobseekerJobRelationMapper.selectById(id);
    }


    /**
     * 根据条件查询返回一条JobseekerJobRelation信息
     *
     * @param jobseekerJobRelationDTO
     * @return
     */
    @Override
    public JobseekerJobRelation selectOneJobseekerJobRelation(JobseekerJobRelationDTO jobseekerJobRelationDTO) {
        JobseekerJobRelation jobseekerJobRelation = new JobseekerJobRelation();
        BeanUtils.copyProperties(jobseekerJobRelationDTO, jobseekerJobRelation);
        QueryWrapper<JobseekerJobRelation> wrapper = new QueryWrapper<>();
        if (jobseekerJobRelation.getId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getId().toString()), "id", jobseekerJobRelation.getId());
        }
        if (jobseekerJobRelation.getJobSeekerId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getJobSeekerId().toString()), "job_seeker_id", jobseekerJobRelation.getJobSeekerId());
        }
        if (jobseekerJobRelation.getResumeId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getResumeId().toString()), "resume_id", jobseekerJobRelation.getResumeId());
        }
        if (jobseekerJobRelation.getRecruitUnitId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getRecruitUnitId().toString()), "recruit_unit_id", jobseekerJobRelation.getRecruitUnitId());
        }
        if (jobseekerJobRelation.getJobId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getJobId().toString()), "job_id", jobseekerJobRelation.getJobId());
        }
        wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getRemark()), "remark", jobseekerJobRelation.getRemark());
        wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getCreator()), "creator", jobseekerJobRelation.getCreator());
        wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getUpdator()), "updator", jobseekerJobRelation.getUpdator());
        return this.jobseekerJobRelationMapper.selectOne(wrapper);
    }


    /**
     * 查询JobseekerJobRelation列表
     *
     * @param jobseekerJobRelationDTO
     * @return
     */
    @Override
    public Page<JobseekerJobRelation> selectJobseekerJobRelationList(JobseekerJobRelationDTO jobseekerJobRelationDTO) throws Exception {
        //通过shiro框架拿到当前登录用户的信息
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();
        LoginUserInfoVO loginUserInfoVO = commonService.selectLoginUserInfoByLoginUserId(userId);

        //如果为hr登录
        if (loginUserInfoVO.getRecruitUnitId() != null) {
            jobseekerJobRelationDTO.setRecruitUnitId(loginUserInfoVO.getRecruitUnitId());
        }

        //如果为求职者登录
        if (loginUserInfoVO.getJobSeekerId() != null) {
            jobseekerJobRelationDTO.setJobSeekerId(loginUserInfoVO.getJobSeekerId());
        }


        //根据单位id查询
        Page<JobseekerJobRelation> page = new Page<>(jobseekerJobRelationDTO.getPageCurrent(), jobseekerJobRelationDTO.getPageSize());
        JobseekerJobRelation jobseekerJobRelation = new JobseekerJobRelation();
        BeanUtils.copyProperties(jobseekerJobRelationDTO, jobseekerJobRelation);
        QueryWrapper<JobseekerJobRelation> wrapper = new QueryWrapper<>();
        if (jobseekerJobRelation.getId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getId().toString()), "id", jobseekerJobRelation.getId());
        }
        if (jobseekerJobRelation.getJobSeekerId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getJobSeekerId().toString()), "job_seeker_id", jobseekerJobRelation.getJobSeekerId());
        }
        if (jobseekerJobRelation.getResumeId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getResumeId().toString()), "resume_id", jobseekerJobRelation.getResumeId());
        }
        if (jobseekerJobRelation.getRecruitUnitId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getRecruitUnitId().toString()), "recruit_unit_id", jobseekerJobRelation.getRecruitUnitId());
        }
        if (jobseekerJobRelation.getJobId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getJobId().toString()), "job_id", jobseekerJobRelation.getJobId());
        }
        if (jobseekerJobRelation.getStatus() != null) {
            wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getStatus().toString()), "status", jobseekerJobRelation.getStatus());
        }
        wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getRemark()), "remark", jobseekerJobRelation.getRemark());
        wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getCreator()), "creator", jobseekerJobRelation.getCreator());
        wrapper.eq(!StrUtil.isBlank(jobseekerJobRelation.getUpdator()), "updator", jobseekerJobRelation.getUpdator());
        Page<JobseekerJobRelation> Ipage = this.jobseekerJobRelationMapper.selectPage(page, wrapper);

        List<JobseekerJobRelation> records = Ipage.getRecords();
        for (JobseekerJobRelation jobseekerJobRelation1 : records) {
            //封装数据
            JobInfo jobInfo = jobInfoService.selectJobInfoById(jobseekerJobRelation1.getJobId());
            RecruitUnit recruitUnit = recruitUnitService.selectRecruitUnitById(jobInfo.getRecruitUnitId());
            System.out.println("ssssss");
            jobInfo.setRecruitUnit(recruitUnit);
            jobseekerJobRelation1.setJobInfo(jobInfo);
            JobSeeker jobSeeker = jobSeekerService.selectJobSeekerById(jobseekerJobRelation1.getJobSeekerId());

            //求职者头像
            AliyunOssDTO aliyunOssDTO = new AliyunOssDTO();
            aliyunOssDTO.setJobseekerId(jobSeeker.getId());
            aliyunOssDTO.setUsed(1);
            AjaxResult ajaxResult = aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
            if ((Integer) ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                AliyunOss data = (AliyunOss) ajaxResult.get("data");
                jobSeeker.setImgUrl("https://jianzhi-backet.oss-cn-shenzhen.aliyuncs.com/"+data.getFilePath());
            }

            jobseekerJobRelation1.setJobSeeker(jobSeeker);
            jobseekerJobRelation1.setResume(resumeService.selectById(jobseekerJobRelation1.getResumeId()));


        }

        return Ipage;
    }

    /**
     * 新增单个JobseekerJobRelation
     *
     * @param jobseekerJobRelationDTO
     * @return
     */
    @Override
    public AjaxResult addJobseekerJobRelation(JobseekerJobRelationDTO jobseekerJobRelationDTO) throws Exception {

         //判断是否投递过该职位
        //通过shiro框架拿到当前登录用户的信息
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();
        LoginUserInfoVO loginUserInfoVO1 = commonService.selectLoginUserInfoByLoginUserId(userId);
        jobseekerJobRelationDTO.setJobSeekerId(loginUserInfoVO1.getJobSeekerId());
        JobseekerJobRelation jobseekerJobRelation1 = selectOneJobseekerJobRelation(jobseekerJobRelationDTO);
        if (jobseekerJobRelation1 != null) {
            return AjaxResult.success("您已投递过该职位");
        }

        JobseekerJobRelation jobseekerJobRelation = new JobseekerJobRelation();
        BeanUtils.copyProperties(jobseekerJobRelationDTO, jobseekerJobRelation);

        //根据登录者id获取求职者的信息
        LoginUserInfoVO loginUserInfoVO = commonService.selectLoginUserInfoByLoginUserId(jobseekerJobRelationDTO.getUserId());
        jobseekerJobRelation.setJobSeekerId(loginUserInfoVO.getJobSeekerId());
        //查求职者简历id
         ResumeDTO resumeDTO = new ResumeDTO();
         resumeDTO.setJobSeekerId(loginUserInfoVO.getJobSeekerId());
         Resume resume = resumeService.selectOneResume(resumeDTO);
         if (resume!=null){
            jobseekerJobRelation.setResumeId(resume.getId());
         }

        jobseekerJobRelation.setStatus(1);
        int insert = this.jobseekerJobRelationMapper.insert(jobseekerJobRelation);

        //发消息
        //1 创建Rabbit管理器
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        //2 创建队列，每个用户都有自己的队列，通过用户id进行区分
        //查岗位发布者userid
        JobInfo jobInfo = jobInfoService.selectJobInfoById(jobseekerJobRelation.getJobId());
        if (jobInfo!=null){
            Queue queue = new Queue("resume_hrget_" + jobInfo.getCreator(), true);
            rabbitAdmin.declareQueue(queue);
            //3 发送消息
            rabbitTemplate.convertAndSend("resume_hrget_" + jobInfo.getCreator(),"zzzzzzz");
            System.out.println("ddddd");
        }



        if (insert == 1) {
            return AjaxResult.success("成功投递");
        }
        return AjaxResult.error();
    }

    /**
     * 批量新增JobseekerJobRelation
     *
     * @param jobseekerJobRelationDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addJobseekerJobRelationList(List<JobseekerJobRelationDTO> jobseekerJobRelationDTOList) throws Exception {
        //添加操作
        for (JobseekerJobRelationDTO jobseekerJobRelationDTO : jobseekerJobRelationDTOList) {
            JobseekerJobRelation jobseekerJobRelation = new JobseekerJobRelation();
            BeanUtils.copyProperties(jobseekerJobRelationDTO, jobseekerJobRelation);
            int insert = this.jobseekerJobRelationMapper.insert(jobseekerJobRelation);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改JobseekerJobRelation
     *
     * @param jobseekerJobRelationDTO
     * @return 结果
     */
    @Override
    public Integer updateJobseekerJobRelation(JobseekerJobRelationDTO jobseekerJobRelationDTO) {
        JobseekerJobRelation jobseekerJobRelation1 = selectJobseekerJobRelationById(jobseekerJobRelationDTO.getId());
        if (jobseekerJobRelationDTO.getStatus() != null) {
            jobseekerJobRelation1.setStatus(jobseekerJobRelationDTO.getStatus());
        }
        int updatedNum = this.jobseekerJobRelationMapper.updateById(jobseekerJobRelation1);
        return updatedNum;
    }

    /**
     * 根据id删除JobseekerJobRelation
     *
     * @param id 需要删除的jobseeker_job_relationID
     * @return 结果
     */
    @Override
    public AjaxResult deleteJobseekerJobRelationById(int id) {
        int delete = this.jobseekerJobRelationMapper.deleteById(id);
        if (delete < 0) {
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}








