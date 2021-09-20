package com.example.listener;

import com.example.args.ArgsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * 可以通过在META-INF/spring.factories文件中添加监听器自动注册
 * 不需要添加@Component
 * <pre>
 * ApplicationStartedEvent
 * ApplicationEnvironmentPreparedEvent
 * ApplicationPreparedEvent
 * ApplicationStartedEvent
 * ApplicationReadyEvent
 * ApplicationFailedEvent
 * </pre>
 */
public class MyListener implements ApplicationListener<ApplicationStartingEvent> {
    Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.err.println(event);
    }
}
