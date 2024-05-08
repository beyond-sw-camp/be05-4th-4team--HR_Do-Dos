package com.teamdev.todo.data.dto.todo;

import java.util.List;

import com.teamdev.todo.data.entity.todo.Todo;
import com.teamdev.todo.data.entity.user.User;

import lombok.Getter;

@Getter
public class TodoResponseDto {
	private Long todoNo;

	private String todoContent;

	private Boolean todoCompleteYn;

	private Boolean todoDeleteYn;

	private Long userNo;

	private String userId;

	private String userName;

	public TodoResponseDto(Todo params) {
		this.todoNo = params.getTodoNo();
		this.todoContent = params.getTodoContent();
		this.todoCompleteYn = params.getTodoCompleteYn();
		this.todoDeleteYn = params.getTodoDeleteYn();
	}

	public static List<TodoResponseDto> toDtoList(List<Todo> todoList) {
		return todoList.stream().map(TodoResponseDto::new).toList();
	}

	public void setUserInfo(User params) {
		this.userNo = params.getUserNo();
		this.userId = params.getUserId();
		this.userName = params.getUserName();
	}
}
