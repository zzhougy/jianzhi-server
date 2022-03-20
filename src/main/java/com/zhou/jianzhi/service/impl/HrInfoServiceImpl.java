package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.base.ActiveUser;
import com.zhou.jianzhi.entity.dto.HrInfoDTO;
import com.zhou.jianzhi.entity.dto.RecruitUnitDTO;
import com.zhou.jianzhi.entity.po.BaseUser;
import com.zhou.jianzhi.entity.po.HrInfo;
import com.zhou.jianzhi.entity.po.RecruitUnit;
import com.zhou.jianzhi.entity.vo.LoginUserInfoVO;
import com.zhou.jianzhi.mapper.HrInfoMapper;
import com.zhou.jianzhi.service.BaseUserService;
import com.zhou.jianzhi.service.ICommonService;
import com.zhou.jianzhi.service.IHrInfoService;
import com.zhou.jianzhi.service.IRecruitUnitService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * hr_infoService业务层处理
 *
 * @author zhou
 * @date 2021-01-09
 */
@Service
@Transactional
public class HrInfoServiceImpl extends ServiceImpl<HrInfoMapper, HrInfo> implements IHrInfoService {

    @Autowired
    private HrInfoMapper hrInfoMapper;

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    @Lazy
    private IRecruitUnitService recruitUnitService;

    @Autowired
    @Lazy
    private ICommonService commonService;


    /**
     * 根据hr_info表id查询
     *
     * @param id
     * @return
     */
    @Override
    public HrInfo selectHrInfoById(Long id) {
        return this.hrInfoMapper.selectById(id);
    }

    /**
     * 查询hr_info列表
     *
     * @param hrInfoDTO
     * @return
     */
    @Override
    public AjaxResult selectHrInfoList(HrInfoDTO hrInfoDTO) throws Exception {
        Page<HrInfo> page = new Page<>(hrInfoDTO.getPageCurrent(),hrInfoDTO.getPageSize());
        HrInfo hrInfo = new HrInfo();
        BeanUtils.copyProperties(hrInfoDTO,hrInfo);
        QueryWrapper<HrInfo> wrapper = new QueryWrapper<>();
        if(hrInfo.getId() != null){
            wrapper.eq(!StrUtil.isBlank(hrInfo.getId().toString()),"id", hrInfo.getId());
        }
        if(hrInfo.getUserId() != null){
            wrapper.eq(!StrUtil.isBlank(hrInfo.getUserId().toString()),"user_id", hrInfo.getUserId());
        }
        wrapper.eq(!StrUtil.isBlank(hrInfo.getName()),"name",hrInfo.getName());


        //通过shiro框架拿到用户的信息
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        //判断当前登录用户身份 2为Hr
        if(activeUser.getUser().getType() == 2){
            //查找当前登录用户的单位id
            LoginUserInfoVO loginUserInfoVO = commonService.selectLoginUserInfoByLoginUserId(activeUser.getUser().getId());
            //判断当前登录hr是否为负责人
            RecruitUnitDTO recruitUnitDTO = new RecruitUnitDTO();
            recruitUnitDTO.setId(loginUserInfoVO.getRecruitUnitId());
            recruitUnitDTO.setHrId(loginUserInfoVO.getHrId());
            RecruitUnit recruitUnit = recruitUnitService.selectOneRecruitUnit(recruitUnitDTO);
            if (recruitUnit==null){
                return AjaxResult.error("不是负责人，没有权限。");
            }

            wrapper.eq(!StrUtil.isBlank(loginUserInfoVO.getRecruitUnitId().toString()),"recruit_unit_id", loginUserInfoVO.getRecruitUnitId());
        }else {
            if(hrInfo.getRecruitUnitId() != null){
                wrapper.eq(!StrUtil.isBlank(hrInfo.getRecruitUnitId().toString()),"recruit_unit_id", hrInfo.getRecruitUnitId());
            }
        }
        Page<HrInfo> Ipage = this.hrInfoMapper.selectPage(page, wrapper);
        List<HrInfo> records = Ipage.getRecords();
        for (HrInfo hrInfo1 : records){
            BaseUser baseUser = baseUserService.selectById(hrInfo1.getUserId());
            if (baseUser!=null){
                hrInfo1.setUsername(baseUser.getUsername());
                hrInfo1.setCreateTime(baseUser.getCreateTime());
            }

            if(hrInfo1.getRecruitUnitId()!=null){
                hrInfo1.setUnitName(recruitUnitService.selectRecruitUnitById(hrInfo1.getRecruitUnitId()).getName());
            }
        }
        return AjaxResult.success(Ipage);
    }

    /**
    * 新增单个HrInfo
    *
    * @param hrInfoDTO
    * @return
    */
    @Override
    public AjaxResult addHrInfo(HrInfoDTO hrInfoDTO) {
        HrInfo hrInfo = new HrInfo();
        BeanUtils.copyProperties(hrInfoDTO,hrInfo);
        int insert = this.hrInfoMapper.insert(hrInfo);
        if (insert==1){
        return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 批量新增hr_info
     *
     * @param hrInfoDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addHrInfoList(List<HrInfoDTO> hrInfoDTOList) throws Exception {
        //添加操作
        for (HrInfoDTO hrInfoDTO : hrInfoDTOList){
            HrInfo hrInfo = new HrInfo();
            BeanUtils.copyProperties(hrInfoDTO,hrInfo);
            int insert = this.hrInfoMapper.insert(hrInfo);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改hr_info
     *
     * @param hrInfoDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateHrInfo(HrInfoDTO hrInfoDTO){
        HrInfo hrInfo = this.hrInfoMapper.selectById(hrInfoDTO.getId());
        if(hrInfoDTO.getId() != null){
            hrInfo.setId(hrInfoDTO.getId());
        }
        if(hrInfoDTO.getUserId() != null){
            hrInfo.setUserId(hrInfoDTO.getUserId());
        }
        if(hrInfoDTO.getName() != null && !hrInfoDTO.getName().isEmpty()){
            hrInfo.setName(hrInfoDTO.getName());
        }
        if(hrInfoDTO.getSex() != null && !hrInfoDTO.getSex().isEmpty()){
            hrInfo.setSex(hrInfoDTO.getSex());
        }
        if(hrInfoDTO.getRecruitUnitId() != null){
            hrInfo.setRecruitUnitId(hrInfoDTO.getRecruitUnitId());
        }
        int update = this.hrInfoMapper.updateById(hrInfo);
        if(update > 0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    /**
     * 根据id删除hr_info
     *
     * @param id 需要删除的hr_infoID
     * @return 结果
     */
    @Override
    public AjaxResult deleteHrInfoById(int id)
    {
        int delete = this.hrInfoMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


    /**
     * 根据条件查询返回一条hr_info
     * @param hrInfoDTO
     * @return
     */
    @Override
    public HrInfo selectOneHrInfo(HrInfoDTO hrInfoDTO) {
        ActiveUser activeUser = null;
        HrInfo hrInfo = new HrInfo();
        BeanUtils.copyProperties(hrInfoDTO,hrInfo);
        QueryWrapper<HrInfo> wrapper = new QueryWrapper<>();
        if(hrInfo.getId() != null){
            wrapper.eq(!StrUtil.isBlank(hrInfo.getId().toString()),"id", hrInfo.getId());
        }
        if (hrInfo.getId()==null&&hrInfo.getUserId()==null){
            activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            Long userId = activeUser.getUser().getId();
            wrapper.eq(!StrUtil.isBlank(userId.toString()),"user_id", userId);
        }
        if(hrInfo.getUserId() != null){
            wrapper.eq(!StrUtil.isBlank(hrInfo.getUserId().toString()),"user_id", hrInfo.getUserId());
        }
        wrapper.eq(!StrUtil.isBlank(hrInfo.getName()),"name",hrInfo.getName());
        if(hrInfo.getRecruitUnitId() != null){
            wrapper.eq(!StrUtil.isBlank(hrInfo.getRecruitUnitId().toString()),"recruit_unit_id", hrInfo.getRecruitUnitId());
        }
        hrInfo = this.hrInfoMapper.selectOne(wrapper);
        if (hrInfo!=null){
            if (activeUser!=null){
                hrInfo.setUsername(activeUser.getUser().getUsername());
            }
        }

        return hrInfo;
    }


}








