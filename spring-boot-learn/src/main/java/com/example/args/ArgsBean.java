package com.example.args;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 可以通过ApplicationArguments获取run方法中传入的参数
 */
@Component
public class ArgsBean {

    Logger logger = LoggerFactory.getLogger(ArgsBean.class);

    @Autowired
    public ArgsBean(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> nonOptionArgs = args.getNonOptionArgs();
        logger.info(String.valueOf(debug));
        logger.info(String.valueOf(nonOptionArgs));
    }
}
