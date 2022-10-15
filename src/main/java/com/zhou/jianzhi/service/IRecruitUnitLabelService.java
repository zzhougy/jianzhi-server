package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.RecruitUnitLabelDTO;
import com.zhou.jianzhi.entity.po.RecruitUnitLabel;

import java.util.List;


/**
 * RecruitUnitLabelService接口
 *
 * @author zhou
 * @date 2021-02-12
 */
public interface IRecruitUnitLabelService extends IService<RecruitUnitLabel> {


    /**
    * 根据recruit_unit_label表id查询
    *
    * @param id
    * @return
    */
    public AjaxResult selectRecruitUnitLabelById(Integer id);

    /**
     * 根据条件查询返回一条RecruitUnitLabel信息
     */
    public AjaxResult selectOneRecruitUnitLabel(RecruitUnitLabelDTO recruitUnitLabelDTO);

    /**
     * 查询RecruitUnitLabel列表
     *
     * @param recruitUnitLabelDTO
     * @return recruit_unit_label集合
     */
    public AjaxResult selectRecruitUnitLabelList(RecruitUnitLabelDTO recruitUnitLabelDTO) throws Exception;

    /**
    * 新增单个RecruitUnitLabel
    *
    * @param recruitUnitLabelDTO
    * @return
    */
    public AjaxResult addRecruitUnitLabel(RecruitUnitLabelDTO recruitUnitLabelDTO);

    /**
     * 批量新增RecruitUnitLabel
     *
     * @param recruitUnitLabelDTOList
     * @return 结果
     */
    public AjaxResult addRecruitUnitLabelList(List<RecruitUnitLabelDTO> recruitUnitLabelDTOList) throws Exception;

    /**
     * 根据id修改RecruitUnitLabel
     *
     * @param recruitUnitLabelDTO
     * @return 结果
     */
    public AjaxResult updateRecruitUnitLabelById(RecruitUnitLabelDTO recruitUnitLabelDTO);

    /**
     * 删除RecruitUnitLabel
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteRecruitUnitLabelById(Integer id);


}
