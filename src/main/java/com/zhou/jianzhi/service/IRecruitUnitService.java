package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.RecruitUnitDTO;
import com.zhou.jianzhi.entity.po.RecruitUnit;

import java.util.List;




/**
 * RecruitUnitService接口
 *
 * @author zhou
 * @date 2021-01-09
 */
public interface IRecruitUnitService extends IService<RecruitUnit> {

    /**
    * 根据recruitment_unit表id查询
    *
    * @param id
    * @return
    */
    public RecruitUnit selectRecruitUnitById(Integer id) throws Exception;


    /**
     * 根据条件查询返回一条RecruitUnit信息
     */
    public RecruitUnit selectOneRecruitUnit(RecruitUnitDTO recruitUnitDTO);

    /**
     * 查询RecruitUnit列表
     *
     * @param recruitUnitDTO
     * @return recruitment_unit集合
     */
    public Page<RecruitUnit> selectRecruitUnitList(RecruitUnitDTO recruitUnitDTO) throws Exception;

    /**
    * 新增单个RecruitUnit
    *
    * @param recruitUnitDTO
    * @return
    */
    public AjaxResult addRecruitUnit(RecruitUnitDTO recruitUnitDTO);

    /**
     * 批量新增RecruitUnit
     *
     * @param recruitUnitDTOList
     * @return 结果
     */
    public AjaxResult addRecruitUnitList(List<RecruitUnitDTO> recruitUnitDTOList) throws Exception;

    /**
     * 根据id修改RecruitUnit
     *
     * @param recruitUnitDTO
     * @return 结果
     */
    public AjaxResult updateRecruitUnit(RecruitUnitDTO recruitUnitDTO);

    /**
     * 删除RecruitUnit
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteRecruitUnitById(int id);


}
