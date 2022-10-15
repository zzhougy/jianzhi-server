package com.zhou.jianzhi.common.util;

import com.zhou.jianzhi.entity.base.ActiveUser;
import org.apache.shiro.SecurityUtils;


public class ActiveUserUtils {
    public static ActiveUser getLoginUser(){
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        return activeUser;
    }
}
