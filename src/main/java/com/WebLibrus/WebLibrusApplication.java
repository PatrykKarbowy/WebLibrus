package com.WebLibrus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableSwagger2
public class WebLibrusApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebLibrusApplication.class, args);
	}
	@Bean
	public Docket get(){
		return new Docket(DocumentationType.SWAGGER_12)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.build();
	}

}
