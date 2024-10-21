package com.example.demo.controllers;


import com.example.demo.services.TodoService;
import com.example.demo.todo.RequestTask;
import com.example.demo.todo.Task;
import com.example.demo.todo.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TodoService todoService;

    public TaskController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/actives")
    public ResponseEntity<List<Task>> getAllActiveTasks() {
        return ResponseEntity.ok(todoService.getActiveTasks());
    }

    @GetMapping("/inactives")
    public ResponseEntity<List<Task>> getAllInactiveTasks() {
        return ResponseEntity.ok(todoService.getAllInactiveTasks());
    }

    @GetMapping("/finished")
    public ResponseEntity<List<Task>> getAllFinishedTasks() {
        return ResponseEntity.ok(todoService.getAllFinishedTasks());
    }

    @GetMapping("/unfinished")
    public ResponseEntity<List<Task>> getAllUnfinishedTasks() {
        return ResponseEntity.ok(todoService.getAllUnfinishedTasks());
    }

    @PostMapping("/create")
    public ResponseEntity<List<Task>> createTask(@RequestBody @Valid RequestTask data) {
        return ResponseEntity.ok(todoService.createTask(data));
    }

    @PutMapping("/update/finish/{id}")
    public ResponseEntity<List<Task>> updateTask(@PathVariable String id) {
        return ResponseEntity.ok(todoService.updateTask(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<Task>> deleteTask(@PathVariable String id) {
        return ResponseEntity.ok(todoService.deleteTask(id));
    }

    @PutMapping("{id}/restore")
    public ResponseEntity<List<Task>> restoreTask(@PathVariable String id) {
        return ResponseEntity.ok(todoService.restoreTask(id));
    }
}
