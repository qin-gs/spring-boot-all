package com.example.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

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
