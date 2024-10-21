package com.example.demo.controllers;


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
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/actives")
    public ResponseEntity<List<Task>> getAllActiveTasks() {
        List<Task> allActiveTasks = taskRepository.findAllByActiveTrue();
        return ResponseEntity.ok(allActiveTasks);
    }

    @GetMapping("/inactives")
    public ResponseEntity<List<Task>> getAllInactiveTasks() {
        List<Task> allInactiveTasks = taskRepository.findAllByActiveFalse();
        return ResponseEntity.ok(allInactiveTasks);
    }

    @GetMapping("/finished")
    public ResponseEntity<List<Task>> getAllFinishedTasks() {
        List<Task> allFinishedTasks = taskRepository.findAllByFinishedTrue();
        return ResponseEntity.ok(allFinishedTasks);
    }

    @GetMapping("/unfinished")
    public ResponseEntity<List<Task>> getAllUnfinishedTasks() {
        List<Task> allUnfinishedTasks = taskRepository.findAllByFinishedFalse();
        return ResponseEntity.ok(allUnfinishedTasks);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody @Valid RequestTask data) {
        Task newTask = new Task(data);
        this.taskRepository.save(newTask);
        return ResponseEntity.ok("Tarefa criada com sucesso.");
    }

    @PutMapping("/update/finish/{id}")
    @Transactional
    public ResponseEntity<String> updateTask(@PathVariable String id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setFinished(true);
            task.setActive(false);
            return ResponseEntity.ok("Tarefa finalizada com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setActive(false);
            return ResponseEntity.ok("Tarefa excluída.");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}/restore")
    @Transactional
    public ResponseEntity<String> restoreTask(@PathVariable String id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setActive(true);
            return ResponseEntity.ok("Tarefa restaurada.");
        }
        return ResponseEntity.notFound().build();
    }
}
