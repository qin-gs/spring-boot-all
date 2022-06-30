package com.example.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Validated
@Component
@PropertySource("classpath:config/mail.properties")
@ConfigurationProperties(prefix = "app.system")
public class AppSystemProperties {

    /**
     * 修改单位(默认是毫秒)，可以用注解，也可以在配置文件中直接写 10s
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout;
    private Duration readTimeout;

    @NotNull
    private DataSize bufferSize;
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize thresholdSize;

    public Duration getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Duration sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    public DataSize getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(DataSize bufferSize) {
        this.bufferSize = bufferSize;
    }

    public DataSize getThresholdSize() {
        return thresholdSize;
    }

    public void setThresholdSize(DataSize thresholdSize) {
        this.thresholdSize = thresholdSize;
    }

    @Override
    public String toString() {
        return "AppSystemProperties{" +
                "sessionTimeout=" + sessionTimeout +
                ", readTimeout=" + readTimeout +
                ", bufferSize=" + bufferSize +
                ", thresholdSize=" + thresholdSize +
                '}';
    }
}
