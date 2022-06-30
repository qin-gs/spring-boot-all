package com.example.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <pre>
 * Scheduler：管理 Trigger 和 Job，并保证 Job 能在 Trigger 设置的时间被触发执行
 *
 * Job：定义任务具体的逻辑 (QuartzJobBean)
 *
 * JobDetail：任务详情
 *
 * Trigger：触发器，设置 job 什么时候执行
 *   SimpleTrigger：简单触发器，适用于 按指定的时间间隔执行多少次任务的情况
 *   CronTrigger：Cron触发器，通过Cron表达式来控制任务的执行时间
 *   DailyTimeIntervalTrigger：日期触发器，在给定的时间范围内或指定的星期内以秒、分钟或者小时为周期进行重复的情况
 *   CalendarIntervalTrigger：日历触发器，根据一个给定的日历时间进行重复
 *
 * 一个Trigger只能绑定一个Job。但是一个Job可以被多个Trigger绑定
 * </pre>
 */
@Configuration
public class QuartzConfig {

	// @Bean
	// public Scheduler scheduler(QuartzJobFactory factory) throws Exception {
	// 	SchedulerFactoryBean bean = new SchedulerFactoryBean();
	// 	bean.setJobFactory(factory);
	// 	bean.afterPropertiesSet();
	//
	// 	Scheduler scheduler = bean.getScheduler();
	// 	scheduler.start();
	//
	// 	return scheduler;
	// }

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob(DateTimeJob.class)
				.withIdentity("date time jon")
				.usingJobData("msg", "hello quartz")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger trigger() {
		// second        0-59
		// minutes       0-59
		// hour          0-23
		// day-of-month  0-31
		// month         1-12
		// day-of-week   1-7
		// year          1970-2099(可忽略)

		// *             所有可能值
		// 3/30          从第三分钟开始，每20分钟
		// ?             dayOfMonth, dayOfWeek 其中一个被指定了值后，需要将另一个设为 ? (不指定值)
		// 9-12          9 点到 12 点
		// 3,6,9         每三点，六点，九点
		// W             工作日
		// L             dayOfMonth 一个月的最后一天；dayOfWeek 一周的最后一天
		CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail())
				.withIdentity("a trigger")
				.withSchedule(cronSchedule)
				.build();
	}
}
