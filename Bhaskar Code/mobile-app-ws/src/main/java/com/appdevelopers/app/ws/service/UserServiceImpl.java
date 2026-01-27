package com.appdevelopers.app.ws.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdevelopers.app.ws.ui.models.request.UserDetailsRequest;
import com.appdevelopers.app.ws.ui.models.response.UserRest;
import com.appdevelopers.app.ws.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	private Map<String, UserRest> usersData;
	
	private Utils utils;
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}




	@Override
	public UserRest createUser(UserDetailsRequest userDeatils) {
		UserRest data = new UserRest();
		data.setFirstName(userDeatils.getFirstName());
		data.setLastName(userDeatils.getLastName());
		data.setEmail(userDeatils.getEmail());

		String userId = utils.generateUserId();
		
		data.setUserId(userId);

		return data;
	}

}
