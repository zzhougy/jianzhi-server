package com.zhou.jianzhi.controller.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.BaseUserDTO;
import com.zhou.jianzhi.entity.po.BaseUser;
import com.zhou.jianzhi.service.BaseUserService;
import com.zhou.jianzhi.vialidatedgroup.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/sysUser")
public class SysLoginController {

    @Autowired
    private BaseUserService baseUserService;

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(@Validated({Add.class}) @RequestBody BaseUserDTO user) {
        BaseUser checkUserName = baseUserService.getOne(new QueryWrapper<BaseUser>().eq("username", user.getUsername()));
        if (!StringUtils.isEmpty(checkUserName)) {
            return AjaxResult.error(200, "此用户名已被注册", null);
        }
        boolean result = baseUserService.register(user);
        if (result) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestBody BaseUserDTO user) {
        String token = baseUserService.login(user.getUsername(), user.getPassword());
        //进行身份验证
        return AjaxResult.success((Object) token);
    }

    /**
     * 未登录请求到的接口
     *
     * @return
     */
    @RequestMapping("/unauth")
    @ResponseBody
    public AjaxResult unauth() {
        return AjaxResult.error(500, "未登录");
    }

}
