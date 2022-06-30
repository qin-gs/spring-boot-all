package com.example.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 如何使MailPropertiesInject生效，三选一
 * <pre>
 * 1. @Component
 * 2. @Configuration + @Bean(修饰方法)
 * 3. @Configuration + @EnableConfigurationProperties(MailPropertiesInject.class)(修饰类)
 * </pre>
 */
@Component
@PropertySource(value = "classpath:config/mail.properties")
@ConfigurationProperties(prefix = "mail")
public class MailPropertiesInject {
    private Boolean enable = Boolean.TRUE;
    private String subject;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MailProperties{" +
                "enable=" + enable +
                ", subject='" + subject + '\'' +
                '}';
    }
}
