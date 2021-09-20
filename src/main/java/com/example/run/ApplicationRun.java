package com.example.run;

import com.example.args.ArgsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 可以添加一些项目启动后需要初始化的功能
 * order越小优先级越高
 */
@Component
public class ApplicationRun implements ApplicationRunner, Ordered {
    Logger logger = LoggerFactory.getLogger(ApplicationRun.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("application run");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
