package com.example.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

/**
 * ApplicationContextInitializer 需要在 META-INF/spring.factories 中配置
 * 或者在 SpringApplication#addInitializers 中传入 ApplicationContextInitializer
 * <p>
 * 可以在 context#refersh 前修改 Environment
 * 可以在这里记录需要动态修改的字段，监听到事件后修改 (反射，销毁后重新创建 bean)
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		ConfigurableEnvironment environment = applicationContext.getEnvironment();

		MapPropertySource mySource = new MapPropertySource("mySource", Map.of("k1", "v1", "k2", "v2"));
		environment.getPropertySources().addLast(mySource);

		// 先销毁再创建，不需要自己反射修改 bean 中需要变更的属性
		// applicationContext.getAutowireCapableBeanFactory()
		// 		.destroyBean(bean);
		// applicationContext.getAutowireCapableBeanFactory()
		// 		.initializeBean(bean, name);

	}
}
