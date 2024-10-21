package com.example.demo.todo;


import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "todo")
@Entity(name = "todo")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private boolean finished;
    private int priority;
    private boolean active;

    public Task(String name, String description, boolean finished, int priority) {
        this.name = name;
        this.description = description;
        this.finished = finished;
        this.priority = priority;
        this.active = true;
    }

    public Task() {
    }

    public Task(RequestTask task) {
        this.name = task.name();
        this.description = task.description();
        this.priority = task.priority();
        this.finished = false;
        this.active = true;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return this.active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return finished == task.finished && priority == task.priority && Objects.equals(name, task.name) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, finished, priority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", finished=" + finished +
                ", priority=" + priority +
                '}';
    }
}
