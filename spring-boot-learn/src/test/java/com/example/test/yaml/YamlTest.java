package com.example.test.yaml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

@DisplayName("yaml 文件读取")
public class YamlTest {

    /**
     * YamlPropertiesFactoryBean -> Properties
     * YamlMapFactoryBean -> Map
     * 会将yaml公开为PropertySource，可以使用 @Value 直接注入
     * <p>
     * 无法使用 @PropertySource注解 加载yaml文件
     */
    @Test
    public void test() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        // test 目录下好像正常没办法读取 src 下 resources 目录下文件
        yaml.setResources(new UrlResource(Objects.requireNonNull(this.getClass().getClassLoader().getResource("application.yml"))));
        Properties properties = yaml.getObject();
        System.out.println(properties);
    }
}
