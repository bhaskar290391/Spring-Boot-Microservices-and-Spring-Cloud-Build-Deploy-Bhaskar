package com.appdeveloperblogs.photoapp.users.ms.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appdeveloperblogs.photoapp.users.ms.dto.UserDTO;

public interface UserService extends UserDetailsService{
	
	UserDTO createUsers(UserDTO userDeatils);

	UserDTO getUserDetailByEmail(String username);
}
