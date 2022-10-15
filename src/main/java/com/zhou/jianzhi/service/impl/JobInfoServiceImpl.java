package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.base.ActiveUser;
import com.zhou.jianzhi.entity.dto.HrInfoDTO;
import com.zhou.jianzhi.entity.dto.JobInfoDTO;
import com.zhou.jianzhi.entity.po.*;
import com.zhou.jianzhi.entity.vo.LoginUserInfoVO;
import com.zhou.jianzhi.mapper.JobInfoMapper;
import com.zhou.jianzhi.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * job_infoService业务层处理
 *
 * @author zhou
 * @date 2021-01-09
 */
@Service
@Transactional
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfo> implements IJobInfoService {

    @Autowired
    private JobInfoMapper jobInfoMapper;

    @Autowired
    private IHrInfoService hrInfoService;

    @Autowired
    private ICommonService commonService;

    @Autowired
    @Lazy
    private IRecruitUnitService recruitUnitService;

    @Autowired
    private IJobLabelService jobLabelService;


    /**
     * 根据job_info表id查询
     *
     * @param id
     * @return
     */
    @Override
    public JobInfo selectJobInfoById(Integer id) throws Exception {
        JobInfo jobInfo = this.jobInfoMapper.selectById(id);
        RecruitUnit recruitUnit = recruitUnitService.selectRecruitUnitById(jobInfo.getRecruitUnitId());
        jobInfo.setRecruitUnit(recruitUnit);
        //转换label成List
        String label = jobInfo.getLabel();
        if (label!=null&&!label.isEmpty()){
            String[] newLabel = label.substring(1, label.length() - 1).split("}\\{");
            List<String> strings = Arrays.asList(newLabel);
            List<Integer> codesInteger = strings.stream().map(Integer::parseInt).collect(Collectors.toList());
            jobInfo.setLabelList(codesInteger);
        }
        jobInfo.setLabelStr(getLabelStr(label));
        return jobInfo;
    }

    /**
     * 查询job_info列表
     *
     * @param jobInfoDTO
     * @return
     */
    @Override
    public Page<JobInfo> selectJobInfoList(JobInfoDTO jobInfoDTO) throws Exception {
        Page<JobInfo> page = new Page<>(jobInfoDTO.getPageCurrent(),jobInfoDTO.getPageSize());
        JobInfo jobInfo = new JobInfo();
        BeanUtils.copyProperties(jobInfoDTO,jobInfo);
        QueryWrapper<JobInfo> wrapper = new QueryWrapper<>();
        if(jobInfo.getId() != null){
            wrapper.eq(!StrUtil.isBlank(jobInfo.getId().toString()),"id", jobInfo.getId());
        }

        if (jobInfoDTO.getUserId()!=null ){
            HrInfoDTO hrInfoDTO = new HrInfoDTO();
            hrInfoDTO.setUserId(jobInfoDTO.getUserId());
            HrInfo hrInfo = hrInfoService.selectOneHrInfo(hrInfoDTO);
            if (hrInfo!=null){
                wrapper.eq("recruit_unit_id",hrInfo.getRecruitUnitId());
            }
        }
        if (jobInfo.getRecruitUnitId() != null) {
            wrapper.eq(!StrUtil.isBlank(jobInfo.getRecruitUnitId().toString()), "recruit_unit_id", jobInfo.getRecruitUnitId());
        }
        //wrapper.eq(!StrUtil.isBlank(jobInfo.getJobName()),"job_name",jobInfo.getJobName());
        wrapper.like(!StrUtil.isBlank(jobInfo.getJobName()),"job_name",jobInfo.getJobName());
        wrapper.eq(!StrUtil.isBlank(jobInfo.getWorkTime()),"work_time",jobInfo.getWorkTime());
        wrapper.eq(!StrUtil.isBlank(jobInfo.getLocation()),"location",jobInfo.getLocation());

        //根据标签查询
        if(!StrUtil.isBlank(jobInfo.getLabel())){
            ArrayList arrayList = JSONObject.parseObject(jobInfo.getLabel(), ArrayList.class);
            //0代表查询所有，
            if ((Integer) arrayList.get(0)!=0){
                for (int i = 0; i < arrayList.size(); i++) {
                    StringBuffer stringBuffer = new StringBuffer("{");
                    stringBuffer.append(arrayList.get(i)).append("}");
                    String string = String.valueOf(stringBuffer);
                    wrapper.like("label",string);
                    if (i!=arrayList.size()-1){
                        wrapper.or();
                    }
                }
            }
        }

        wrapper.eq(!StrUtil.isBlank(jobInfo.getDetail()),"detail",jobInfo.getDetail());
        wrapper.eq(!StrUtil.isBlank(jobInfo.getRequirement()),"requirement",jobInfo.getRequirement());
        wrapper.eq(!StrUtil.isBlank(jobInfo.getSalaryTreatment()),"salary_treatment",jobInfo.getSalaryTreatment());
        if (jobInfo.getStatus() != null) {
            wrapper.eq(!StrUtil.isBlank(jobInfo.getStatus().toString()), "status", jobInfo.getStatus());
        }
        wrapper.eq(!StrUtil.isBlank(jobInfo.getCreator()),"creator",jobInfo.getCreator());
        wrapper.eq(!StrUtil.isBlank(jobInfo.getUpdator()),"updator",jobInfo.getUpdator());
        wrapper.eq(!StrUtil.isBlank(jobInfo.getRemark()),"remark",jobInfo.getRemark());
        Page<JobInfo> Ipage = this.jobInfoMapper.selectPage(page, wrapper);
        //根据招聘单位id设置招聘单位对象
        if (Ipage.getRecords().size()!=0){
            for (JobInfo jobInfo1 :Ipage.getRecords()){
                RecruitUnit recruitUnit = recruitUnitService.selectRecruitUnitById(jobInfo1.getRecruitUnitId());
                jobInfo1.setRecruitUnit(recruitUnit);
                //start将标签{1}转换成字符串
                String label = jobInfo1.getLabel();
                StringBuffer newLabel = new StringBuffer();

                if (label!=null&&!label.isEmpty()){
                    String[] split = label.substring(1, label.length() - 1).split("}\\{");
                    List<String> strings = Arrays.asList(split);
                    List<Integer> codesInteger = strings.stream().map(Integer::parseInt).collect(Collectors.toList());

                    for (Integer id : codesInteger){
                        AjaxResult ajaxResult = jobLabelService.selectJobLabelById(id);
                        if ((Integer)ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                            JobLabel jobLabel = (JobLabel) ajaxResult.get("data");
                            newLabel.append(jobLabel.getName());
                            newLabel.append(",");
                        }
                    }
                }
                jobInfo1.setLabel(newLabel.substring(0,newLabel.length()-1));
                //end将标签{1}转换成字符串

            }

        }
        return Ipage;
    }

    /**
    * 新增单个JobInfo
    *
    * @param jobInfoDTO
    * @return
    */
    @Override
    public Integer addJobInfo(JobInfoDTO jobInfoDTO) throws Exception {
        //通过shiro框架拿到当前登录用户的信息
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();
        LoginUserInfoVO loginUserInfoVO = commonService.selectLoginUserInfoByLoginUserId(userId);


        JobInfo jobInfo = new JobInfo();
        BeanUtils.copyProperties(jobInfoDTO,jobInfo);
        if (jobInfoDTO.getLabel()!=null){
            ArrayList arrayList = JSONObject.parseObject(jobInfoDTO.getLabel(), ArrayList.class);
            StringBuffer stringBuffer = null;
            for (int i = 0; i < arrayList.size(); i++) {
                stringBuffer = new StringBuffer();
                stringBuffer.append("{").append(arrayList.get(i)).append("}");
            }
            jobInfo.setLabel(String.valueOf(stringBuffer));
        }

        jobInfo.setStatus("1");
        jobInfo.setRecruitUnitId(commonService.selectLoginUserInfoByLoginUserId(jobInfoDTO.getUserId()).getRecruitUnitId());

        //设置创建人
        jobInfo.setCreator(activeUser.getUser().getId().toString());


        int insertedNum = this.jobInfoMapper.insert(jobInfo);
        return insertedNum;



    }

    /**
     * 批量新增job_info
     *
     * @param jobInfoDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addJobInfoList(List<JobInfoDTO> jobInfoDTOList) throws Exception {
        //添加操作
        for (JobInfoDTO jobInfoDTO : jobInfoDTOList){
            JobInfo jobInfo = new JobInfo();
            BeanUtils.copyProperties(jobInfoDTO,jobInfo);
            int insert = this.jobInfoMapper.insert(jobInfo);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改job_info
     *
     * @param jobInfoDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateJobInfo(JobInfoDTO jobInfoDTO) throws Exception {
        //通过shiro框架拿到当前登录用户的信息
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();
        LoginUserInfoVO loginUserInfoVO = commonService.selectLoginUserInfoByLoginUserId(userId);

        JobInfo jobInfo = selectJobInfoById(jobInfoDTO.getId());
        if (jobInfo!=null){
            if(jobInfoDTO.getId() != null){
                jobInfo.setId(jobInfoDTO.getId());
            }
            if(jobInfoDTO.getRecruitUnitId() != null){
                jobInfo.setRecruitUnitId(jobInfoDTO.getRecruitUnitId());
            }
            if(jobInfoDTO.getJobName() != null && !jobInfoDTO.getJobName().isEmpty()){
                jobInfo.setJobName(jobInfoDTO.getJobName());
            }
            if(jobInfoDTO.getWorkTime() != null && !jobInfoDTO.getWorkTime().isEmpty()){
                jobInfo.setWorkTime(jobInfoDTO.getWorkTime());
            }
            if(jobInfoDTO.getLocation() != null && !jobInfoDTO.getLocation().isEmpty()){
                jobInfo.setLocation(jobInfoDTO.getLocation());
            }
            if(jobInfoDTO.getLabel() != null && !jobInfoDTO.getLabel().isEmpty()){
                StringBuffer stringBuffer = new StringBuffer();
                String string = null;
                ArrayList arrayList = JSONObject.parseObject(jobInfoDTO.getLabel(), ArrayList.class);
                for (int i = 0; i < arrayList.size(); i++) {
                    stringBuffer.append("{");
                    stringBuffer.append(arrayList.get(i)).append("}");
                    string = String.valueOf(stringBuffer);
                }
                jobInfo.setLabel(string);
            }
            if(jobInfoDTO.getDetail() != null && !jobInfoDTO.getDetail().isEmpty()){
                jobInfo.setDetail(jobInfoDTO.getDetail());
            }
            if(jobInfoDTO.getRequirement() != null && !jobInfoDTO.getRequirement().isEmpty()){
                jobInfo.setRequirement(jobInfoDTO.getRequirement());
            }
            if(jobInfoDTO.getSalaryTreatment() != null && !jobInfoDTO.getSalaryTreatment().isEmpty()){
                jobInfo.setSalaryTreatment(jobInfoDTO.getSalaryTreatment());
            }
            if(jobInfoDTO.getStatus() != null && !jobInfoDTO.getStatus().isEmpty()){
                jobInfo.setStatus(jobInfoDTO.getStatus());
            }
            if(jobInfoDTO.getNeedResumeAttachment() != null){
                jobInfo.setNeedResumeAttachment(jobInfoDTO.getNeedResumeAttachment().toString());
            }
            if(jobInfoDTO.getCreator() != null && !jobInfoDTO.getCreator().isEmpty()){
                jobInfo.setCreator(jobInfoDTO.getCreator());
            }
            if(jobInfoDTO.getUpdator() != null && !jobInfoDTO.getUpdator().isEmpty()){
                jobInfo.setUpdator(jobInfoDTO.getUpdator());
            }
            if(jobInfoDTO.getRemark() != null && !jobInfoDTO.getRemark().isEmpty()){
                jobInfo.setRemark(jobInfoDTO.getRemark());
            }

            //设置更新人
            jobInfo.setUpdator(userId.toString());

            int updatedNum = this.jobInfoMapper.updateById(jobInfo);
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
     * 根据id删除job_info
     *
     * @param id 需要删除的job_infoID
     * @return 结果
     */
    @Override
    public AjaxResult deleteJobInfoById(int id)
    {
        int delete = this.jobInfoMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


    //将标签{1}转换成字符串
    private String getLabelStr(String oldLabel) {
        StringBuffer newLabel = new StringBuffer();
        if (oldLabel!=null&&!oldLabel.isEmpty()){
            String[] split = oldLabel.substring(1, oldLabel.length() - 1).split("}\\{");
            List<String> strings = Arrays.asList(split);
            List<Integer> codesInteger = strings.stream().map(Integer::parseInt).collect(Collectors.toList());
            for (Integer id : codesInteger){
                AjaxResult ajaxResult1 = jobLabelService.selectJobLabelById(id);
                if ((Integer)ajaxResult1.get("code") == AjaxResult.Type.SUCCESS.value()){
                    JobLabel jobLabel = (JobLabel) ajaxResult1.get("data");
                    newLabel.append(jobLabel.getName());
                    newLabel.append(",");
                }
            }
        }
        return newLabel.substring(0,newLabel.length()-1);
    }

}








