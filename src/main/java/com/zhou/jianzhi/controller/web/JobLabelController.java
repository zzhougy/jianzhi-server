package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobLabelDTO;
import com.zhou.jianzhi.service.IJobLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
* JobLabelController
*
* @author zhou
* @date 2021-02-08
*/
@RestController
@RequestMapping("/jobLabel")
public class JobLabelController {

    @Autowired
    private IJobLabelService jobLabelService;


    /**
     * 根据job_label表id查询JobLabel
     */
    @GetMapping("/getById")
    public AjaxResult selectById(@RequestParam Integer id) {
        return jobLabelService.selectJobLabelById(id);
    }

    /**
     * 根据条件查询返回一条JobLabel信息
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody JobLabelDTO jobLabelDTO) {
        return jobLabelService.selectOneJobLabel(jobLabelDTO);
    }

    /**
     * 查询JobLabel列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody JobLabelDTO jobLabelDTO) throws Exception {
        return jobLabelService.selectJobLabelList(jobLabelDTO);
    }

    /**
    * 新增单个JobLabel
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody JobLabelDTO jobLabelDTO) throws Exception {
        return jobLabelService.addJobLabel(jobLabelDTO);
    }

    /**
     * 批量新增JobLabel
     */
    @PostMapping("/addList")
    public AjaxResult addJobLabelList(@RequestBody List<JobLabelDTO> jobLabelDTOList) throws Exception {
        return AjaxResult.success(jobLabelService.addJobLabelList(jobLabelDTOList));
    }

    /**
     * 根据id修改JobLabel
     */
    @PostMapping("/edit")
    public AjaxResult editById(@RequestBody JobLabelDTO jobLabelDTO) {
        return jobLabelService.updateJobLabelById(jobLabelDTO);
    }

    /**
     * 根据id删除JobLabel
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        return AjaxResult.success(jobLabelService.deleteJobLabelById(id));
    }

}
