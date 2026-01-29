package com.appdeveloperblogs.photoapp.users.ms.config;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appdeveloperblogs.photoapp.users.ms.dto.UserDTO;
import com.appdeveloperblogs.photoapp.users.ms.model.LoginRequestModel;
import com.appdeveloperblogs.photoapp.users.ms.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilters extends UsernamePasswordAuthenticationFilter {

	private Environment environment;

	private UserService service;

	public AuthenticationFilters(UserService service, Environment environment, AuthenticationManager manager) {
		super(manager);
		this.service = service;
		this.environment = environment;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		LoginRequestModel model = new LoginRequestModel();
		try {
			model = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword(), new ArrayList<>()));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = ((User) authResult.getPrincipal()).getUsername();
		UserDTO userDetail = service.getUserDetailByEmail(username);

		String tokenSecret = environment.getProperty("token.secret");
		byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
		SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);

		Instant now = Instant.now();

		String token = Jwts.builder().subject(userDetail.getUserId())
				.expiration(Date.from(now.plusMillis(Long.parseLong(environment.getProperty("token.expiration_time")))))
				.issuedAt(Date.from(now)).signWith(secretKey).compact();

		response.addHeader("token", token);
		response.addHeader("userId", userDetail.getUserId());
	}

}
