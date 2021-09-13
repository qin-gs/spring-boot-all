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

WebMvcAutoConfiguration
