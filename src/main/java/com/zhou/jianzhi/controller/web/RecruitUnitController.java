package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.RecruitUnitDTO;
import com.zhou.jianzhi.entity.po.RecruitUnit;
import com.zhou.jianzhi.service.IRecruitUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* RecruitUnitController
*
* @author zhou
* @date 2021-01-09
*/
@RestController
@RequestMapping("/recruitUnit")
public class RecruitUnitController {
    @Autowired
    private IRecruitUnitService recruitUnitService;


     /**
      * 根据recruitment_unit表id查询RecruitUnit
      */
     @GetMapping("/getById")
     public AjaxResult selectById(@RequestParam Integer id) throws Exception {
        RecruitUnit recruitUnit = recruitUnitService.selectRecruitUnitById(id);
        if (recruitUnit==null){
           return AjaxResult.error("查不到");
        }
           return AjaxResult.success(recruitUnit);
     }


    /**
     * 根据条件查询返回一条RecruitUnit信息
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody RecruitUnitDTO recruitUnitDTO) {
        RecruitUnit recruitUnit = recruitUnitService.selectOneRecruitUnit(recruitUnitDTO);
        if (recruitUnit==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(recruitUnit);
    }


    /**
     * 查询RecruitUnit列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody RecruitUnitDTO recruitUnitDTO) throws Exception{
        return AjaxResult.success(recruitUnitService.selectRecruitUnitList(recruitUnitDTO));
    }


    /**
    * 新增单个RecruitUnit
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody RecruitUnitDTO recruitUnitDTO) throws Exception{
        return AjaxResult.success(recruitUnitService.addRecruitUnit(recruitUnitDTO));
    }

    /**
     * 批量新增RecruitUnit
     */
    @PostMapping("/addList")
    public AjaxResult addRecruitUnitList(@RequestBody List<RecruitUnitDTO> recruitUnitDTOList) throws Exception{
        return AjaxResult.success(recruitUnitService.addRecruitUnitList(recruitUnitDTOList));
    }

    /**
     * 根据id修改RecruitUnit
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody RecruitUnitDTO recruitUnitDTO) {
        return AjaxResult.success(recruitUnitService.updateRecruitUnit(recruitUnitDTO));
    }

    /**
     * 根据id删除RecruitUnit
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable int id) {
        return AjaxResult.success(recruitUnitService.deleteRecruitUnitById(id));
    }


}
