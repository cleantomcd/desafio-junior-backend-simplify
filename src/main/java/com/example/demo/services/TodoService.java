package com.example.demo.services;

import com.example.demo.todo.RequestTask;
import com.example.demo.todo.Task;
import com.example.demo.todo.TaskRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TaskRepository taskRepository;

    public TodoService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> createTask(@RequestBody @Valid RequestTask data) {
        Task newTask = new Task(data);
        taskRepository.save(newTask);
        return getActiveTasks();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(sortTasks());
    }

    public List<Task> getActiveTasks() {
        return taskRepository.findAllByActiveTrue();
    }

    public Sort sortTasks() {
        return Sort.by("priority").descending().and(
                Sort.by("name").ascending());
    }

    @Transactional
    public List<Task> updateTask(@PathVariable String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setFinished(true);
            task.setActive(false);
        }
        else throw new IllegalArgumentException("Tarefa não encontrada.");
        return getActiveTasks();
    }

    @Transactional
    public List<Task> deleteTask(@PathVariable String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setActive(false);
        }
        else throw new IllegalArgumentException("Tarefa não encontrada.");
        return getActiveTasks();
    }

    @Transactional
    public List<Task> restoreTask(@PathVariable String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setActive(true);
        }
        else throw new IllegalArgumentException("Tarefa não encontrada.");
        return getActiveTasks();
    }

    public List<Task> getAllInactiveTasks() {
        return taskRepository.findAllByActiveFalse();
    }

    public List<Task> getAllFinishedTasks() {
        return taskRepository.findAllByFinishedTrue();
    }

    public List<Task> getAllUnfinishedTasks() {
        return taskRepository.findAllByFinishedFalse();
    }
}
