package com.cooksystems.assessment.team2.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.mappers.UserMapper;
import com.cooksystems.assessment.team2.api.repositories.UserRepository;
import com.cooksystems.assessment.team2.api.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	private final UserMapper userMapper;

	@Override
	public List<UserResponseDto> getAllUsers() {
		
		return userMapper.entitiesToResponseDtos(userRepository.findAllByDeletedFalse());
	}
	
	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		
		User savedUser = userMapper.userRequestDtoToEntity(userRequestDto);
		return userMapper.entityToDto(userRepository.saveAndFlush(savedUser));
	}

}
