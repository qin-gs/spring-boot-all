package com.example.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncExecutor {

    public Executor executor() {
        // ThreadPoolExecutor executor = new ThreadPoolExecutor();
        // return executor;
        return null;
    }
}
