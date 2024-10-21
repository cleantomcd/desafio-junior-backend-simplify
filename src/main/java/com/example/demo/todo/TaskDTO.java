package com.example.demo.todo;

public record TaskDTO(String name,
                      String description,
                      boolean done,
                      int priority,
                      boolean active) {
}
