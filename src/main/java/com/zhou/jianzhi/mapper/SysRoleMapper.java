package com.zhou.jianzhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.jianzhi.entity.dto.SysRoleDTO;
import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.vo.SysRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    IPage<SysRoleVO> queryList(Page page, @Param("sysRole") SysRoleDTO sysRoleDto);

    List<String> selectUserId(@Param("deptid") String deptid,@Param("role") String role);

    List<String> selectUserIdByRole(@Param("roles") List<String> roles);

    SysRoleVO selectByRoleId(Long roleId);


}
