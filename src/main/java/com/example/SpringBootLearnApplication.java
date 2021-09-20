package com.example;

import com.example.listener.MyListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @MapperScan("com.example.mapper") // @Mapper 使用一个就行
// 如果打成war包，需要这样写
// public class SpringBootLearnApplication extends SpringBootServletInitializer {
public class SpringBootLearnApplication {

    public static void main(String[] args) {

        // 拿到指定的退出状态码
        // System.exit(SpringApplication.exit(SpringApplication.run(SpringBootLearnApplication.class, args)));

        SpringApplication.run(SpringBootLearnApplication.class, args);

        // 可以使用SpringApplicationBuilder加载具有父子关系的多个上下文
        // new SpringApplicationBuilder()
        //         .listeners(new MyListener())
        //         .sources(SpringBootLearnApplication.class)
        //         .bannerMode(Banner.Mode.OFF)
        //         .run(args);
    }

    /**
     * 返回特定的退出代码
     */
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 123;
    }

    /**
     * 打成war包，需要指明入口类
     */
    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    //     return builder.sources(SpringBootLearnApplication.class);
    // }
}
