### springboot启动

原来使用web.xml初始化Spring

提供配置文件

```html
<context-param>
	<param-name>contextConfigLocation</param-name>
  <param-value>classpath:applicationContext.xml</param-value>
</context-param>
```

`<listener>`启动时去加载`<context-param>`里面的参数，启动spring容器

```html
web项目的入口在tomcat(不能用main方法new AnnotationConfigApplicationContext()初始化容器)
所以用listener用来初始化spring的context环境，上面的<context-param>提供配置文件
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

配置servlet

```html
配置servlet，或使用@WebServlet注解(servlet2.6加入，tomcat7及以上支持)(websocket只能运行在tomcat8及以上)
<servlet>
	<servlet-name>spring mvc</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
  	<param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring-mvc.xml</param-value>
  </init-param>
</servlet>
```

配置文件功能(包扫描 + bean声明)



`web.xml` 被 `WebApplicationInitializer` 替代

springboot中如何实现上述功能

1. `AnnotationConfigWebApplicationContext`完成context的初始化和bean注册
2. 使用`WebApplicationInitializer`完成`DispatcherServlet`注册





