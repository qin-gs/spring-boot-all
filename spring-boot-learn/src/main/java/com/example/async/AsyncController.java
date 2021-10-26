package com.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class AsyncController {
    Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncTaskService service;

    @GetMapping("task")
    public String task() throws InterruptedException, ExecutionException {
        long start = Instant.now().toEpochMilli();
        Future<String> task1 = service.task1();
        Future<String> task2 = service.task2();
        for (;;) {
            if (task1.isDone() && task2.isDone()) {
                logger.info(task1.get());
                logger.info(task2.get());
                break;
            }
        }
        Thread.sleep(1000);
        long end = Instant.now().toEpochMilli();
        logger.info("时间: " + (end - start));
        return "success";
    }
}
