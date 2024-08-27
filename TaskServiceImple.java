package com.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dao.TaskRepository;
import com.model.Task;
import com.model.Task.Status;
import com.model.User;

@Service
public class TaskServiceImple implements TaskService{
	
	private final TaskRepository taskRepo;
    private final UserService userService;

  
    public TaskServiceImple(TaskRepository taskRepo, UserService userService) {
        this.taskRepo = taskRepo;
        this.userService = userService;
    }

    @Override
    public Task createTask(Task task, String username) {

        User user = userService.findByUsername(username);
        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepo.save(task);
    }

    @Override
    public List<Task> getTasks(String username) {
        User user = userService.findByUsername(username);
        return taskRepo.findAll();
    }

    @Override
    public Task updateTask(Long taskId, Task updatedTask, String username) {
        Task task = findTaskByIdAndUsername(taskId, username);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long taskId, String username) {
        Task task = findTaskByIdAndUsername(taskId, username);
        taskRepo.delete(task);;
    }

    @Override
    public List<Task> filterTasks(String username, String status, String priority, LocalDate dueDate) {
        User user = userService.findByUsername(username);
        return taskRepo.findByUserAndStatusAndPriorityAndDueDate(user, Status.valueOf(status), priority, dueDate);
    }

    @Override
    public List<Task> searchTasks(String username, String query) {
        User user = userService.findByUsername(username);
        return taskRepo.findByUserAndTitleContainingOrDescriptionContaining(user, query, query);
    }

    private Task findTaskByIdAndUsername(Long taskId, String username) {
        User user = userService.findByUsername(username);
        return taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

//	@Override
//	public List<Task> getTasks(Long taskId) {
//		// TODO Auto-generated method stub
//		User user = userService.findById(taskId);
//        return taskRepo.findAll();
//	}

	@Override
	public List<Task> getAllTasks(Long taskId) {
		// TODO Auto-generated method stub
		User user = userService.findById(taskId);
        return taskRepo.findAll();
		
	}
    
}
