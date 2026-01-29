package com.appdeveloperblogs.photoapp.users.ms.config;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private Environment environment;

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		
		System.out.println("===> "+ environment.getProperty("LOCAL_IP"));
		InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("Local IP Address: " + inetAddress.getHostAddress());
        System.out.println("Host Name: " + inetAddress.getHostName());
        System.out.println("Gateway IP: " + environment.getProperty("gateway.ip"));


		
		http.csrf((cus)-> cus.disable());
		http.authorizeHttpRequests((auth) -> auth.requestMatchers(new AntPathRequestMatcher("/users"))
				.permitAll()
				//.access(new WebExpressionAuthorizationManager("hasIpAddress('"+environment.getProperty("gateway.ip")+"')"))
				.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll())
		.sessionManagement(sesssion-> sesssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.headers( header -> header.frameOptions(frame -> frame.sameOrigin()));
				
				
		return http.build();
	}
}
