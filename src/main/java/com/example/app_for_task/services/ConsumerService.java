package com.example.app_for_task.services;

import com.example.app_for_task.model.consumer.Consumer;

public interface ConsumerService {
    Consumer getById(int id);
    Consumer getByName(String name);

    Consumer create(Consumer consumer);
    Consumer update(Consumer consumer);
    void delete(int id);
    boolean whoseTask(int consumerId, int taskId);

}
