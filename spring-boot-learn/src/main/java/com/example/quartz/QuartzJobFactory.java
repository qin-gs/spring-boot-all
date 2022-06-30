package com.example.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 注入 spring 中的 job 对象
 */
@Component
public class QuartzJobFactory extends AdaptableJobFactory {

	private final AutowireCapableBeanFactory capableBeanFactory;

	public QuartzJobFactory(AutowireCapableBeanFactory capableBeanFactory) {
		this.capableBeanFactory = capableBeanFactory;
	}

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		// Class<?> jobClass = bundle.getJobDetail().getJobClass();
		// capableBeanFactory.autowireBean(jobClass);
		// return jobClass;
		return super.createJobInstance(bundle);
	}
}
