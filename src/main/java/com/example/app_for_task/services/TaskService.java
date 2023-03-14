package com.example.app_for_task.services;

import com.example.app_for_task.model.tasks.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task, int consId);
    Task update(Task task);
    void delete(int taskId);
    Task getById(int taskId);
    List<Task> findAllTaskById(int consId);
}
