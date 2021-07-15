package com.api.challenge.config;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig{
	
	@Bean
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.api.challenge.controllers"))
				.paths(PathSelectors.any())
				.build();
				
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Challenge alkemy",
				"Descripcion",
				"1.0",
				"Todos los derechos reservados",
				new Contact("Enzo Ruiz Diaz","https://github.com/enzoruizdiaz","julianruizdiaz96@hotmail.com"),
				"licencia",
				"Api Licencia",
				Collections.emptyList());
				
				
				
	}
	

}
