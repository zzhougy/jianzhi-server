package com.zhou.jianzhi.config.zhounettyrabbit;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
@Configuration
public class RabbitMQConfig {

//    @Bean("sysNoticeContainer")
//    public SimpleMessageListenerContainer createSys(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container =
//                new SimpleMessageListenerContainer(connectionFactory);
//        //使用Channel
//        container.setExposeListenerChannel(true);
//        //设置自己编写的监听器
//        container.setMessageListener(new SysNoticeListener());
//
//        return container;
//    }
//    @Bean("userNoticeContainer")
//    public SimpleMessageListenerContainer createUser(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container =
//                new SimpleMessageListenerContainer(connectionFactory);
//        //使用Channel
//        container.setExposeListenerChannel(true);
//        //设置自己编写的监听器
//        container.setMessageListener(new UserNoticeListener());
//
//        return container;
//    }

    @Bean("getResNoticeContainer")
    public SimpleMessageListenerContainer create(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(connectionFactory);
        //使用Channel
        container.setExposeListenerChannel(true);
        //设置自己编写的监听器
        container.setMessageListener(new GetResNoticeListener());

        return container;
    }

}