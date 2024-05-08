package com.teamdev.todo.data.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {

	private Long userNo;

	private String userId;

	private String userName;

}
