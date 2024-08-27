package com.service;

import java.time.LocalDate;
import java.util.List;

import com.model.Task;

public interface TaskService {

	public Task createTask(Task task, String username);
//	public List<Task> getTasks(Long taskId);
	public Task updateTask(Long taskId, Task updatedTask, String username);
	public void deleteTask(Long taskId, String username);
	public List<Task> filterTasks(String username, String status, String priority, LocalDate dueDate);
	public List<Task> searchTasks(String username, String query);
	public List<Task> getAllTasks(Long taskId);
	public List<Task> getTasks(String status);
}
