package com.teamdev.todo.ctrl.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamdev.todo.api.ApiResponse;
import com.teamdev.todo.data.dto.user.UserSignInRequestDto;
import com.teamdev.todo.data.dto.user.UserSignInResponseDto;
import com.teamdev.todo.data.entity.user.User;
import com.teamdev.todo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final UserRepository userRepository;

	private final HttpSession session;

	@PostMapping("/sign-in")
	public ApiResponse<?> login(@RequestBody UserSignInRequestDto user) {
		User responseUser = userRepository.findByUserId(user.getUserId());
		if (responseUser.getUserPassword().equals(user.getUserPassword())) {
			session.setAttribute("user", responseUser);
			UserSignInResponseDto userDto = new UserSignInResponseDto( responseUser.getUserNo(), responseUser.getUserId(),
				responseUser.getUserName());
			return ApiResponse.createSuccessWithMessage(userDto, "Login success");
		} else {
			return ApiResponse.createError("Login Failed");
		}
	}

	@PostMapping("/sign-out")
	public ApiResponse<String> logout() {
		session.invalidate();
		return ApiResponse.createSuccess("Logout success");
	}

}
