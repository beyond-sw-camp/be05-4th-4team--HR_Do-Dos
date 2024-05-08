package com.teamdev.todo.ctrl.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamdev.todo.api.ApiResponse;
import com.teamdev.todo.data.dto.user.UserSignUpRequestDto;
import com.teamdev.todo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/sign-up")
	public ApiResponse<?> signUp(@Valid @RequestBody UserSignUpRequestDto params) {
		if (!userService.signUp(params)) {
			return ApiResponse.createError("회원가입에 실패했습니다");
		}

		return ApiResponse.createSuccessWithNoContent();
	}
}