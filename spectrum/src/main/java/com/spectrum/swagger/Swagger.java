package com.spectrum.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spectrum.constants.ApiConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger {
	private static final String SWAGGER_API_VERSION = "1.0";
	private static final String LICENSE_TEXT = "License";
	private static final String TITLE = "Spectrum RESTFul API";
	private static final String DESCRIPTION = "RESTful APIs for Spectrum";
	public static final Contact DEFAULT_CONTACT = new Contact("Pawan Maurya", "http://github.com/mistermaurya",
			"pawank@thelattice.in");

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION).license(LICENSE_TEXT).contact(DEFAULT_CONTACT)
				.version(SWAGGER_API_VERSION).build();
	}

	@Bean
	public Docket bostonApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.tags(new Tag(ApiConstants.USER_CONTROLLER_TAG, ApiConstants.USER_CONTROLLER_DESCRIPTION),
						new Tag(ApiConstants.USER_LOGIN_TAG, ApiConstants.USER_LOGIN_DESCRIPTION))
				.select().apis(RequestHandlerSelectors.basePackage("com.spectrum")).build().enableUrlTemplating(true)
				.securitySchemes(Arrays.asList(apiKey()));
	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, "Authorization", "");
	}
	
}
