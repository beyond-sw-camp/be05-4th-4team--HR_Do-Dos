package com.teamdev.todo.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ERROR = "error";

	private String status;
	private T data;
	private Object message;

	private ApiResponse(String status, T data, Object message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public static <T> ApiResponse<T> createSuccess(T data) {
		return new ApiResponse<>(SUCCESS, data, null);
	}

	public static <T> ApiResponse<T> createSuccessWithMessage(T data, String message) {
		return new ApiResponse<>(SUCCESS, data, message);
	}

	public static ApiResponse<?> createSuccessWithNoContent() {
		return new ApiResponse<>(SUCCESS, null, null);
	}

	/* Hibernate Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환 */
	public static ApiResponse<?> createFail(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();

		List<ObjectError> allErrors = bindingResult.getAllErrors();
		for (ObjectError error : allErrors) {
			if (error instanceof FieldError) {
				errors.put(((FieldError)error).getField(), error.getDefaultMessage());
			} else {
				errors.put(error.getObjectName(), error.getDefaultMessage());
			}
		}
		return new ApiResponse<>(FAIL, null, errors);
	}

	/* 예외 발생으로 API 호출 실패 시 반환 */
	public static ApiResponse<?> createError(String message) {
		return new ApiResponse<>(ERROR, null, message);
	}
}
