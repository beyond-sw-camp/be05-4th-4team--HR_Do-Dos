package com.teamdev.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamdev.todo.data.dto.user.UserSignUpRequestDto;
import com.teamdev.todo.data.entity.user.User;
import com.teamdev.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public Boolean signUp(UserSignUpRequestDto data) {
		User user = data.toEntity(data);
		userRepository.save(user);

		return userRepository.findByUserId(data.getUserId()) != null;
	}
}

