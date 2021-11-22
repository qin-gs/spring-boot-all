package com.example.yaml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;

@DisplayName("yaml 文件读取")
public class YamlTest {

    /**
     * YamlPropertiesFactoryBean -> Properties
     * YamlMapFactoryBean -> Map
     * 会将yaml公开为PropertySource，可以使用 @Value 直接注入
     *
     * 无法使用 @PropertySource注解 加载yaml文件
     */
    @Test
    public void test() {

    }
}
