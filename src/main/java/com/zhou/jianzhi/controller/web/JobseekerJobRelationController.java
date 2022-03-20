package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobseekerJobRelationDTO;
import com.zhou.jianzhi.entity.po.JobseekerJobRelation;
import com.zhou.jianzhi.service.IJobseekerJobRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* JobseekerJobRelationController
*
* @author zhou
* @date 2021-01-09
*/
@RestController
@RequestMapping("/jobseekerJobRelation")
public class JobseekerJobRelationController {
    @Autowired
    private IJobseekerJobRelationService jobseekerJobRelationService;


     /**
      * 根据jobseeker_job_relation表id查询JobseekerJobRelation
      */
     @GetMapping("/getById")
     public AjaxResult selectById(@RequestParam Integer id) {
        JobseekerJobRelation jobseekerJobRelation = jobseekerJobRelationService.selectJobseekerJobRelationById(id);
        if (jobseekerJobRelation==null){
           return AjaxResult.error("查不到");
        }
           return AjaxResult.success(jobseekerJobRelation); 
     }


    /**
     * 根据条件查询返回一条JobseekerJobRelation信息
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody JobseekerJobRelationDTO jobseekerJobRelationDTO) {
        JobseekerJobRelation jobseekerJobRelation = jobseekerJobRelationService.selectOneJobseekerJobRelation(jobseekerJobRelationDTO);
        if (jobseekerJobRelation==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(jobseekerJobRelation);
    }


    /**
     * 查询JobseekerJobRelation列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody JobseekerJobRelationDTO jobseekerJobRelationDTO) throws Exception{
        return AjaxResult.success(jobseekerJobRelationService.selectJobseekerJobRelationList(jobseekerJobRelationDTO));
    }


    /**
    * 新增单个JobseekerJobRelation
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody JobseekerJobRelationDTO jobseekerJobRelationDTO) throws Exception{
        return AjaxResult.success(jobseekerJobRelationService.addJobseekerJobRelation(jobseekerJobRelationDTO));
    }

    /**
     * 批量新增JobseekerJobRelation
     */
    @PostMapping("/addList")
    public AjaxResult addJobseekerJobRelationList(@RequestBody List<JobseekerJobRelationDTO> jobseekerJobRelationDTOList) throws Exception{
        return AjaxResult.success(jobseekerJobRelationService.addJobseekerJobRelationList(jobseekerJobRelationDTOList));
    }

    /**
     * 根据id修改JobseekerJobRelation
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody JobseekerJobRelationDTO jobseekerJobRelationDTO) {
        Integer updatedNum = jobseekerJobRelationService.updateJobseekerJobRelation(jobseekerJobRelationDTO);
        if(updatedNum > 0){
            return AjaxResult.success("修改成功");
        }else{
            return AjaxResult.error("修改失败");
        }
    }

    /**
     * 根据id删除JobseekerJobRelation
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable int id) {
        return AjaxResult.success(jobseekerJobRelationService.deleteJobseekerJobRelationById(id));
    }


}
