package com.teamdev.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamdev.todo.data.entity.todo.Todo;
import com.teamdev.todo.data.entity.user.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	Todo findByTodoNo(Long todoNo);

	List<Todo> findAllByUserNo(User user);
}
