package com.example.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * springboot 中引入 quartz job 这个类不需要 @Component 也可以使用 @Value, @Autowired 等进行注入。
 * 因为 QuartzAutoConfiguration#quartzScheduler 方法中 setFactoryBean(new SpringBeanJobFactory()) 配置了 SpringBeanJobFactory 工厂，
 * 这个工厂的 createJobInstance 方法会判断当前是不是 spring 环境，是的话使用 BeanFactory 创建一个对象并注入对象中的属性，
 * 不是的话调用父类的方法反射创建对象
 */
// @Component
public class DateTimeJob extends QuartzJobBean {

	@Value("${server.port}")
	private String port;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
		String msg = dataMap.getString("msg");
		System.out.println(msg);
		System.out.println(port);
	}
}
