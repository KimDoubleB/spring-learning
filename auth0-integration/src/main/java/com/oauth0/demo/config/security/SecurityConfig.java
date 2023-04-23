package com.oauth0.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/*
		// `/` 페이지를 제외한 모든 페이지 로그인 요구
		http.authorizeRequests()
			.mvcMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and().oauth2Login();
		 */

		return http
			.oauth2Login()
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.addLogoutHandler(logoutHandler)
			.and().build();
	}

}
