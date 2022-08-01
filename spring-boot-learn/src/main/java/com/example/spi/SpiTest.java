package com.example.spi;

import java.util.ServiceLoader;

/**
 * springboot 使用 spi 技术，使用内置 tomcat 注册 DispatcherServlet
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<Shout> load = ServiceLoader.load(Shout.class);
        load.forEach(Shout::shout);
    }
}
