package com.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncTaskService {

    Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

    @Async
    public Future<String> task1() throws InterruptedException {
        logger.info("task1 start");
        Thread.sleep(5000);
        logger.info("task1 finished");
        return new AsyncResult<>( "task1 success");
    }

    @Async
    public Future<String> task2() throws InterruptedException {
        logger.info("task2 start");
        Thread.sleep(5000);
        logger.info("task2 finished");
        return new AsyncResult<>( "task2 success");
    }
}
