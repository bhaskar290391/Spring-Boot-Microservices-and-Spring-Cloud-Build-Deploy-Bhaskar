package com.appdevelopers.app.ws.ui.models.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserDetailsRequest {

	@NotNull(message = "Firstname cannot be null")
	@Size(min = 2,message = "First Name cannot be less than 2 characters")
	private String firstName;

	@NotNull(message = "LastName cannot be null")
	@Size(min = 2,message = "Last Name cannot be less than 2 characters")
	private String lastName;

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
	
	
	
}
