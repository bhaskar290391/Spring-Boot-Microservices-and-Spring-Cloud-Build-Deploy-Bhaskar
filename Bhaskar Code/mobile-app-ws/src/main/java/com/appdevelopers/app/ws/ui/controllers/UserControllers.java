package com.appdevelopers.app.ws.ui.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdevelopers.app.ws.ui.models.response.UserRest;

@RestController
@RequestMapping("users")
public class UserControllers {

	@GetMapping
	public String getUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "50") int limit,
			@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {
		return "Get user was called with userId page " + page + " limit : " + limit + " sort :" + sort;
	}

	@GetMapping("/{userId}")
	public UserRest getUsersById(@PathVariable String userId) {
		UserRest returnValue=new UserRest(userId, "Bhaskar", "Mudaliyar", "maddy@gmail.com");
		return returnValue;
	}

	@PostMapping
	public String createUsers() {
		return "Create user was called";
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
