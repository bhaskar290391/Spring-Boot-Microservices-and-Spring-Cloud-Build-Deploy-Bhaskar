package com.appdeveloperblogs.photoapp.users.ms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appdeveloperblogs.photoapp.users.ms.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
			throws Exception {

		AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

		managerBuilder.userDetailsService(service).passwordEncoder(encoder);
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").access(
				new IpBasedAuthorizationManager(environment))  
				.requestMatchers("/users/status/check").access(
						new IpBasedAuthorizationManager(environment))
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated());

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

		// Register your custom filter
		AuthenticationFilters filter = new AuthenticationFilters(service, environment, authenticationManager);
		filter.setFilterProcessesUrl(environment.getProperty("login.path.url"));
		http.addFilter(filter);

		http.authenticationManager(authenticationManager);

		return http.build();
	}
}
