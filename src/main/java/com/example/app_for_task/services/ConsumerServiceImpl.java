package com.example.app_for_task.services;

import com.example.app_for_task.exception.NoSuchElementException;
import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.model.consumer.Role;
import com.example.app_for_task.repositories.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public Consumer getById(int id) {

        return consumerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
    }

    @Override
    @Transactional(readOnly = true)
    public Consumer getByName(String name) {
        return consumerRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
    }

    @Override
    @Transactional
    public Consumer create(Consumer consumer) {

        if (consumerRepository.findByName(consumer.getFirstName()).isPresent()) {
            throw new IllegalStateException("Пользователь с таким именем уже существует");
        }

        if (!consumer.getPassword().equals(consumer.getPasswordConf())) {
            throw new IllegalStateException("Пароли не совпадают");
        }

        consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
        consumerRepository.create(consumer);

        Set<Role> roleSet = Set.of(Role.ROLE_USER);
        consumerRepository.insertConsumerRole(consumer.getId(), Role.ROLE_USER);
        consumer.setRoleSet(roleSet);

        return consumer;
    }

    @Override
    @Transactional
    public Consumer update(Consumer consumer) {
        consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
        consumerRepository.update(consumer);

        return consumer;
    }

    @Override
    @Transactional
    public void delete(int id) {
        consumerRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean whoseTask(int consumerId, int taskId) {
        return consumerRepository.whoseTask(consumerId, taskId);
    }
}
