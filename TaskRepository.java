package com.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Task;
import com.model.Task.Status;
import com.model.User;

public interface TaskRepository extends JpaRepository<Task,Long>{

	List<Task> findByUserAndTitleContainingOrDescriptionContaining(User user, String query, String query2);

	List<Task> findByUserAndStatusAndPriorityAndDueDate(User user, Status valueOf, String priority, LocalDate dueDate);

}
