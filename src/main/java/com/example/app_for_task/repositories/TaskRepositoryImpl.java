package com.example.app_for_task.repositories;

import com.example.app_for_task.model.tasks.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    @Override
    public Optional<Task> findById(int taskId) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAllTaskById(int consId) {
        return null;
    }

    @Override
    public void connectConsWithTask(int taskId, int consId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void delete(int taskId) {

    }
}
