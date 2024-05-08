package com.teamdev.todo.data.dto.todo;

import com.teamdev.todo.data.entity.todo.Todo;
import com.teamdev.todo.data.entity.user.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoRequestDto {

	@NotBlank
	private String todoContent;

	@NotNull
	private Boolean todoCompleteYn;

	@NotNull
	private Boolean todoDeleteYn;

	@NotNull
	private Long userNo;

	public Todo toEntity(TodoRequestDto params, User user) {
		return Todo.builder()
			.user(user)
			.todoContent(params.getTodoContent())
			.todoCompleteYn(params.getTodoCompleteYn())
			.todoDeleteYn(params.getTodoDeleteYn())
			.build();
	}
}
