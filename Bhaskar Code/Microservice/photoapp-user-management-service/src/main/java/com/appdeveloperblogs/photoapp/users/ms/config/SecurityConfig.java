package com.appdeveloperblogs.photoapp.users.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		
		http.csrf((cus)-> cus.disable());
		http.authorizeHttpRequests((auth) -> auth.requestMatchers(new AntPathRequestMatcher("/users")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll())
		.sessionManagement(sesssion-> sesssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.headers( header -> header.frameOptions(frame -> frame.sameOrigin()));
				
				
		return http.build();
	}
}
