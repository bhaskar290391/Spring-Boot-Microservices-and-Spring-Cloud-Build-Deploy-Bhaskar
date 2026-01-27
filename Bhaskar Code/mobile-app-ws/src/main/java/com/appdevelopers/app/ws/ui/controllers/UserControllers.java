package com.appdevelopers.app.ws.ui.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdevelopers.app.ws.exception.UserServiceException;
import com.appdevelopers.app.ws.service.UserService;
import com.appdevelopers.app.ws.ui.models.request.UpdateUserDetailsRequest;
import com.appdevelopers.app.ws.ui.models.request.UserDetailsRequest;
import com.appdevelopers.app.ws.ui.models.response.UserRest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserControllers {

	private Map<String, UserRest> usersData;

	@Autowired
	private UserService service;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "50") int limit,
			@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {
		return "Get user was called with userId page " + page + " limit : " + limit + " sort :" + sort;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUsersById(@PathVariable String userId) {

		// throw new UserServiceException("Custome Exception");

		if (usersData.containsKey(userId)) {
			return new ResponseEntity<UserRest>(usersData.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> createUsers(@Valid @RequestBody UserDetailsRequest userDeatils) {

		UserRest user = service.createUser(userDeatils);
		if (usersData == null) {
			usersData = new HashMap<>();
		}

		usersData.put(user.getUserId(), user);
		return new ResponseEntity<UserRest>(user, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> updateUsers(@PathVariable String userId,
			@Valid @RequestBody UpdateUserDetailsRequest userDeatils) {

		UserRest userRest = usersData.get(userId);

		userRest.setFirstName(userDeatils.getFirstName());
		userRest.setLastName(userDeatils.getLastName());

		usersData.put(userId, userRest);

		return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUsers(@PathVariable String userId) {

		usersData.remove(userId);
		return ResponseEntity.noContent().build();
	}
}
