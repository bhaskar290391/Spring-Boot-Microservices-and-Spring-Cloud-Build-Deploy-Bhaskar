package com.appdeveloperblogs.photoapp.users.ms.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdeveloperblogs.photoapp.users.ms.dao.UserRepository;
import com.appdeveloperblogs.photoapp.users.ms.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDTO createUsers(UserDTO userDetails) {

		String userId = UUID.randomUUID().toString();

		userDetails.setUserId(userId);
		return null;
	}

}
