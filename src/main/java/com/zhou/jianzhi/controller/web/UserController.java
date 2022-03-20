package com.zhou.jianzhi.controller.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.common.util.SHA256Util;
import com.zhou.jianzhi.concerter.RoleConverter;
import com.zhou.jianzhi.config.annotation.GuestAccess;
import com.zhou.jianzhi.entity.dto.BaseUserDTO;
import com.zhou.jianzhi.entity.po.BaseUser;
import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.vo.BaseUserVO;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;
import com.zhou.jianzhi.entity.vo.RoleTransferItemVO;
import com.zhou.jianzhi.service.BaseUserService;
import com.zhou.jianzhi.service.SysRoleService;
import com.zhou.jianzhi.vialidatedgroup.Update;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;


@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseUserService userService;
    @Autowired
    private Executor asyncExecutor;
    @Autowired
    private SysRoleService roleService;

    /**
     * 获取所有用户列表zhou
     *
     * @param userDto
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    @GuestAccess
    public AjaxResult getUserList(BaseUserDTO userDto) {
        asyncExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ssssssss");
            }
        });
        IPage<BaseUser> userList = userService.getUserList(userDto);
        logger.info("查询用户{}", userList.getRecords().toString());
        return AjaxResult.success(userList);
    }

    /**
     * 获取所有用户列表old【这个项目没用】
     *
     * @param userDto
     * @return
     */
    @ResponseBody
    @GetMapping("/findUserList")
    public AjaxResult findUserList(BaseUserDTO userDto) {
        IPage<BaseUserVO> userList = userService.findUserList(userDto);
        logger.info("查询用户{}", userList.getRecords().toString());
        return AjaxResult.success(userList);
    }



    /**
     * 获取用户的信息
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/info")
    public AjaxResult info() throws Exception {
        BaseUserVO userInfo = userService.info();
        return AjaxResult.success(userInfo);
    }

    /**
     * 获取用户的信息selectById
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getInfoById/{userId}")
    public AjaxResult info(@PathVariable Long userId) throws Exception {
        BaseUser baseUser = userService.selectById(userId);
        return AjaxResult.success(baseUser);
    }


    /**
     * 根据username获取用户的头像
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/userImag/{username}")
    public AjaxResult getuserImag(@PathVariable String username) throws Exception {
        BaseUserVO userInfo = userService.getuserImag(username);
        return AjaxResult.success(userInfo);
    }

    /**
     * 获取当前用户的菜单
     *
     * @return
     */
    @GetMapping("/findMenu")
    @ResponseBody
    public AjaxResult findMenu() {
        List<MenuNodeVO> menuTreeVOS = userService.findMenu();
        return AjaxResult.success(menuTreeVOS);
    }

    /**
     * 分配角色
     *
     * @param id
     * @param rids
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"user:assign"})
    @PostMapping("/{id}/assignRoles")
    public AjaxResult assignRoles(@PathVariable Long id, @RequestBody Long[] rids) {
        userService.assignRoles(id, rids);
        return AjaxResult.success();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"user:delete"})
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        userService.deleteById(id);
        return AjaxResult.success();
    }

    /**
     * 更改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequiresPermissions({"user:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public AjaxResult updateStatus(@PathVariable Long id, @PathVariable Boolean status) {
        userService.updateStatus(id, status);
        return AjaxResult.success();
    }

    /**
     * 更新用户
     *
     * @param id
     * @param baseUserDto
     * @return
     */
    @ResponseBody
    @PutMapping("/update/{id}")
    public AjaxResult update(@PathVariable Long id, @RequestBody @Validated({Update.class}) BaseUserDTO baseUserDto) {
        userService.update(id, baseUserDto);
        return AjaxResult.success();
    }

    /**
     * 编辑用户 获取信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/edit/{id}")
    public AjaxResult edit(@PathVariable Long id) {
        BaseUserVO userVO = userService.edit(id);
        return AjaxResult.success(userVO);
    }

    /**
     * 用户角色信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/{id}/roles")
    public AjaxResult roles(@PathVariable Long id) {
        List<Long> values = userService.roles(id);
        List<SysRole> list = roleService.findAll();
        //转成前端需要的角色Item
        List<RoleTransferItemVO> items = RoleConverter.converterToRoleTransferItem(list);
        Map<String, Object> map = new HashMap<>();
        map.put("roles", items);
        map.put("values", values);
        return AjaxResult.success(map);
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(@Validated({Update.class}) @RequestBody BaseUserDTO user) {
        BaseUser dbUser = new BaseUser();
        BeanUtils.copyProperties(user, dbUser);
        String salt = RandomStringUtils.randomAlphabetic(20);
        dbUser.setSalt(salt);
        dbUser.setPassword(SHA256Util.sha256(user.getPassword(), salt));
        boolean result = userService.updateById(dbUser);
        if (result) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }


}

