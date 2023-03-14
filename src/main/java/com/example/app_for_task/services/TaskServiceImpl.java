package com.example.app_for_task.services;

import com.example.app_for_task.exception.NoSuchElementException;
import com.example.app_for_task.model.tasks.Status;
import com.example.app_for_task.model.tasks.Task;
import com.example.app_for_task.repositories.ConsumerRepository;
import com.example.app_for_task.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Task create(Task task, int consId) {
        task.setStatus(Status.SCHEDULED_TASK);
        taskRepository.create(task);
        taskRepository.connectConsWithTask(task.getId(), consId);
        return task;
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.SCHEDULED_TASK);
        }
        taskRepository.update(task);
        return task;
    }

    @Override
    @Transactional
    public void delete(int taskId) {
        taskRepository.delete(taskId);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getById(int taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Задача не обнаружена"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAllTaskById(int consId) {

        return taskRepository.findAllTaskById(consId);
    }
}
