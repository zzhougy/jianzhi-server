package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.SchoolDTO;
import com.zhou.jianzhi.entity.po.School;
import com.zhou.jianzhi.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* SchoolController
*
* @author zhou
* @date 2021-01-24
*/
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private ISchoolService schoolService;


     /**
      * 根据school表id查询School
      */
     @GetMapping("/getById")
     public AjaxResult selectById(@RequestParam Integer id) {
        School school = schoolService.selectSchoolById(id);
        if (school==null){
           return AjaxResult.error("查不到");
        }
           return AjaxResult.success(school); 
     }


    /**
     * 根据条件查询返回一条School信息
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody SchoolDTO schoolDTO) {
        School school = schoolService.selectOneSchool(schoolDTO);
        if (school==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(school);
    }


    /**
     * 查询School列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody SchoolDTO schoolDTO) throws Exception{
        return AjaxResult.success(schoolService.selectSchoolList(schoolDTO));
    }


    /**
    * 新增单个School
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SchoolDTO schoolDTO) throws Exception{
        Integer insertedNum = schoolService.addSchool(schoolDTO);
        if (insertedNum > 0){
        return AjaxResult.success("新增成功");
        }
        return AjaxResult.error("新增失败");
    }

    /**
     * 批量新增School
     */
    @PostMapping("/addList")
    public AjaxResult addSchoolList(@RequestBody List<SchoolDTO> schoolDTOList) throws Exception{
        return AjaxResult.success(schoolService.addSchoolList(schoolDTOList));
    }

    /**
     * 根据id修改School
     */
    @PostMapping("/edit")
    public AjaxResult editById(@RequestBody SchoolDTO schoolDTO) {
        Integer updatedNum = schoolService.updateSchoolById(schoolDTO);
        if(updatedNum > 0){
           return AjaxResult.success("修改成功");
        }else{
           return AjaxResult.error("修改失败");
        }
    }

    /**
     * 根据id删除School
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        return AjaxResult.success(schoolService.deleteSchoolById(id));
    }

}
