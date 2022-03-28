package com.getircase.readingisgood.application.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.getircase.readingisgood"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Reading Is Good Api Rest service",
                "This application provides api for building Online Book Reatil Management application.",
                "1.0.0",
                "Terms of Service Url",
                new Contact("Remziye Sahin", "https://www.linkedin.com/in/remziye-sahin-639838a7/", "remziye.csahin@gmail.com"),
                "Apache 2.0","LICENSE URL",  Collections.emptyList());
    }

}
