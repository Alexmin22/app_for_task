package com.example.app_for_task.repositories;

import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.model.consumer.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class ConsumerRepositoryImpl implements ConsumerRepository {
    @Override
    public Optional<Consumer> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Consumer> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void update(Consumer consumer) {

    }

    @Override
    public void create(Consumer consumer) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void insertConsumerRole(int consId, Role role) {

    }

    @Override
    public boolean whoseTask(int consumerId, int taskId) {
        return false;
    }
}
