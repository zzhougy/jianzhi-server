package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.ResumeDTO;
import com.zhou.jianzhi.entity.po.Resume;
import com.zhou.jianzhi.service.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
* ResumeController
*
* @author zhou
* @date 2021-01-03
*/
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private IResumeService resumeService;


    /**
     * 根据${table.name}表id查询${table.name}
     */
    @GetMapping("/getById")
    public AjaxResult selectById(@RequestParam Integer id) throws Exception{
        Resume resume = resumeService.selectById(id);
        if (resume==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success();
    }


    @PostMapping("/getOne")
    public AjaxResult selectOneResume(@RequestBody ResumeDTO resumeDTO) throws Exception{
        Resume resume = resumeService.selectOneResume(resumeDTO);
        if (resume==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(resume);
    }


    /**
     * 查询resume列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody ResumeDTO resumeDTO) throws Exception{
        return AjaxResult.success(resumeService.selectResumeList(resumeDTO));
    }


    /**
     * 新增单个resume
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ResumeDTO resumeDTO) throws Exception{
        return AjaxResult.success(resumeService.addResume(resumeDTO));
    }


    /**
     * 批量新增resume
     */
    @PostMapping("/addList")
    public AjaxResult addList(@RequestBody List<ResumeDTO> resumeDTOList) throws Exception{
        return AjaxResult.success(resumeService.insertResumeList(resumeDTOList));
    }

    /**
     * 根据id修改resume
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody ResumeDTO resumeDTO) {
        return AjaxResult.success(resumeService.updateResume(resumeDTO));
    }

    /**
     * 根据id删除resume
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable int id) {
        return AjaxResult.success(resumeService.deleteResumeById(id));
    }


}
