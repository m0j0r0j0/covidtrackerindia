package com.prad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class CovidtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidtrackerApplication.class, args);
	}

	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
