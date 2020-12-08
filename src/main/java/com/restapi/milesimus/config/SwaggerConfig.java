package com.restapi.milesimus.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket produtoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.restapi.milesimus"))
                .paths(regex("/pet.*"))
                .build()
                .apiInfo(metaInfo());
    }


    private ApiInfo metaInfo() {
    ApiInfo apiInfo = new ApiInfo(
            "Produtos API REST","API de cadastro de pets em estabelecimento","1.0","Terms of Service",
            new Contact("Bruno Felipe Magalhães","https://github.com/obamis","brunomagalhaes@usp.br" ),
            "Apache License Version 2.0",
            "https://www.apache.org/license.html", new ArrayList<VendorExtension>()
    );
    return apiInfo;

    }

    }


