package com.appdevelopers.app.ws.ui.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdevelopers.app.ws.ui.models.request.UserDetailsRequest;
import com.appdevelopers.app.ws.ui.models.response.UserRest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserControllers {

	@GetMapping
	public String getUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "50") int limit,
			@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {
		return "Get user was called with userId page " + page + " limit : " + limit + " sort :" + sort;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUsersById(@PathVariable String userId) {
		UserRest returnValue = new UserRest(userId, "Bhaskar", "Mudaliyar", "maddy@gmail.com");
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.ACCEPTED);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes  = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> createUsers(@Valid @RequestBody UserDetailsRequest userDeatils) {
		
		UserRest data=new UserRest();
		data.setFirstName(userDeatils.getFirstName());
		data.setLastName(userDeatils.getLastName());
		data.setEmail(userDeatils.getEmail());
		
		return new ResponseEntity<UserRest>(data, HttpStatus.CREATED);
	}

	@PutMapping
	public String updateUsers() {
		return "Update user was called";
	}

	@DeleteMapping
	public String deleteUsers() {
		return "Delete user was called";
	}
}
