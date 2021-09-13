package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
// @MapperScan("com.example.mapper") // @Mapper 使用一个就行
// 如果打成war包，需要这样写
// public class SpringBootLearnApplication extends SpringBootServletInitializer {
public class SpringBootLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnApplication.class, args);
    }

    /**
     * 打成war包，需要指明入口类
     */
    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    //     return builder.sources(SpringBootLearnApplication.class);
    // }
}
