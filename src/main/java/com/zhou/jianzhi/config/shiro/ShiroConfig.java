package com.zhou.jianzhi.config.shiro;

import com.zhou.jianzhi.config.jwt.JWTFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(UserRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(realm);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(securityManager);
        /*
         * 自定义url规则
         * http://shiro.apache.org/web.html#urls-
         */
        Map<String, String> filterRuleMap = new HashMap<>();
        //所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put("/sysUser/**", "anon");
        filterRuleMap.put("/userPortal/login", "anon");//设置不需要携带token

        filterRuleMap.put("/user/{id}/assignRoles", "anon");//暂时设置分配权限不需要携带token

        filterRuleMap.put("/pic/**", "anon");
        filterRuleMap.put("/app/getPhoneNumber", "anon");
        filterRuleMap.put("/app/wxLogin", "anon");
        filterRuleMap.put("/app/cheakToken", "anon");
        filterRuleMap.put("/app/getWxSessionKeyAndOpenIdByCode", "anon");

        filterRuleMap.put("/recruitUnitLabel/list", "anon");
        filterRuleMap.put("/recruitUnit/list", "anon");
        filterRuleMap.put("/jobLabel/list", "anon");
        filterRuleMap.put("/jobInfo/list", "anon");


        //？匹配一个字符
        // *匹配0个或多个字符
        // **匹配0个或多个目录
        filterRuleMap.put("/recruitUnit/getById**", "anon");//请求/recruitUnit/getById?id=1
        filterRuleMap.put("/jobInfo/getById**", "anon");//请求/jobInfo/getById?id=1


        // 访问401和404页面不通过我们的Filter
//        filterRuleMap.put("/**","anon");
//        filterRuleMap.put("/sysUser/**", "anon");
//        //开放API文档接口
//        filterRuleMap.put("/swagger-ui.html", "anon");
//        filterRuleMap.put("/webjars/**","anon");
//        filterRuleMap.put("/swagger-resources/**","anon");
//        filterRuleMap.put("/v2/**","anon");
//        filterRuleMap.put("/**","anon");
//        //sql监控
//        filterRuleMap.put("/druid/**","anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
