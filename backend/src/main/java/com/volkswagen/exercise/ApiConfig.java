package com.volkswagen.exercise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class provides the url and other parameters configuration for the backend access.
 * Currently only "http://localhost:4200" is allowed which is frontend API and other parameters like
 * headers, REST methods all can be configured here.
 */
@Configuration
public class ApiConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			/** @descriptions This method configures cross origin requests */
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.maxAge(3600);
			}

			/** @descriptions This method restricts content type to application/json */
			@Override
			public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
				configurer.defaultContentType(MediaType.APPLICATION_JSON);
			}
		};
	}
}
