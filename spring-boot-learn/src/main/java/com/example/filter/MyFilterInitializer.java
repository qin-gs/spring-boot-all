package com.example.filter;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;

@Component
public class MyFilterInitializer implements ServletContextInitializer {
	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
		FilterRegistration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
	}

}
