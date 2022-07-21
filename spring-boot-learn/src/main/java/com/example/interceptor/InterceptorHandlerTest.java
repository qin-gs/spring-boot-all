package com.example.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class InterceptorHandlerTest implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("InterceptorTest.preHandle");
		HandlerMethod method = (HandlerMethod) handler;
		System.out.println("method.getMethod() = " + method.getMethod());
		System.out.println("method.getMethodParameters() = " + Arrays.toString(method.getMethodParameters()));
		System.out.println("method.getBeanType() = " + method.getBeanType());
		return true;
	}
}
