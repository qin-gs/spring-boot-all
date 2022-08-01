package com.example.config;

import com.example.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@ServletComponentScan("com.example.filter")
public class BeanConfig {

    /**
     * 注册过滤器第二种方式
     */
    @Bean
    public FilterRegistrationBean<MyFilter> registrationBean() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("myFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
