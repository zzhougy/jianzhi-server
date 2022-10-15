package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.RecruitUnitLabelDTO;
import com.zhou.jianzhi.entity.po.RecruitUnitLabel;
import com.zhou.jianzhi.mapper.RecruitUnitLabelMapper;
import com.zhou.jianzhi.service.IRecruitUnitLabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * recruit_unit_labelService业务层处理
 *
 * @author zhou
 * @date 2021-02-12
 */
@Service
@Transactional
public class RecruitUnitLabelServiceImpl extends ServiceImpl<RecruitUnitLabelMapper, RecruitUnitLabel> implements IRecruitUnitLabelService {

    @Autowired
    private RecruitUnitLabelMapper recruitUnitLabelMapper;


    /**
     * 根据recruit_unit_label表id查询
     *
     * @param id
     * @return
     */
    @Override
    public AjaxResult selectRecruitUnitLabelById(Integer id) {
        RecruitUnitLabel recruitUnitLabel = this.recruitUnitLabelMapper.selectById(id);
        if (recruitUnitLabel==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(recruitUnitLabel);
    }

	/**
     * 根据条件查询返回一条RecruitUnitLabel信息
     * @param recruitUnitLabelDTO
     * @return
     */
    @Override
    public AjaxResult selectOneRecruitUnitLabel(RecruitUnitLabelDTO recruitUnitLabelDTO) {
        RecruitUnitLabel recruitUnitLabel = new RecruitUnitLabel();
        BeanUtils.copyProperties(recruitUnitLabelDTO,recruitUnitLabel);
        QueryWrapper<RecruitUnitLabel> wrapper = new QueryWrapper<>();
        if(recruitUnitLabel.getId() != null){
            wrapper.eq(!StrUtil.isBlank(recruitUnitLabel.getId().toString()),"id", recruitUnitLabel.getId());
        }
        wrapper.eq(!StrUtil.isBlank(recruitUnitLabel.getName()),"name",recruitUnitLabel.getName());
        recruitUnitLabel = this.recruitUnitLabelMapper.selectOne(wrapper);
        if (recruitUnitLabel==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(recruitUnitLabel);
    }

    /**
     * 查询RecruitUnitLabel列表
     *
     * @param recruitUnitLabelDTO
     * @return
     */
    @Override
    public AjaxResult selectRecruitUnitLabelList(RecruitUnitLabelDTO recruitUnitLabelDTO) throws Exception {
        Page<RecruitUnitLabel> page = new Page<>(recruitUnitLabelDTO.getPageCurrent(),recruitUnitLabelDTO.getPageSize());
        RecruitUnitLabel recruitUnitLabel = new RecruitUnitLabel();
        BeanUtils.copyProperties(recruitUnitLabelDTO,recruitUnitLabel);
        QueryWrapper<RecruitUnitLabel> wrapper = new QueryWrapper<>();
        if(recruitUnitLabel.getId() != null){
            wrapper.eq(!StrUtil.isBlank(recruitUnitLabel.getId().toString()),"id", recruitUnitLabel.getId());
        }
        wrapper.eq(!StrUtil.isBlank(recruitUnitLabel.getName()),"name",recruitUnitLabel.getName());
        Page<RecruitUnitLabel> Ipage = this.recruitUnitLabelMapper.selectPage(page, wrapper);
        if (Ipage==null){
            return AjaxResult.error();
        }
        return AjaxResult.success(Ipage);
    }

    /**
    * 新增单个RecruitUnitLabel
    *
    * @param recruitUnitLabelDTO
    * @return
    */
    @Override
    public AjaxResult addRecruitUnitLabel(RecruitUnitLabelDTO recruitUnitLabelDTO) {
        RecruitUnitLabel recruitUnitLabel = new RecruitUnitLabel();
        BeanUtils.copyProperties(recruitUnitLabelDTO,recruitUnitLabel);
        int insertedNum = this.recruitUnitLabelMapper.insert(recruitUnitLabel);
        if (insertedNum > 0){
            return AjaxResult.success("新增成功");
        }
        return AjaxResult.error("新增失败");
    }

    /**
     * 批量新增RecruitUnitLabel
     *
     * @param recruitUnitLabelDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addRecruitUnitLabelList(List<RecruitUnitLabelDTO> recruitUnitLabelDTOList) throws Exception {
        //添加操作
        for (RecruitUnitLabelDTO recruitUnitLabelDTO : recruitUnitLabelDTOList){
            RecruitUnitLabel recruitUnitLabel = new RecruitUnitLabel();
            BeanUtils.copyProperties(recruitUnitLabelDTO,recruitUnitLabel);
            int insert = this.recruitUnitLabelMapper.insert(recruitUnitLabel);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改RecruitUnitLabel
     *
     * @param recruitUnitLabelDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateRecruitUnitLabelById(RecruitUnitLabelDTO recruitUnitLabelDTO){
        AjaxResult ajaxResult = selectRecruitUnitLabelById(recruitUnitLabelDTO.getId());
        if ((Integer) ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
            RecruitUnitLabel recruitUnitLabel = (RecruitUnitLabel)ajaxResult.get("data");
            if(recruitUnitLabelDTO.getId() != null){
                recruitUnitLabel.setId(recruitUnitLabelDTO.getId());
            }
            if(recruitUnitLabelDTO.getName() != null && !recruitUnitLabelDTO.getName().isEmpty()){
                recruitUnitLabel.setName(recruitUnitLabelDTO.getName());
            }
            int updatedNum = this.recruitUnitLabelMapper.updateById(recruitUnitLabel);
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
     * 根据id删除RecruitUnitLabel
     *
     * @param id 需要删除的recruit_unit_labelID
     * @return 结果
     */
    @Override
    public AjaxResult deleteRecruitUnitLabelById(Integer id)
    {
        int delete = this.recruitUnitLabelMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}








