package com.teamdev.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamdev.todo.data.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserNo(Long userNo);

	User findByUserId(String userId);
}
