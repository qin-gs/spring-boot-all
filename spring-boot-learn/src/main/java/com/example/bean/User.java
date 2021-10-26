package com.example.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

@Component
// @PropertySource("classpath:application.yml") // 指定其他配置文件里的数据 + @Value
// @ConfigurationProperties(prefix = "user") // 加载默认配置文件里的数据
@Validated
public class User {
    private String name;
    // @Pattern(regexp = "^/w*$", message = "名称不符合规则")
    private String fullName;
    private int age;
    @Email(message = "邮箱不符合规则")
    private String email;
    private List<String> list;
    private Map<String, Object> map;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", list=" + list +
                ", map=" + map +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getList() {
        return list;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
