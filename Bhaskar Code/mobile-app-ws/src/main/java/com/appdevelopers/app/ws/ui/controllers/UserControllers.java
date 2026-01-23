package com.appdevelopers.app.ws.ui.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserControllers {

	@PostMapping
	public String createUsers() {
		return "Create user was called";
	}

	@GetMapping
	public String getUsers() {
		return "Get user was called";
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
