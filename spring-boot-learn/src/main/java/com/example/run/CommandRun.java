package com.example.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 可以添加一些项目启动后需要初始化的功能；
 * CommandLineRunner 先于 ApplicationRunner 执行
 */
@Component
public class CommandRun implements CommandLineRunner, Ordered {
    Logger logger = LoggerFactory.getLogger(CommandRun.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("command run");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
