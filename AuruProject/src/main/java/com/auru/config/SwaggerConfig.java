package com.auru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket greatLearning() 
	{

Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
					groupName("LabProject").select().
					apis(RequestHandlerSelectors.basePackage("com.auru.controller"))
					.build();
		return docket;
	}

	private ApiInfo apiInfo() {

		ApiInfo api = new ApiInfoBuilder().build();
		return api;
	}
	
}
