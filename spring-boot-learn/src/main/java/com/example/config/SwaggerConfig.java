package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等。
 *
 * <pre><code>
 * {@code @Api }修饰整个类，描述类的作用
 * {@code @ApiOperation }描述一个类的一个方法，或者说一个接口
 * {@code ApiImplicitParams } 用在方法上描述一组参数的作用
 * {@code ApiImplicitParam } 用在方法上描述一组参数的作用
 *   name 参数名
 *   value 参数的意思
 *   required 是否必须
 *   dataType 参数类型
 *   defaultValue 默认值
 *   paramType 参数放在哪个地方
 *     header: @RequestHeader
 *     query: @RequestParam
 *     path: @PathVariable
 *     body: ...
 *     form: ...
 * {@code @ApiParam } 单个参数描述
 * {@code @ApiModel } 用对象来接收参数
 * {@code @ApiProperty } 用对象接收参数时，描述对象的一个字段
 * {@code @ApiResponses } HTTP响应整体描述
 * {@code @ApiResponse } HTTP响应其中一个描述 {code: 400, message: 参数错误, response: SwaggerController.class}
 * {@code @ApiIgnore }使用该注解忽略这个API
 * {@code @ApiError }发生错误返回的信息
 * </code></pre>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(appInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                .title("springboot swagger 文档")
                .description("swagger 功能测试")
                .termsOfServiceUrl("https://www.example.com")
                .version("1.0")
                .build();
    }
}
