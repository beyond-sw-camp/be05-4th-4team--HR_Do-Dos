package com.teamdev.todo.data.dto.user;

import com.teamdev.todo.data.entity.user.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserSignUpRequestDto {

	@Size(min = 3, max = 25)
	@NotBlank
	private String userId;

	@NotBlank
	private String userPassword;

	@NotBlank
	private String userName;

	// TODO: 비밀번호 암호화
	public User toEntity(UserSignUpRequestDto params) {
		return User.builder()
			.userId(params.getUserId())
			.userPassword(params.getUserPassword())
			.userName(params.getUserName())
			.build();
	}
}
