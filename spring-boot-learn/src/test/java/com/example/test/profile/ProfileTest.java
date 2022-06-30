package com.example.test.profile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@ActiveProfiles("remote")
@SpringBootTest
@Transactional
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@DisplayName("profile 测试")
public class ProfileTest {

	private MockMvc mockMvc;
	@Autowired
	private ApplicationContext context;

	@Test
	public void test() {
		String[] names = context.getBeanDefinitionNames();
		System.out.println(Arrays.toString(names));
	}
}
