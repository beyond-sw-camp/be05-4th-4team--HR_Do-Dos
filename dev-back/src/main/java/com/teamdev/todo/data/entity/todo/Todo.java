package com.teamdev.todo.data.entity.todo;

import com.teamdev.todo.data.entity.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_no", nullable = false)
	private Long todoNo;

	@Column(name = "todo_content", nullable = false)
	private String todoContent;

	@Column(name = "todo_complete_yn", nullable = false)
	private Boolean todoCompleteYn;

	@Column(name = "todo_delete_yn", nullable = false)
	private Boolean todoDeleteYn;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_no", nullable = false)
	private User userNo;

	@Builder
	public Todo(User user, String todoContent, Boolean todoCompleteYn, Boolean todoDeleteYn) {
		this.userNo = user;
		this.todoContent = todoContent;
		this.todoCompleteYn = todoCompleteYn;
		this.todoDeleteYn = todoDeleteYn;
	}

	public void updateTodoCompleteYn() {
		this.todoCompleteYn = !this.todoCompleteYn;
	}

	public void updateTodoDeleteYn() {
		this.todoDeleteYn = !this.todoDeleteYn;
	}
}
