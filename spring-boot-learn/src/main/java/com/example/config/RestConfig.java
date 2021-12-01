package com.example.config;

import com.example.bean.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    private static final Logger log = LoggerFactory.getLogger(RestConfig.class);

    @Value("${server.port}")
    private int port;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    @Bean
    public CommandLineRunner runner(RestTemplate template) {
        return args -> {
            log.info("使用 RestTemplate 获取数据");
            Book book = template.getForObject("http://localhost:" + port + "/getBookById/1", Book.class);
            log.info(book.toString());
        };
    }
}
