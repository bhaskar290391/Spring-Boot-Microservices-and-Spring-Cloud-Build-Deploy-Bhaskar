package com.appdeveloperblogs.photoapp.users.ms.controller;

import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloperblogs.photoapp.users.ms.dto.UserDTO;
import com.appdeveloperblogs.photoapp.users.ms.model.CreateUserRequestModel;
import com.appdeveloperblogs.photoapp.users.ms.model.CreateUserResponseModel;
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
	public ResponseEntity<CreateUserResponseModel> createUsers(@Valid @RequestBody CreateUserRequestModel userDetails) {
		
		ModelMapper mapper =new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDTO userDTO = mapper.map(userDetails, UserDTO.class);
		
		UserDTO createdValue = service.createUsers(userDTO);
		
		CreateUserResponseModel returnValue = mapper.map(createdValue, CreateUserResponseModel.class);
		return new ResponseEntity<CreateUserResponseModel>(returnValue, null, HttpStatus.SC_CREATED);
	}
}
