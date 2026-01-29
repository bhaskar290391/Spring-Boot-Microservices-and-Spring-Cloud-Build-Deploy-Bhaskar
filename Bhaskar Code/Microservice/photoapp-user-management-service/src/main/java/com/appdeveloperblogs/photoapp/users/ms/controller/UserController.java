package com.appdeveloperblogs.photoapp.users.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloperblogs.photoapp.users.ms.model.CreateUserRequestModel;
import com.appdeveloperblogs.photoapp.users.ms.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment env;
	
	@Autowired
	private UserService service;

	@GetMapping("/status/check")
	public String greet() {
		return "Working" + env.getProperty("local.server.port");
	}

	@PostMapping
	public String createUsers(@Valid @RequestBody CreateUserRequestModel userDetails) {
		return "User Create was called ";
	}
}
