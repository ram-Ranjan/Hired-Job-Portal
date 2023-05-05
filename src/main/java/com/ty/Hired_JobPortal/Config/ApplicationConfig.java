package com.ty.Hired_JobPortal.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {

	@Bean
	public Docket getDocket()
	{
		 Contact contact = new Contact("TestYantra", "www.testyantra.com", "testyantra@gmail.com");
		List<VendorExtension> extensions = new ArrayList<>();
		ApiInfo apiInfo = new ApiInfo("Hired_JobPortal App", "Search and Apply for Jobs", "1.01", "14 days of free service",
				contact, "www.testyantra.com", "www.testyantra.com", extensions);
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ty.Hired_JobPortal")).build()
				.apiInfo(apiInfo).useDefaultResponseMessages(false);
	}

}
