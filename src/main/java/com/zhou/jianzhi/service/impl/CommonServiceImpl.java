package com.zhou.jianzhi.service.impl;

import com.zhou.jianzhi.entity.dto.HrInfoDTO;
import com.zhou.jianzhi.entity.dto.JobSeekerDTO;
import com.zhou.jianzhi.entity.po.HrInfo;
import com.zhou.jianzhi.entity.po.JobSeeker;
import com.zhou.jianzhi.entity.vo.LoginUserInfoVO;
import com.zhou.jianzhi.service.ICommonService;
import com.zhou.jianzhi.service.IHrInfoService;
import com.zhou.jianzhi.service.IJobSeekerService;
import com.zhou.jianzhi.service.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


/**
 *
 * @author zhou
 * @date 2021-01-09
 */
@Service
public class CommonServiceImpl  implements ICommonService {

    @Autowired
    private IJobSeekerService jobSeekerService;

    @Autowired
    private IResumeService resumeService;

    @Autowired
    @Lazy
    private IHrInfoService hrInfoService;

    @Override
    public LoginUserInfoVO selectLoginUserInfoByLoginUserId(Long id) throws Exception {
        LoginUserInfoVO loginUserInfoVO = new LoginUserInfoVO();

        //查求职者id
        JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
        jobSeekerDTO.setUserId(id);
        JobSeeker jobSeeker = jobSeekerService.selectOneJobSeeker(jobSeekerDTO);
        if (jobSeeker!=null){
            loginUserInfoVO.setJobSeekerId(jobSeeker.getId());
        }

        //拿到hr所在的单位id
        HrInfoDTO hrInfoDTO = new HrInfoDTO();
        hrInfoDTO.setUserId(id);
        HrInfo hrInfo = hrInfoService.selectOneHrInfo(hrInfoDTO);
        if (hrInfo!=null){
            loginUserInfoVO.setRecruitUnitId(hrInfo.getRecruitUnitId());
            loginUserInfoVO.setHrId(hrInfo.getId());
        }

        return loginUserInfoVO;
    }
}








