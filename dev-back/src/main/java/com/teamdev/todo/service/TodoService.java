package com.teamdev.todo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.teamdev.todo.data.dto.todo.TodoRequestDto;
import com.teamdev.todo.data.dto.todo.TodoResponseDto;
import com.teamdev.todo.data.entity.todo.Todo;
import com.teamdev.todo.data.entity.todo.TodoHistory;
import com.teamdev.todo.data.entity.user.User;
import com.teamdev.todo.repository.TodoHistoryRepository;
import com.teamdev.todo.repository.TodoRepository;
import com.teamdev.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

	private final TodoRepository todoRepository;

	private final UserRepository userRepository;

	private final TodoHistoryRepository todoHistoryRepository;

	@Transactional
	public TodoResponseDto saveTodo(TodoRequestDto data) {
		User user = userRepository.findByUserNo(data.getUserNo());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 가진 유저를 찾지 못했습니다 : " + data.getUserNo());
		}

		Todo todo = data.toEntity(data, user);
		todoRepository.save(todo);

		// todoHistory 로그 데이터 생성
		TodoHistory todoHistory = new TodoHistory();
		todoHistory.createTodoHistory(LocalDateTime.now(), null, null, todo);
		todoHistoryRepository.save(todoHistory);

		// 예외 처리
		Todo response = todoRepository.findByTodoNo(todo.getTodoNo());
		if (response == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "항목을 생성하는데 실패했습니다.");
		}

		TodoHistory responseHistory = todoHistoryRepository.findByTodoNo(todo);
		if (responseHistory == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "로그 항목을 생성하는데 실패했습니다.");
		}

		TodoResponseDto responseDto = new TodoResponseDto(response);
		responseDto.setUserInfo(user);
		return responseDto;
	}

	public List<TodoResponseDto> findByUserNo(Long userNo) {
		User user = userRepository.findByUserNo(userNo);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 가진 유저를 찾지 못했습니다 : " + userNo);
		}

		List<Todo> todoList = todoRepository.findAllByUserNo(user).stream()
			.filter(todo -> !todo.getTodoDeleteYn())
			.collect(Collectors.toList());
		// if (todoList.isEmpty()) {
		// 	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "리스트를 반환하는데 실패했습니다");
		// }

		List<TodoResponseDto> responseList = TodoResponseDto.toDtoList(todoList);
		responseList.forEach(todo -> todo.setUserInfo(user));
		return responseList;
	}

	public void completeTodo(Long todoNo) {
		Todo todo = todoRepository.findByTodoNo(todoNo);
		TodoHistory todoHistory = todoHistoryRepository.findByTodoNo(todo);

		if (todo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 가진 할일을 찾지 못했습니다 : " + todoNo);
		}

		todo.updateTodoCompleteYn();
		todoRepository.save(todo);

		todoHistory.updateCompleteDate();
		todoHistoryRepository.save(todoHistory);

		// TODO: 반환 처리에 관한 논의(프론트) - 리스트를 반환해줘야 하나
	}

	public void deleteTodoByTodoNo(Long todoNo) {
		Todo todo = todoRepository.findByTodoNo(todoNo);
		TodoHistory todoHistory = todoHistoryRepository.findByTodoNo(todo);
		if (todo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 가진 할일을 찾지 못했습니다 : " + todoNo);
		}

		todo.updateTodoDeleteYn();
		todoRepository.save(todo);

		todoHistory.updateDeleteDate();
		todoHistoryRepository.save(todoHistory);

		// TODO: 반환 처리에 관한 논의(프론트) - 리스트를 반환해줘야 하나
	}
}
