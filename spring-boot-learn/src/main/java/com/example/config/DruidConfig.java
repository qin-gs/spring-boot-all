package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code @EnableConfigurationProperties}注解的作用是：让使用 {@code @ConfigurationProperties} 注解的类生效。
 */
// @Configuration
@EnableConfigurationProperties
public class DruidConfig {

    /**
     * 该注解会将配置文件中以 spring.datasource 开头的属性注入进去
     */
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
