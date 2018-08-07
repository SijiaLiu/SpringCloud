package com.lsj.settlement.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * class_name: swaggerConfig
 * package: com.lsj.settlement.config
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/25
**/

@Configuration
@EnableSwagger2
public class swaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lsj.settlement.sys.controller")) //指定包下的controller
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("刘斯佳测试API文档")
                .description("liusijia_cloud Platform Management core API")
                .termsOfServiceUrl("http://www.google.com")
                .contact(new springfox.documentation.service.Contact("刘斯佳","http://www.google.com","79891847@qq.com"))
                .license("http://www.google.com")
                .version("1.0")
                .build();
//        ApiInfo apiInfo = new ApiInfo("刘斯佳测试API文档","liusijia_cloud Platform Management core API",
//                "1.0", "http://www.google.com", "刘斯佳", "http://www.google.com",
//                "http://www.google.com");
//        return apiInfo;

    }

}
