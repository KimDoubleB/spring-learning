package com.lab.etag;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class EtagConfig {

	@Bean
	public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
		var shallowEtagHeaderFilter = new FilterRegistrationBean<>(new ShallowEtagHeaderFilter());
		shallowEtagHeaderFilter.addUrlPatterns("/sample/etag/*");
		return shallowEtagHeaderFilter;
	}

}
