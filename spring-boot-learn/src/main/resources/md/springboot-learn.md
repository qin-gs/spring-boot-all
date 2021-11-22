springboot 配置文件

```text
配置文件位置(优先级逐渐降低)
file:./config
file:./
classpath:/config/
classpath:/
```

springboot 静态资源

```text
ResourceProperties 默认静态资源四个存放位置(优先级逐渐降低)

classpath:/META-INF/resources/
classpath:/resources/
classpath:/static/
classpath:/public/

如果配置文件里配置了
spring:
  mvc:
    static-path-pattern: /myStatic

以上四个会失效
```

SpringBoot配置属性加载优先级及顺序(优先级逐渐降低)

1. 在您的主目录（当 devtools 被激活，则为 ~/.spring-boot-devtools.properties ）中的 Devtools 全局设置属性。
2. 在测试中使用到的 @TestPropertySource 注解。
3. 在测试中使用到的 properties 属性，可以是 @SpringBootTest和用于测试应用程序某部分的测试注解。
4. **命令行参数 --server.port=8081。**
5. 来自 SPRING_APPLICATION_JSON 的属性（嵌入在环境变量或者系统属性【system propert】中的内联 JSON）。
6. ServletConfig 初始化参数。
7. ServletContext 初始化参数。
8. 来自 java:comp/env 的 JNDI 属性。
9. Java 系统属性（System.getProperties()）。
10. 操作系统环境变量。
11. 只有 random.* 属性的 RandomValuePropertySource。
12. **在已打包的 jar 外部的指定 profile 的应用属性文件（application-{profile}.properties 和 YAML 变量）。**
13. 在已打包的 jar 内部的指定 profile 的应用属性文件（application-{profile}.properties 和 YAML 变量）。
14. **在已打包的 jar 外部的应用属性文件（application.properties 和 YAML 变量）。**
15. 在已打包的 jar 内部的应用属性文件（application.properties 和 YAML 变量）。
16. **在 @Configuration 类上的 @PropertySource 注解。**
17. 默认属性（使用 SpringApplication.setDefaultProperties 指定）。



WebMvcAutoConfiguration
