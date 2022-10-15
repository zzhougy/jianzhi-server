package com.zhou.jianzhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan(basePackages = {"com.zhou.jianzhi.mapper"})
@EnableAsync
public class JianzhiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JianzhiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统启动成功  ლ(´ڡ`ლ)ﾞ");
    }
}
