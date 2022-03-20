package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.HrInfoDTO;
import com.zhou.jianzhi.entity.po.HrInfo;
import com.zhou.jianzhi.service.IHrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* HrInfoController
*
* @author zhou
* @date 2021-01-09
*/
@RestController
@RequestMapping("/hrInfo")
public class HrInfoController {
    @Autowired
    private IHrInfoService hrInfoService;


     /**
      * 根据hr_info表id查询hr_info
      */
     @GetMapping("/getById")
     public AjaxResult selectById(@RequestParam Long id) {
        HrInfo hrInfo = hrInfoService.selectHrInfoById(id);
        if (hrInfo==null){
           return AjaxResult.error("查不到");
        }
           return AjaxResult.success(hrInfo);
     }


    /**
     * 根据条件查询返回一条hr_info
     */
    @PostMapping("/getOne")
    public AjaxResult selectOne(@RequestBody HrInfoDTO hrInfoDTO) {
        HrInfo hrInfo = hrInfoService.selectOneHrInfo(hrInfoDTO);
        if (hrInfo==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(hrInfo);
    }



    /**
     * 查询hr_info列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody HrInfoDTO hrInfoDTO) throws Exception{
        return hrInfoService.selectHrInfoList(hrInfoDTO);
    }


    /**
    * 新增单个HrInfo
    */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HrInfoDTO hrInfoDTO) throws Exception{
        return AjaxResult.success(hrInfoService.addHrInfo(hrInfoDTO));
    }

    /**
     * 批量新增hr_info
     */
    @PostMapping("/addList")
    public AjaxResult addHrInfoList(@RequestBody List<HrInfoDTO> hrInfoDTOList) throws Exception{
        return AjaxResult.success(hrInfoService.addHrInfoList(hrInfoDTOList));
    }

    /**
     * 根据id修改hr_info
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody HrInfoDTO hrInfoDTO) {
        return AjaxResult.success(hrInfoService.updateHrInfo(hrInfoDTO));
    }

    /**
     * 根据id删除hr_info
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable int id) {
        return AjaxResult.success(hrInfoService.deleteHrInfoById(id));
    }


}
