package com.zhou.jianzhi.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义shiro注解，用于放开认证的接口
 * 通过对controller的接口方法添加该注解，实现不需要登录既可以访问。
 */
@Target(value ={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GuestAccess {
}
