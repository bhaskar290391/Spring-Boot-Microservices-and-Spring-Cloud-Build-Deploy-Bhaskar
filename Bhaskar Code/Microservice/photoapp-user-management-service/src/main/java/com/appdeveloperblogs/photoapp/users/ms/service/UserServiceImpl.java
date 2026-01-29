package com.appdeveloperblogs.photoapp.users.ms.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appdeveloperblogs.photoapp.users.ms.Entity.UserEntity;
import com.appdeveloperblogs.photoapp.users.ms.dao.UserRepository;
import com.appdeveloperblogs.photoapp.users.ms.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDTO createUsers(UserDTO userDetails) {

		String userId = UUID.randomUUID().toString();
		userDetails.setUserId(userId);

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDetails, UserEntity.class);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		userEntity = repository.save(userEntity);

		UserDTO returnValue = mapper.map(userEntity, UserDTO.class);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity data = repository.findByEmail(username);

		if (data == null)
			throw new UsernameNotFoundException(username);
		return new User(data.getEmail(), data.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDTO getUserDetailByEmail(String username) {
		UserEntity data = repository.findByEmail(username);

		if (data == null)
			throw new UsernameNotFoundException(username);
		return new ModelMapper().map(data, UserDTO.class);
	}

}
