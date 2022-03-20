package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.base.ActiveUser;
import com.zhou.jianzhi.entity.dto.AliyunOssDTO;
import com.zhou.jianzhi.entity.dto.HrInfoDTO;
import com.zhou.jianzhi.entity.dto.JobInfoDTO;
import com.zhou.jianzhi.entity.dto.RecruitUnitDTO;
import com.zhou.jianzhi.entity.po.*;
import com.zhou.jianzhi.mapper.RecruitUnitMapper;
import com.zhou.jianzhi.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * recruitment_unitService业务层处理
 *
 * @author zhou
 * @date 2021-01-09
 */
@Service
@Transactional
public class RecruitUnitServiceImpl extends ServiceImpl<RecruitUnitMapper, RecruitUnit> implements IRecruitUnitService {

    @Autowired
    private RecruitUnitMapper recruitUnitMapper;

    @Autowired
    private IHrInfoService hrInfoService;

    @Autowired
    private IJobSeekerService jobSeekerService;

    @Autowired
    private IAliyunOssService aliyunOssService;

    @Autowired
    private IRecruitUnitLabelService recruitUnitLabelService;

    @Autowired
    private IJobInfoService jobInfoService;

    /**
     * 根据recruitment_unit表id查询
     *
     * @param id
     * @return
     */
    @Override
    public RecruitUnit selectRecruitUnitById(Integer id)  {
        try {
            System.out.println(id);


            RecruitUnit recruitUnit = this.recruitUnitMapper.selectById(id);
            AliyunOssDTO aliyunOssDTO = new AliyunOssDTO();
            aliyunOssDTO.setRecruitUnitId(id);
            aliyunOssDTO.setUsed(1);
            AjaxResult ajaxResult = aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
            if ((Integer)ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                AliyunOss data = (AliyunOss) ajaxResult.get("data");
                recruitUnit.setUnitImageUrl("https://jianzhi-backet.oss-cn-shenzhen.aliyuncs.com/"+data.getFilePath());
            }
            //将标签{1}转换成字符串
            String label = recruitUnit.getField();
            System.out.println(label);
            String fieldStr = getFieldStr(label);
            //不用recruitUnit.setField(fieldStr)，因为有一级缓存存在
            recruitUnit.setFieldStr(fieldStr);

            return recruitUnit;
        }catch (Exception e){
            System.out.println("'dddddddddd");
        }

       return null;


    }
	
	
	/**
     * 根据条件查询返回一条RecruitUnit信息
     * @param recruitUnitDTO
     * @return
     */
    @Override
    public RecruitUnit selectOneRecruitUnit(RecruitUnitDTO recruitUnitDTO) {
        //通过shiro框架拿到用户的信息
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();

        //根据用户id拿到hr所在的单位id
        HrInfoDTO hrInfoDTO = new HrInfoDTO();
        hrInfoDTO.setUserId(userId);
        HrInfo hrInfo = hrInfoService.selectOneHrInfo(hrInfoDTO);
        recruitUnitDTO.setId(hrInfo.getRecruitUnitId());

        RecruitUnit recruitUnit = new RecruitUnit();
        BeanUtils.copyProperties(recruitUnitDTO,recruitUnit);
        QueryWrapper<RecruitUnit> wrapper = new QueryWrapper<>();
        if(recruitUnit.getId() != null){
            wrapper.eq(!StrUtil.isBlank(recruitUnit.getId().toString()),"id", recruitUnit.getId());
        }
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getName()),"name",recruitUnit.getName());
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getLocation()),"location",recruitUnit.getLocation());
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getField()),"field",recruitUnit.getField());
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getScale()),"scale",recruitUnit.getScale());
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getProfile()),"profile",recruitUnit.getProfile());
        if(recruitUnit.getStatus() != null){
            wrapper.eq(!StrUtil.isBlank(recruitUnit.getStatus().toString()),"status", recruitUnit.getStatus());
        }
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getCreator()),"creator",recruitUnit.getCreator());
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getUpdator()),"updator",recruitUnit.getUpdator());
        return this.recruitUnitMapper.selectOne(wrapper);
    }
	

    /**
     * 查询RecruitUnit列表
     *
     * @param recruitUnitDTO
     * @return
     */
    @Override
    public Page<RecruitUnit> selectRecruitUnitList(RecruitUnitDTO recruitUnitDTO) throws Exception {
        Page<RecruitUnit> page = new Page<>(recruitUnitDTO.getPageCurrent(),recruitUnitDTO.getPageSize());
        RecruitUnit recruitUnit = new RecruitUnit();
        BeanUtils.copyProperties(recruitUnitDTO,recruitUnit);
        QueryWrapper<RecruitUnit> wrapper = new QueryWrapper<>();
        if(recruitUnit.getId() != null){
            wrapper.eq(!StrUtil.isBlank(recruitUnit.getId().toString()),"id", recruitUnit.getId());
        }
        wrapper.like(!StrUtil.isBlank(recruitUnit.getName()),"name",recruitUnit.getName());
//        wrapper.like("name",)

        wrapper.eq(!StrUtil.isBlank(recruitUnit.getLocation()),"location",recruitUnit.getLocation());
        //wrapper.eq(!StrUtil.isBlank(recruitUnit.getField()),"field",recruitUnit.getField());

        //根据标签查询
        if(!StrUtil.isBlank(recruitUnit.getField())){
            ArrayList arrayList = JSONObject.parseObject(recruitUnit.getField(), ArrayList.class);
            //0代表查询所有，
            if ((Integer) arrayList.get(0)!=0){
                for (int i = 0; i < arrayList.size(); i++) {
                    StringBuffer stringBuffer = new StringBuffer("{");
                    stringBuffer.append(arrayList.get(i)).append("}");
                    String string = String.valueOf(stringBuffer);
                    wrapper.like("field",string);
                    if (i!=arrayList.size()-1){
                        wrapper.or();
                    }
                }
            }
        }

        wrapper.eq(!StrUtil.isBlank(recruitUnit.getScale()),"scale",recruitUnit.getScale());
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getProfile()),"profile",recruitUnit.getProfile());
        if(recruitUnit.getStatus() != null){
            wrapper.eq(!StrUtil.isBlank(recruitUnit.getStatus().toString()),"status", recruitUnit.getStatus());
        }
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getCreator()),"creator",recruitUnit.getCreator());
        wrapper.eq(!StrUtil.isBlank(recruitUnit.getUpdator()),"updator",recruitUnit.getUpdator());
        Page<RecruitUnit> Ipage = this.recruitUnitMapper.selectPage(page, wrapper);
        List<RecruitUnit> records = Ipage.getRecords();
        for (RecruitUnit recruitUnit1:records){
            AliyunOssDTO aliyunOssDTO = new AliyunOssDTO();
            aliyunOssDTO.setRecruitUnitId(recruitUnit1.getId());
            aliyunOssDTO.setUsed(1);
            AjaxResult ajaxResult = aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
            if ((Integer)ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                AliyunOss data = (AliyunOss) ajaxResult.get("data");
                recruitUnit1.setUnitImageUrl("https://jianzhi-backet.oss-cn-shenzhen.aliyuncs.com/"+data.getFilePath());
            }

            //将标签{1}转换成字符串
            String label = recruitUnit1.getField();
            String fieldStr = getFieldStr(label);
            recruitUnit1.setField(fieldStr);

            //查询在招岗位数量
            JobInfoDTO jobInfoDTO = new JobInfoDTO();
            jobInfoDTO.setRecruitUnitId(recruitUnit1.getId());
            jobInfoDTO.setStatus("1");
            Page<JobInfo> jobInfoPage = jobInfoService.selectJobInfoList(jobInfoDTO);
            recruitUnit1.setJobNum(jobInfoPage.getTotal());

        }
        return Ipage;
    }


    //将标签{1}转换成字符串
    private String getFieldStr(String oldLabel) {
        StringBuffer newLabel = new StringBuffer();
        if (oldLabel!=null&&!oldLabel.isEmpty()){
            String[] split = oldLabel.substring(1, oldLabel.length() - 1).split("}\\{");
            List<String> strings = Arrays.asList(split);
            List<Integer> codesInteger = strings.stream().map(Integer::parseInt).collect(Collectors.toList());
            for (Integer id : codesInteger){
                AjaxResult ajaxResult1 = recruitUnitLabelService.selectRecruitUnitLabelById(id);
                if ((Integer)ajaxResult1.get("code") == AjaxResult.Type.SUCCESS.value()){
                    RecruitUnitLabel recruitUnitLabel = (RecruitUnitLabel) ajaxResult1.get("data");
                    newLabel.append(recruitUnitLabel.getName());
                    newLabel.append(",");
                }
            }
        }
        return newLabel.substring(0,newLabel.length()-1);

    }

    /**
    * 新增单个RecruitUnit
    *
    * @param recruitUnitDTO
    * @return
    */
    @Override
    public AjaxResult addRecruitUnit(RecruitUnitDTO recruitUnitDTO) {
        RecruitUnit recruitUnit = new RecruitUnit();
        BeanUtils.copyProperties(recruitUnitDTO,recruitUnit);
        recruitUnit.setStatus(1);//修改状态为审核中
        //通过shiro框架拿到当前登录用户的信息
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();
        HrInfoDTO hrInfoDTO1 = new HrInfoDTO();
        hrInfoDTO1.setUserId(userId);
        HrInfo hrInfo = hrInfoService.selectOneHrInfo(hrInfoDTO1);
        recruitUnit.setHrId(hrInfo.getId());
        int insert = this.recruitUnitMapper.insert(recruitUnit);
        if (insert==1){
            //添加hr表关联单位id
            HrInfoDTO hrInfoDTO = new HrInfoDTO();
            hrInfoDTO.setUserId(userId);
            hrInfoDTO.setRecruitUnitId(recruitUnit.getId());
            AjaxResult ajaxResult = hrInfoService.addHrInfo(hrInfoDTO);
            if ((Integer)ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                return AjaxResult.success();
            }
        }
        return AjaxResult.error();
    }

    /**
     * 批量新增RecruitUnit
     *
     * @param recruitUnitDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addRecruitUnitList(List<RecruitUnitDTO> recruitUnitDTOList) throws Exception {
        //添加操作
        for (RecruitUnitDTO recruitUnitDTO : recruitUnitDTOList){
            RecruitUnit recruitUnit = new RecruitUnit();
            BeanUtils.copyProperties(recruitUnitDTO,recruitUnit);
            int insert = this.recruitUnitMapper.insert(recruitUnit);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改RecruitUnit
     *
     * @param recruitUnitDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateRecruitUnit(RecruitUnitDTO recruitUnitDTO){
        RecruitUnit recruitUnit = new RecruitUnit();
        BeanUtils.copyProperties(recruitUnitDTO,recruitUnit);
        int update = this.recruitUnitMapper.updateById(recruitUnit);
        if(update > 0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    /**
     * 根据id删除RecruitUnit
     *
     * @param id 需要删除的recruitment_unitID
     * @return 结果
     */
    @Override
    public AjaxResult deleteRecruitUnitById(int id)
    {
        int delete = this.recruitUnitMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}








