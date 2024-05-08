package com.teamdev.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamdev.todo.data.entity.todo.Todo;
import com.teamdev.todo.data.entity.todo.TodoHistory;

public interface TodoHistoryRepository extends JpaRepository<TodoHistory, Long> {
	TodoHistory findByTodoNo(Todo todo);
}
