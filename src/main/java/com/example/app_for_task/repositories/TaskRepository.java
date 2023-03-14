package com.example.app_for_task.repositories;

import com.example.app_for_task.model.tasks.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(int taskId);
    List<Task> findAllTaskById(int consId);
    void connectConsWithTask(int taskId, int consId);
    void create(Task task);
    void update(Task task);
    void delete(int taskId);
}
