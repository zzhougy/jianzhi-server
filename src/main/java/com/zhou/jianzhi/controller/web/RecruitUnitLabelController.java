package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.RecruitUnitLabelDTO;
import com.zhou.jianzhi.service.IRecruitUnitLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* RecruitUnitLabelController
*
* @author zhou
* @date 2021-02-12
*/
@RestController
@RequestMapping("/recruitUnitLabel")
public class RecruitUnitLabelController {

    @Autowired
    private IRecruitUnitLabelService recruitUnitLabelService;


    /**
     * 根据recruit_unit_label表id查询RecruitUnitLabel
     */
    @GetMapping("/getById")
    public AjaxResult selectById(@RequestParam Integer id) throws ClassNotFoundException {
        return recruitUnitLabelService.selectRecruitUnitLabelById(id);
    }

    /**
     * 根据条件查询返回一条RecruitUnitLabel信息
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody RecruitUnitLabelDTO recruitUnitLabelDTO) {
        return recruitUnitLabelService.selectOneRecruitUnitLabel(recruitUnitLabelDTO);
    }

    /**
     * 查询RecruitUnitLabel列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody RecruitUnitLabelDTO recruitUnitLabelDTO) throws Exception {



        return recruitUnitLabelService.selectRecruitUnitLabelList(recruitUnitLabelDTO);
    }

    /**
    * 新增单个RecruitUnitLabel
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody RecruitUnitLabelDTO recruitUnitLabelDTO) throws Exception {
        return recruitUnitLabelService.addRecruitUnitLabel(recruitUnitLabelDTO);
    }

    /**
     * 批量新增RecruitUnitLabel
     */
    @PostMapping("/addList")
    public AjaxResult addRecruitUnitLabelList(@RequestBody List<RecruitUnitLabelDTO> recruitUnitLabelDTOList) throws Exception {
        return AjaxResult.success(recruitUnitLabelService.addRecruitUnitLabelList(recruitUnitLabelDTOList));
    }

    /**
     * 根据id修改RecruitUnitLabel
     */
    @PostMapping("/edit")
    public AjaxResult editById(@RequestBody RecruitUnitLabelDTO recruitUnitLabelDTO) {
        return recruitUnitLabelService.updateRecruitUnitLabelById(recruitUnitLabelDTO);
    }

    /**
     * 根据id删除RecruitUnitLabel
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        return AjaxResult.success(recruitUnitLabelService.deleteRecruitUnitLabelById(id));
    }

}
