package com.example.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.interceptor.InterceptorHandlerTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// 用WebMvcConfigurer接口 扩展spring mvc
// 如果用@EnableWebMvc 会全面接管mvc
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器的方式
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 测试拦截器的第三个参数 (HandlerMethod)
        registry.addInterceptor(new InterceptorHandlerTest());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cors").setViewName("cors");
    }

    /**
     * 注册语言解析器
     */
    // @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    /**
     * 将对象转换成json
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FastJsonHttpMessageConverter());
    }
}
