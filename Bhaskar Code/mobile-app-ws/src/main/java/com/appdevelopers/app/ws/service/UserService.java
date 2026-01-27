package com.appdevelopers.app.ws.service;

import com.appdevelopers.app.ws.ui.models.request.UserDetailsRequest;
import com.appdevelopers.app.ws.ui.models.response.UserRest;

public interface UserService {
	 UserRest createUser(UserDetailsRequest request);
}
