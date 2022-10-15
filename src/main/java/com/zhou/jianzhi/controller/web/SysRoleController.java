package com.zhou.jianzhi.controller.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.SysRoleDTO;
import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;
import com.zhou.jianzhi.entity.vo.SysRoleVO;
import com.zhou.jianzhi.service.SysMenuService;
import com.zhou.jianzhi.service.SysRoleService;
import com.zhou.jianzhi.vialidatedgroup.Add;
import com.zhou.jianzhi.vialidatedgroup.Update;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 角色授权
     *
     * @param id
     * @param mids
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"sysRole:authority"})
    @PostMapping("/authority/{id}")
    public AjaxResult authority(@PathVariable Long id, @RequestBody Long[] mids) {
        sysRoleService.authority(id, mids);
        return AjaxResult.success();
    }

    /**
     * 角色拥有的菜单权限id和菜单树
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/findRoleMenu/{id}")
    public AjaxResult findRoleMenu(@PathVariable Long id) {
        //加载所有菜单/按钮数据(树状结构)
        List<MenuNodeVO> tree = sysMenuService.findMenuTree();
        //角色拥有的菜单id集合
        List<Long> mids = sysRoleService.findMenuIdsByRoleId(id);
        //找到设置为打开的菜单/按钮
        List<Long> ids = sysMenuService.findOpenIds();
        Map<String, Object> map = new HashMap<>();
        map.put("tree", tree);
        map.put("mids", mids);
        map.put("open", ids);
        return AjaxResult.success(map);
    }

    /**
     * 角色列表
     *
     * @param sysRoleDto
     * @return
     */
    @ResponseBody
    @GetMapping("/findRoleList")
    public AjaxResult findRoleList(SysRoleDTO sysRoleDto) {
        IPage<SysRoleVO> roleList = sysRoleService.findRoleList(sysRoleDto);
        return AjaxResult.success(roleList);
    }

    /**
     * 添加角色信息
     *
     * @param roleDto
     * @return
     */
    @RequiresPermissions({"sysRole:add"})
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@Validated({Add.class}) @RequestBody SysRoleDTO roleDto) {
        logger.info("【添加角色】:" + roleDto);
        sysRoleService.add(roleDto);
        return AjaxResult.success();
    }

    /**
     * 删除角色ID
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"sysRole:delete"})
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        sysRoleService.deleteById(id);
        return AjaxResult.success();
    }

    @ResponseBody
    @GetMapping("/edit/{id}")
    @RequiresPermissions({"sysRole:edit"})
    public AjaxResult edit(@PathVariable Long id) {
        SysRole role = sysRoleService.edit(id);
        return AjaxResult.success(role);
    }

    @ResponseBody
    @RequiresPermissions({"sysRole:update"})
    @PutMapping("/update/{id}")
    public AjaxResult update(@PathVariable Long id, @RequestBody @Validated({Update.class}) SysRoleDTO roleDto) {
        sysRoleService.update(id, roleDto);
        return AjaxResult.success();
    }

    @ResponseBody
    @RequiresPermissions({"sysRole:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public AjaxResult updateStatus(@PathVariable Long id, @PathVariable Boolean status) {
        sysRoleService.updateStatus(id, status);
        return AjaxResult.success();
    }

}
