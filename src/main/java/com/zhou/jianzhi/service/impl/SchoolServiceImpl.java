package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.SchoolDTO;
import com.zhou.jianzhi.entity.po.School;
import com.zhou.jianzhi.mapper.SchoolMapper;
import com.zhou.jianzhi.service.ISchoolService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * schoolService业务层处理
 *
 * @author zhou
 * @date 2021-01-24
 */
@Service
@Transactional
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

    @Autowired
    private SchoolMapper schoolMapper;


    /**
     * 根据school表id查询
     *
     * @param id
     * @return
     */
    @Override
    public School selectSchoolById(Integer id) {
        return this.schoolMapper.selectById(id);
    }
	
	
	/**
     * 根据条件查询返回一条School信息
     * @param schoolDTO
     * @return
     */
    @Override
    public School selectOneSchool(SchoolDTO schoolDTO) {
        School school = new School();
        BeanUtils.copyProperties(schoolDTO,school);
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        if(school.getId() != null){
            wrapper.eq(!StrUtil.isBlank(school.getId().toString()),"id", school.getId());
        }
        wrapper.eq(!StrUtil.isBlank(school.getName()),"name",school.getName());
        return this.schoolMapper.selectOne(wrapper);
    }
	

    /**
     * 查询School列表
     *
     * @param schoolDTO
     * @return
     */
    @Override
    public Page<School> selectSchoolList(SchoolDTO schoolDTO) throws Exception {
        Page<School> page = new Page<>(schoolDTO.getPageCurrent(),schoolDTO.getPageSize());
        School school = new School();
        BeanUtils.copyProperties(schoolDTO,school);
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        if(school.getId() != null){
            wrapper.eq(!StrUtil.isBlank(school.getId().toString()),"id", school.getId());
        }
        wrapper.eq(!StrUtil.isBlank(school.getName()),"name",school.getName());
        Page<School> Ipage = this.schoolMapper.selectPage(page, wrapper);
        return Ipage;
    }

    /**
    * 新增单个School
    *
    * @param schoolDTO
    * @return
    */
    @Override
    public Integer addSchool(SchoolDTO schoolDTO) {
        School school = new School();
        BeanUtils.copyProperties(schoolDTO,school);
        int insertedNum = this.schoolMapper.insert(school);
        return insertedNum;
    }

    /**
     * 批量新增School
     *
     * @param schoolDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addSchoolList(List<SchoolDTO> schoolDTOList) throws Exception {
        //添加操作
        for (SchoolDTO schoolDTO : schoolDTOList){
            School school = new School();
            BeanUtils.copyProperties(schoolDTO,school);
            int insert = this.schoolMapper.insert(school);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改School
     *
     * @param schoolDTO
     * @return 结果
     */
    @Override
    public Integer updateSchoolById(SchoolDTO schoolDTO){
        School school = selectSchoolById(schoolDTO.getId());
        if(school.getId() != null){
            school.setId(schoolDTO.getId());
        }
        if(school.getName() != null && !school.getName().isEmpty()){
            school.setName(schoolDTO.getName());
        }
        int updatedNum = this.schoolMapper.updateById(school);
        return updatedNum;
    }

    /**
     * 根据id删除School
     *
     * @param id 需要删除的schoolID
     * @return 结果
     */
    @Override
    public AjaxResult deleteSchoolById(Integer id)
    {
        int delete = this.schoolMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}








