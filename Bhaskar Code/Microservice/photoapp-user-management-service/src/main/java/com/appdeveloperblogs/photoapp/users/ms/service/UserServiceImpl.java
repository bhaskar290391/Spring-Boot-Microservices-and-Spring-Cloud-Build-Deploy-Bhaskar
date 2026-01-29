package com.appdeveloperblogs.photoapp.users.ms.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdeveloperblogs.photoapp.users.ms.Entity.UserEntity;
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

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDetails, UserEntity.class);
		userEntity.setEncryptedPassword("test");

		userEntity = repository.save(userEntity);

		UserDTO returnValue = mapper.map(userEntity, UserDTO.class);

		return returnValue;
	}

}
