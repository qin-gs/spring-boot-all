package com.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * <a href="https://plentymore.github.io/2018/12/29/Spring-EnableAsync-%E6%B3%A8%E8%A7%A3%E5%8E%9F%E7%90%86/">参考</a>
 * 被 @Async 注解的方法会返回值只能是 Future 或者 void，
 * <p>
 * 原理：AsyncExecutionInterceptor#invoke 方法
 * determineAsyncExecutor 方法拿到方法对应的线程池；
 * 执行代理对象的方法；
 * 判断代理对象的返回值类型，如果不是 Future 或者 void，返回 null；
 */
@Component
public class AsyncTaskService {

    Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

    @Async
    public Future<String> task1() throws InterruptedException {
        logger.info("task1 start");
        Thread.sleep(5000);
        logger.info("task1 finished");
        return new AsyncResult<>("task1 success");
    }

    @Async
    public Future<String> task2() throws InterruptedException {
        logger.info("task2 start");
        Thread.sleep(5000);
        logger.info("task2 finished");
        return new AsyncResult<>("task2 success");
    }
}
