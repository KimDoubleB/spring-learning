package com.version.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureApiVersioning(ApiVersionConfigurer configurer) {
		configurer
				.useRequestParam("version")
				.useRequestHeader("X-API-Version")
				.setDefaultVersion("1.0.0")
				.addSupportedVersions("1.0.0", "2.0.0")
				.setVersionRequired(true);

	}

}
