package com.example.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

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
