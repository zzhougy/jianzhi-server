package com.zhou.jianzhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.jianzhi.entity.dto.BaseUserDTO;
import com.zhou.jianzhi.entity.po.BaseUser;
import com.zhou.jianzhi.entity.vo.BaseUserVO;
import com.zhou.jianzhi.entity.vo.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BaseUserMapper extends BaseMapper<BaseUser> {
    IPage<BaseUserVO> queryList(Page page, @Param("user") BaseUserDTO baseUserDto);
    List<SysRoleVO> selectRolesByUserId(Long id);

    BaseUserVO queryByJoinEmp(String userId);
}

