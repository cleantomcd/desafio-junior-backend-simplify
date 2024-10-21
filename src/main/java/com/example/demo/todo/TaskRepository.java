package com.example.demo.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAllByActiveTrue();
    List<Task> findAllByActiveFalse();
    List<Task> findAllByFinishedTrue();
    List<Task> findAllByFinishedFalse();
}
