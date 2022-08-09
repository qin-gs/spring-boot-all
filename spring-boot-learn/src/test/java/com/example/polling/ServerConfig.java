package com.example.polling;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@RestController
@SpringBootApplication
public class ServerConfig {
    /**
     * 服务端会维护 dataId 和长轮询的映射关系；可能多个客户端同时监听一个数据 (nacos 中 3000 个 dataId 包装成一个长轮询任务)
     */
    private final Multimap<String, AsyncTask> dataIdContext = Multimaps.synchronizedSetMultimap(HashMultimap.create());
    private final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("polling-%d").build();
    private final ScheduledExecutorService timeoutChecker = new ScheduledThreadPoolExecutor(1, threadFactory);

    public static void main(String[] args) {
        SpringApplication.run(ServerConfig.class, args);
    }

    @RequestMapping("/listener")
    public void addListener(HttpServletRequest request, HttpServletResponse response) {

        String dataId = request.getParameter("dataId");
        AsyncContext asyncContext = request.startAsync(request, response);
        AsyncTask asyncTask = new AsyncTask(asyncContext, true);

        dataIdContext.put(dataId, asyncTask);

        // 定时向客户端发送配置没改变信息，客户端发起新一轮长轮询
        timeoutChecker.schedule(() -> {
            if (asyncTask.isTimeout()) {
                // 将轮询移除，客户端重新发起长轮询
                dataIdContext.remove(dataId, asyncTask);
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                asyncContext.complete();
            }
        }, 30, TimeUnit.SECONDS);
    }

    /**
     * 服务端数据发生变更后，将变更后的数据写入轮询
     */
    @RequestMapping("publish")
    public String publish(String dataId, String config) throws IOException {
        // 将所有轮询移除
        Collection<AsyncTask> asyncTasks = dataIdContext.removeAll(dataId);
        for (AsyncTask asyncTask : asyncTasks) {
            asyncTask.setTimeout(false);
            HttpServletResponse response = (HttpServletResponse) asyncTask.getAsyncContext().getResponse();
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(config);
            asyncTask.getAsyncContext().complete();
        }
        return "success";
    }
}

class AsyncTask {
    private AsyncContext asyncContext;
    private boolean timeout;

    public AsyncTask(AsyncContext asyncContext, boolean timeout) {
        this.asyncContext = asyncContext;
        this.timeout = timeout;
    }

    public AsyncContext getAsyncContext() {
        return asyncContext;
    }

    public void setAsyncContext(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }
}
