package com.techkieventures.adminservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringConfiguration {

	 @Bean
	 OpenAPI springShopOpenAPI() { 
		 return new OpenAPI() 
		 .info(new Info().title("Admin Service REST API") 
		 .description("Generated Documentation for Admin Service")); 
	 } 
}
