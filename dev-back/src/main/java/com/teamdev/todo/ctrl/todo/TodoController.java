package com.teamdev.todo.ctrl.todo;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.teamdev.todo.api.ApiResponse;
import com.teamdev.todo.data.dto.todo.TodoRequestDto;
import com.teamdev.todo.data.dto.todo.TodoResponseDto;
import com.teamdev.todo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@PostMapping("/todo/create")
	public ApiResponse<TodoResponseDto> createTodo(@Valid @RequestBody TodoRequestDto params) {
		TodoResponseDto dto = todoService.saveTodo(params);

		return ApiResponse.createSuccess(dto);
	}

	@GetMapping("/todo/list/{userNo}")
	public ApiResponse<List<TodoResponseDto>> readTodoList(@PathVariable Long userNo) {
		List<TodoResponseDto> response = todoService.findByUserNo(userNo);

		return ApiResponse.createSuccess(response);
	}

	@PostMapping("/todo/complete/{todoNo}")
	public ApiResponse<?> completeTodo(@PathVariable Long todoNo) {
		todoService.completeTodo(todoNo);

		return ApiResponse.createSuccessWithNoContent();
	}

	@PostMapping("/todo/delete/{todoNo}")
	public ApiResponse<?> deleteTodo(@PathVariable Long todoNo) {
		todoService.deleteTodoByTodoNo(todoNo);

		return ApiResponse.createSuccessWithNoContent();
	}

}
