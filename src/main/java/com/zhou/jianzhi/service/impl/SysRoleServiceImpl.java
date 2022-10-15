package com.zhou.jianzhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.enums.ErrorCodeEnum;
import com.zhou.jianzhi.common.enums.RoleStatusEnum;
import com.zhou.jianzhi.entity.dto.SysRoleDTO;
import com.zhou.jianzhi.entity.po.SysMenu;
import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.po.SysRoleMenu;
import com.zhou.jianzhi.entity.po.SysUserRole;
import com.zhou.jianzhi.entity.vo.SysRoleVO;
import com.zhou.jianzhi.exception.MyException;
import com.zhou.jianzhi.mapper.SysMenuMapper;
import com.zhou.jianzhi.mapper.SysRoleMapper;
import com.zhou.jianzhi.mapper.SysRoleMenuMapper;
import com.zhou.jianzhi.mapper.SysUserRoleMapper;
import com.zhou.jianzhi.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;
    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;


    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void authority(Long id, Long[] mids) {
        //找出需要授权的角色信息
        SysRole role = sysRoleMapper.selectById(id);
        if (StringUtils.isEmpty(role)) {
            throw new MyException(500, "该角色不存在");
        }
        //删除原有权限
        roleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
        //增加新权限
        if (mids.length > 0) {
            Arrays.stream(mids).map(m -> {
                //找到选中的菜单/按钮
                SysMenu menu = menuMapper.selectById(m);
                if (menu == null) {
                    throw new MyException("menuId=" + m + "菜单权限不存在");
                } else {
                    SysRoleMenu roleMenu = new SysRoleMenu();
                    roleMenu.setRoleId(id);
                    roleMenu.setMenuId(m);
                    roleMenuMapper.insert(roleMenu);
                }
                return m;
            }).collect(Collectors.toList());
        }
    }

    /**
     * 查询角色列表
     *
     * @param sysRoleDto
     * @return
     */
    @Override
    public IPage<SysRoleVO> findRoleList(SysRoleDTO sysRoleDto) {
        Page<SysRoleVO> page = new Page<>(sysRoleDto.getPageCurrent(), sysRoleDto.getPageSize());
        IPage<SysRoleVO> list = sysRoleMapper.queryList(page, sysRoleDto);
        return list;
    }

    /**
     * 添加角色
     *
     * @param roleDto
     */
    @Override
    public void add(SysRoleDTO roleDto) {
        //校验角色名称是否存在
        List<SysRole> roles = sysRoleMapper
                .selectList(new QueryWrapper<SysRole>().eq("role_name", roleDto.getRoleName()));
        if (!CollectionUtils.isEmpty(roles)) {
            throw new MyException(ErrorCodeEnum.ROLE_NAME_EXIT.getCode(), ErrorCodeEnum.ROLE_NAME_EXIT.getMsg());
        }
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        sysRoleMapper.insert(sysRole);

    }


    @Transactional
    @Override
    public void deleteById(Long roleId) {
        SysRole role = sysRoleMapper.selectById(roleId);
        if (StringUtils.isEmpty(role)) {
            throw new MyException("要删除的角色不存在");
        }
        List<SysUserRole> sysUserRoles = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
        if (!CollectionUtils.isEmpty(sysUserRoles)) {
            throw new MyException("角色已被使用，不能删除");
        }
        sysRoleMapper.deleteById(roleId);
        //删除对应的[角色-菜单]记录
        roleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
    }

    @Override
    public SysRole edit(Long id) {
        SysRole sysRole = sysRoleMapper.selectById(id);
        if (StringUtils.isEmpty(sysRole)) {
            throw new MyException(200, "编辑的角色不存在");
        }
        return sysRole;
    }

    @Transactional
    @Override
    public boolean update(SysRoleDTO roleDto) {
        SysRole dbRole = sysRoleMapper.selectById(roleDto.getId());
        if (StringUtils.isEmpty(dbRole)) {
            throw new MyException(200, "编辑的角色不存在");
        }
        List<SysRole> roles = sysRoleMapper.selectList(new QueryWrapper<SysRole>().eq("role_name", roleDto.getRoleName()));
        if (!CollectionUtils.isEmpty(roles)) {
            SysRole role = roles.get(0);
            if (!role.getId().equals(roleDto.getId())) {
                throw new MyException("该角色名已被占用");
            }
        }
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleDto, role);
        UpdateWrapper<SysRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", roleDto.getId());
        Integer rows = sysRoleMapper.update(role, updateWrapper);
        return rows > 0 ? true : false;
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Long> findMenuIdsByRoleId(Long id) {
        //根据角色id找到对应的角色
        SysRole role = sysRoleMapper.selectById(id);
        if (StringUtils.isEmpty(role)) {
            throw new MyException("该角色已不存在");
        }
        List<Long> ids = new ArrayList<>();
        //根据角色id从角色-权限(菜单/按钮)关系表中的找到对应记录
        List<SysRoleMenu> roleMenus = roleMenuMapper
                .selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
        //把该角色对应的权限id添加进集合中
        if (!CollectionUtils.isEmpty(roleMenus)) {
            for (SysRoleMenu roleMenu : roleMenus) {
                ids.add(roleMenu.getMenuId());
            }
        }
        return ids;
    }

    @Override
    public void update(Long id, SysRoleDTO roleDto) {
        SysRole dbRole = sysRoleMapper.selectById(id);
        if (StringUtils.isEmpty(dbRole)) {
            throw new MyException("要更新的角色不存在");
        }
        List<SysRole> roles = sysRoleMapper.selectList(new QueryWrapper<SysRole>().eq("role_name", roleDto.getRoleName()));
        if (!CollectionUtils.isEmpty(roles)) {
            SysRole role = roles.get(0);
            if (!role.getId().equals(id)) {
                throw new MyException("该角色名已被占用");
            }
        }
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleDto, role);
        sysRoleMapper.updateById(role);
    }

    @Override
    public List<String> selectUserId(List roles) {
        return sysRoleMapper.selectUserIdByRole(roles);
    }

    @Override
    public void updateStatus(Long id, Boolean status) {
        SysRole role = sysRoleMapper.selectById(id);
        if (StringUtils.isEmpty(role)) {
            throw new MyException("角色不存在");
        }
        SysRole t = new SysRole();
        t.setId(id);
        t.setStatus(status ? RoleStatusEnum.AVAILABLE.getStatusCode() :
                RoleStatusEnum.DISABLE.getStatusCode());
        sysRoleMapper.updateById(t);
    }

    @Override
    public List<String> selectUserId(String deptid, String role) {
        return sysRoleMapper.selectUserId(deptid, role);
    }
}
