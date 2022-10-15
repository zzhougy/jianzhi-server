package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobSeekerDTO;
import com.zhou.jianzhi.entity.po.JobSeeker;
import com.zhou.jianzhi.service.IJobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* JobSeekerController
*
* @author zhou
* @date 2021-01-09
*/
@RestController
@RequestMapping("/jobSeeker")
public class JobSeekerController {
    @Autowired
    private IJobSeekerService jobSeekerService;


     /**
      * 根据job_seeker表id查询JobSeeker
      */
     @GetMapping("/getById")
     public AjaxResult selectById(@RequestParam Integer id) {
        JobSeeker jobSeeker = jobSeekerService.selectJobSeekerById(id);
        if (jobSeeker==null){
           return AjaxResult.error("查不到");
        }
           return AjaxResult.success(jobSeeker); 
     }


    /**
     * 根据条件查询返回一条JobSeeker信息
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody JobSeekerDTO jobSeekerDTO) {
        JobSeeker jobSeeker = jobSeekerService.selectOneJobSeeker(jobSeekerDTO);
        if (jobSeeker==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(jobSeeker);
    }


    /**
     * 查询JobSeeker列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody JobSeekerDTO jobSeekerDTO) throws Exception{
        return AjaxResult.success(jobSeekerService.selectJobSeekerList(jobSeekerDTO));
    }


    /**
    * 新增单个JobSeeker
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody JobSeekerDTO jobSeekerDTO) throws Exception{
        return AjaxResult.success(jobSeekerService.addJobSeeker(jobSeekerDTO));
    }

    /**
     * 批量新增JobSeeker
     */
    @PostMapping("/addList")
    public AjaxResult addJobSeekerList(@RequestBody List<JobSeekerDTO> jobSeekerDTOList) throws Exception{
        return AjaxResult.success(jobSeekerService.addJobSeekerList(jobSeekerDTOList));
    }

    /**
     * 根据id修改JobSeeker
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody JobSeekerDTO jobSeekerDTO) {
        return AjaxResult.success(jobSeekerService.updateJobSeeker(jobSeekerDTO));
    }

    /**
     * 根据id删除JobSeeker
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable int id) {
        return AjaxResult.success(jobSeekerService.deleteJobSeekerById(id));
    }


}
