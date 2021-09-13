package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

// @Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * druid监控
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        Map<String, String> map = new HashMap<>();
        map.put("loginUsername", "root");
        map.put("loginPassword", "root");
        map.put("allow", "");
        bean.setInitParameters(map);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> webStateFilter() {
        FilterRegistrationBean<WebStatFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new WebStatFilter());
        Map<String, String> map = new HashMap<>();
        map.put("exclusions", "*.js,*.css,/druid/");

        filter.setInitParameters(map);
        return filter;
    }
}
