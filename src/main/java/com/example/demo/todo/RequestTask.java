package com.example.demo.todo;

public record RequestTask(
        String id,
        String name,
        String description,
        int priority
) {
}
