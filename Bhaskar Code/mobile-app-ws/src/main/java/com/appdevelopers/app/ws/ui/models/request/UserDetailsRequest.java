package com.appdevelopers.app.ws.ui.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetailsRequest {

	@NotNull(message = "Firstname cannot be null")
	@Size(min = 2,message = "First Name cannot be less than 2 characters")
	private String firstName;

	@NotNull(message = "LastName cannot be null")
	@Size(min = 2,message = "Last Name cannot be less than 2 characters")
	private String lastName;
	
	@NotNull(message = "password cannot be null")
	@Size(min = 8,max = 20)
	private String password;
	
	@NotNull(message = "email cannot be null")
	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
