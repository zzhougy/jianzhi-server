package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.base.ActiveUser;
import com.zhou.jianzhi.entity.dto.AliyunOssDTO;
import com.zhou.jianzhi.entity.dto.ResumeDTO;
import com.zhou.jianzhi.entity.po.AliyunOss;
import com.zhou.jianzhi.entity.po.Resume;
import com.zhou.jianzhi.entity.vo.LoginUserInfoVO;
import com.zhou.jianzhi.mapper.ResumeMapper;
import com.zhou.jianzhi.service.IAliyunOssService;
import com.zhou.jianzhi.service.ICommonService;
import com.zhou.jianzhi.service.IResumeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * resumeService业务层处理
 *
 * @author zhou
 * @date 2021-01-03
 */
@Service
@Transactional
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements IResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    @Lazy
    private IAliyunOssService aliyunOssService;

    @Autowired
    private ICommonService commonService;

    @Override
    public Resume selectById(Integer id) throws Exception {
        Resume resume = this.resumeMapper.selectById(id);
        AliyunOssDTO aliyunOssDTO = new AliyunOssDTO();
        aliyunOssDTO.setJobseekerId(resume.getJobSeekerId());
        aliyunOssDTO.setUsed(2);
        AjaxResult ajaxResult = aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
        if ((Integer) ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
            AliyunOss aliyunOss = (AliyunOss)ajaxResult.get("data");
            resume.setResumeAttachUrl("https://jianzhi-backet.oss-cn-shenzhen.aliyuncs.com/"+aliyunOss.getFilePath());
        }
        return resume;
    }

    /**
     * 新增单个resume
     *
     * @param resumeDTO
     * @return
     */
    @Override
    public AjaxResult addResume(ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        BeanUtils.copyProperties(resumeDTO,resume);
        int insert = this.resumeMapper.insert(resume);
        if (insert==1){
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @Override
    public Resume selectOneResume(ResumeDTO resumeDTO) throws Exception {

        Resume resume = new Resume();
        BeanUtils.copyProperties(resumeDTO,resume);
        QueryWrapper<Resume> wrapper = new QueryWrapper<>();
        //如果没有传id查询登陆用户
        if (resumeDTO.getJobSeekerId()==null){
            //通过shiro框架拿到当前登录用户的信息
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            Long userId = activeUser.getUser().getId();
            LoginUserInfoVO loginUserInfoVO = commonService.selectLoginUserInfoByLoginUserId(userId);
            wrapper.eq("job_seeker_id",loginUserInfoVO.getJobSeekerId());
            resumeDTO.setJobSeekerId(loginUserInfoVO.getJobSeekerId());
        }
        if(resume.getId() != null){
            wrapper.eq(!StrUtil.isBlank(resume.getId().toString()),"id", resume.getId());
        }
        if(resume.getJobSeekerId() != null){
            wrapper.eq(!StrUtil.isBlank(resume.getJobSeekerId().toString()),"job_seeker_id", resume.getJobSeekerId());
        }
        wrapper.eq(!StrUtil.isBlank(resume.getResName()),"res_name",resume.getResName());
        wrapper.eq(!StrUtil.isBlank(resume.getResSex()),"res_sex",resume.getResSex());
        wrapper.eq(!StrUtil.isBlank(resume.getResExpectWork()),"res_expect_work",resume.getResExpectWork());
        wrapper.eq(!StrUtil.isBlank(resume.getResWorkExper()),"res_work_exper",resume.getResWorkExper());
        wrapper.eq(!StrUtil.isBlank(resume.getResProjectExper()),"res_project_exper",resume.getResProjectExper());
        wrapper.eq(!StrUtil.isBlank(resume.getResEmail()),"res_email",resume.getResEmail());
        wrapper.eq(!StrUtil.isBlank(resume.getResPhone()),"res_phone",resume.getResPhone());
        wrapper.eq(!StrUtil.isBlank(resume.getResDesc()),"res_desc",resume.getResDesc());
        wrapper.eq(!StrUtil.isBlank(resume.getCreator()),"creator",resume.getCreator());
        wrapper.eq(!StrUtil.isBlank(resume.getUpdator()),"updator",resume.getUpdator());
        wrapper.eq(!StrUtil.isBlank(resume.getRemark()),"remark",resume.getRemark());
        Resume resume1 = resumeMapper.selectOne(wrapper);

        //查询附件简历
        AliyunOssDTO aliyunOssDTO = new AliyunOssDTO();
        aliyunOssDTO.setJobseekerId(resume1.getJobSeekerId());
        aliyunOssDTO.setUsed(2);
        AjaxResult ajaxResult = aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
        if ((Integer)ajaxResult.get("code")==AjaxResult.Type.SUCCESS.value()){
            resume1.setAliyunOss((AliyunOss)ajaxResult.get("data"));
        }
        return resume1;
    }

    /**
     * 查询resume列表
     *
     * @param resumeDTO
     * @return
     */
    @Override
    public Page<Resume> selectResumeList(ResumeDTO resumeDTO) throws Exception {
        Page<Resume> page = new Page<>(resumeDTO.getPageCurrent(),resumeDTO.getPageSize());
        Resume resume = new Resume();
        BeanUtils.copyProperties(resumeDTO,resume);
        QueryWrapper<Resume> wrapper = new QueryWrapper<>();
        if(resume.getId() != null){
            wrapper.eq(!StrUtil.isBlank(resume.getId().toString()),"id", resume.getId());
        }
        if(resume.getJobSeekerId() != null){
            wrapper.eq(!StrUtil.isBlank(resume.getJobSeekerId().toString()),"job_seeker_id", resume.getJobSeekerId());
        }
        wrapper.eq(!StrUtil.isBlank(resume.getResName()),"res_name",resume.getResName());
        wrapper.eq(!StrUtil.isBlank(resume.getResSex()),"res_sex",resume.getResSex());
        wrapper.eq(!StrUtil.isBlank(resume.getResExpectWork()),"res_expect_work",resume.getResExpectWork());
        wrapper.eq(!StrUtil.isBlank(resume.getResWorkExper()),"res_work_exper",resume.getResWorkExper());
        wrapper.eq(!StrUtil.isBlank(resume.getResProjectExper()),"res_project_exper",resume.getResProjectExper());
        wrapper.eq(!StrUtil.isBlank(resume.getResEmail()),"res_email",resume.getResEmail());
        wrapper.eq(!StrUtil.isBlank(resume.getResPhone()),"res_phone",resume.getResPhone());
        wrapper.eq(!StrUtil.isBlank(resume.getResDesc()),"res_desc",resume.getResDesc());
        wrapper.eq(!StrUtil.isBlank(resume.getCreator()),"creator",resume.getCreator());
        wrapper.eq(!StrUtil.isBlank(resume.getUpdator()),"updator",resume.getUpdator());
        wrapper.eq(!StrUtil.isBlank(resume.getRemark()),"remark",resume.getRemark());
        Page<Resume> Ipage = this.resumeMapper.selectPage(page, wrapper);

        return Ipage;
    }


    /**
     * 批量新增resume
     *
     * @param resumeDTOList
     * @return 结果
     */
    @Override
    public AjaxResult insertResumeList(List<ResumeDTO> resumeDTOList) throws Exception {
        //添加操作
        for (ResumeDTO resumeDTO : resumeDTOList){
            Resume resume = new Resume();
            BeanUtils.copyProperties(resumeDTO,resume);
            int insert = this.resumeMapper.insert(resume);
        }
        return AjaxResult.success();
    }

    /**
     * 修改resume
     *
     * @param resumeDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateResume(ResumeDTO resumeDTO){
        Resume resume = new Resume();
        BeanUtils.copyProperties(resumeDTO,resume);
        int update = this.resumeMapper.updateById(resume);
        if(update > 0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    /**
     * 根据id删除resume
     *
     * @param id 需要删除的resumeID
     * @return 结果
     */
    @Override
    public AjaxResult deleteResumeById(int id)
    {
        int delete = this.resumeMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }



}








