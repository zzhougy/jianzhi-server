package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.SchoolDTO;
import com.zhou.jianzhi.entity.po.School;

import java.util.List;




/**
 * SchoolService接口
 *
 * @author zhou
 * @date 2021-01-24
 */
public interface ISchoolService extends IService<School> {

    /**
    * 根据school表id查询
    *
    * @param id
    * @return
    */
    public School selectSchoolById(Integer id);


    /**
     * 根据条件查询返回一条School信息
     */
    public School selectOneSchool(SchoolDTO schoolDTO);

    /**
     * 查询School列表
     *
     * @param schoolDTO
     * @return school集合
     */
    public Page<School> selectSchoolList(SchoolDTO schoolDTO) throws Exception;

    /**
    * 新增单个School
    *
    * @param schoolDTO
    * @return
    */
    public Integer addSchool(SchoolDTO schoolDTO);

    /**
     * 批量新增School
     *
     * @param schoolDTOList
     * @return 结果
     */
    public AjaxResult addSchoolList(List<SchoolDTO> schoolDTOList) throws Exception;

    /**
     * 根据id修改School
     *
     * @param schoolDTO
     * @return 结果
     */
    public Integer updateSchoolById(SchoolDTO schoolDTO);

    /**
     * 删除School
     *
     * @param id 需要删除的ID
     * @return 结果
     */
    public AjaxResult deleteSchoolById(Integer id);


}
