package com.zhou.jianzhi.controller.web;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.SysMenuDTO;
import com.zhou.jianzhi.entity.po.SysMenu;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;
import com.zhou.jianzhi.service.SysMenuService;
import com.zhou.jianzhi.vialidatedgroup.Add;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 加载菜单树
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/tree")
    public AjaxResult tree() {
        //从数据库中找到所有的菜单和按钮,并转化成菜单数
        List<MenuNodeVO> menuTree = sysMenuService.findMenuTree();
        //找到加载时需要打开的节点id
        List<Long> ids = sysMenuService.findOpenIds();
        Map<String, Object> map = new HashMap<>();
        map.put("tree", menuTree);
        map.put("open", ids);
        return AjaxResult.success(map);
    }

    /**
     * 新增菜单/按钮
     *
     * @param sysMenuDto
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"sysMenu:add"})
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated({Add.class}) SysMenuDTO sysMenuDto) {
        logger.info("【新增菜单/按钮】:" + sysMenuDto);
        SysMenu node = sysMenuService.add(sysMenuDto);
        Map<String, Object> map = new HashMap<>();
        //封装的数据暂时没有用
        map.put("id", node.getId());
        map.put("menuName", node.getMenuName());
        map.put("children", new ArrayList<>());
        map.put("icon", node.getIcon());
        return AjaxResult.success(map);
    }

    /**
     * 删除菜单/按钮
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"sysMenu:delete"})
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        sysMenuService.delete(id);
        return AjaxResult.success();
    }

    /**
     * 菜单详情,用于编辑时的回显
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"sysMenu:edit"})
    @GetMapping("/edit/{id}")
    public AjaxResult edit(@PathVariable Long id) {
        SysMenuDTO menuDto = sysMenuService.edit(id);
        return AjaxResult.success(menuDto);
    }

    /**
     * 更新菜单
     *
     * @param id
     * @param menuDto
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"sysMenu:update"})
    @PutMapping("/update/{id}")
    public AjaxResult update(@PathVariable Long id, @RequestBody @Validated SysMenuDTO menuDto) {
        sysMenuService.update(id, menuDto);
        return AjaxResult.success();
    }
}
