package com.zhou.jianzhi.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 上下文工具类
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * spring在bean初始化后会判断是不是ApplicationContextAware的子类
     * 如果该类是，setApplicationContext()方法，会将在容器中ApplicationContext作为参数传入进去
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
    public static <T>T getBean(Class<T> beanClass){
        return context.getBean(beanClass);
    }
}
