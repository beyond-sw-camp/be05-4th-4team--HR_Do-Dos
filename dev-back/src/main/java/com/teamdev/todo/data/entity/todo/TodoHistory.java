package com.teamdev.todo.data.entity.todo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TodoHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_no", nullable = false)
	private Long historyNo;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "delete_date")
	private LocalDateTime deleteDate;

	@Column(name = "complete_date")
	private LocalDateTime completeDate;

	@OneToOne
	@JoinColumn(name = "todo_no", nullable = false)
	private Todo todoNo;

	public void createTodoHistory(LocalDateTime createDate, LocalDateTime deleteDate, LocalDateTime completeDate,
		Todo todoNo) {
		this.createDate = createDate;
		this.deleteDate = deleteDate;
		this.completeDate = completeDate;
		this.todoNo = todoNo;
	}

	public void updateCompleteDate() {
		this.completeDate = LocalDateTime.now();
	}

	public void updateDeleteDate() {
		this.deleteDate = LocalDateTime.now();
	}
}
