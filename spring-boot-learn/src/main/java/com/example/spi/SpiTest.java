package com.example.spi;

import java.util.ServiceLoader;

/**
 * springboot使用spi技术，使用内置tomcat注册DispatcherServlet
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<Shout> load = ServiceLoader.load(Shout.class);
        load.forEach(Shout::shout);
    }
}
