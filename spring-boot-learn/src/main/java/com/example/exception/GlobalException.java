package com.example.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 之前web的处理方式
 */
// @Component
public class GlobalException implements HandlerExceptionResolver {
    /**
     * 如果Controller中出现异常，如果没有异常处理，会进入这里
     *
     * @param handler 当前出现错误的方法对象
     * @param ex      异常对象
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        if (ex instanceof RuntimeException) {
            modelAndView.setViewName("500");
        } else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }
}
