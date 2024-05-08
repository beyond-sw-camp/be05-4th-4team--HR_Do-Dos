package com.teamdev.todo.handler;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.teamdev.todo.api.ApiResponse;

@RestControllerAdvice(basePackages = "com.teamdev.todo")
public class ExceptionResponseHandler {
	@ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
	public ResponseEntity<ApiResponse<?>> handleCommonException(Exception e) {
		return ResponseEntity.badRequest().body(ApiResponse.createError(e.getMessage()));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<?>> handleAccessDeniedException() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.createError("접근이 거부되었습니다."));
	}

	// @ExceptionHandler(Exception.class)
	// public ResponseEntity<ApiResponse<?>> handleUnexpectedException() {
	// 	return ResponseEntity.internalServerError().body(ApiResponse.createError("서버에 문제가 발생했습니다."));
	// }

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ApiResponse<?>> handleDuplicatedUserException(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.createError(exception.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleValidationExceptions(BindingResult bindingResult) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.createFail(bindingResult));
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ApiResponse<?>> handleSignatureException() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.createError("토큰이 유효하지 않습니다."));
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ApiResponse<?>> handleResponseStatusException(ResponseStatusException e) {
		return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.createError(e.getReason()));
	}
}
