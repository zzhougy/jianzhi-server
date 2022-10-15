package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.JobInfoDTO;
import com.zhou.jianzhi.entity.po.JobInfo;
import com.zhou.jianzhi.service.IJobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* JobInfoController
*
* @author zhou
* @date 2021-01-09
*/
@RestController
@RequestMapping("/jobInfo")
public class JobInfoController {
    @Autowired
    private IJobInfoService jobInfoService;


     /**
      * 根据job_info表id查询job_info
      */
     @GetMapping("/getById")
     public AjaxResult selectById(@RequestParam Integer id) throws Exception {
        JobInfo jobInfo = jobInfoService.selectJobInfoById(id);
        if (jobInfo==null){
           return AjaxResult.error("查不到");
        }
           return AjaxResult.success(jobInfo);
     }

    /**
     * 查询job_info列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody JobInfoDTO jobInfoDTO) throws Exception{
        System.out.println(jobInfoDTO.getLabel());
        return AjaxResult.success(jobInfoService.selectJobInfoList(jobInfoDTO));
    }

    /**
    * 新增单个JobInfo
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody JobInfoDTO jobInfoDTO) throws Exception{
        Integer insertedNum = jobInfoService.addJobInfo(jobInfoDTO);
        if (insertedNum > 0){
            return AjaxResult.success("新增成功");
        }
        return AjaxResult.error("新增失败");
    }

    /**
     * 批量新增job_info
     */
    @PostMapping("/addList")
    public AjaxResult addJobInfoList(@RequestBody List<JobInfoDTO> jobInfoDTOList) throws Exception{
        return AjaxResult.success(jobInfoService.addJobInfoList(jobInfoDTOList));
    }

    /**
     * 根据id修改job_info
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody JobInfoDTO jobInfoDTO) throws Exception {
        return AjaxResult.success(jobInfoService.updateJobInfo(jobInfoDTO));
    }

    /**
     * 根据id删除job_info
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable int id) {
        return AjaxResult.success(jobInfoService.deleteJobInfoById(id));
    }


}
