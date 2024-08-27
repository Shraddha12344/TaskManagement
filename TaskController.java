package com.controller;

import com.model.Task;
import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task, null);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value = "dueDate", required = false) String dueDate,
            @RequestParam(value = "search", required = false) String search) {
        
        List<Task> tasks = taskService.getTasks(status);
        return ResponseEntity.ok(tasks);
    }

//    @GetMapping("/{taskId}")
//    public ResponseEntity<List<Task>> getTaskById(@PathVariable Long taskId) {
//        List<Task> task = taskService.getTasks(taskId);
//        return ResponseEntity.ok(task);
//    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task, null);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId, null);
        return ResponseEntity.noContent().build();
    }
}
