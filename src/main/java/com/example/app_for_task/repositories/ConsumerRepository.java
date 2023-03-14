package com.example.app_for_task.repositories;

import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.model.consumer.Role;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConsumerRepository {
    Optional<Consumer> findById(int id);
    Optional<Consumer> findByName(String name);
    public void update(Consumer consumer);
    public void create(Consumer consumer);
    public void delete(int id);
    public void insertConsumerRole(@Param("consId") int consId, @Param("role") Role role);
    boolean whoseTask(@Param("consId") int consId, @Param("taskId") int taskId);

}
