package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.ConsumerDTO;
import com.example.app_for_task.dto.ConsumerDTOMapper;
import com.example.app_for_task.dto.TaskDTO;
import com.example.app_for_task.dto.TaskDTOMapper;
import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.model.tasks.Task;
import com.example.app_for_task.services.ConsumerService;
import com.example.app_for_task.services.TaskService;
import com.example.app_for_task.validation.OnCreate;
import com.example.app_for_task.validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumer")
@Validated
public class ConsumerRestController {

    private final TaskService taskService;
    private final ConsumerService consumerService;

    private final TaskDTOMapper taskDTOMapper;
    private final ConsumerDTOMapper consumerDTOMapper;

    @Autowired
    public ConsumerRestController(TaskService taskService, ConsumerService consumerService, TaskDTOMapper taskDTOMapper, ConsumerDTOMapper consumerDTOMapper) {
        this.taskService = taskService;
        this.consumerService = consumerService;
        this.taskDTOMapper = taskDTOMapper;
        this.consumerDTOMapper = consumerDTOMapper;
    }

    public ConsumerDTO update(@Validated(OnUpdate.class) @RequestBody ConsumerDTO consumerDTO) {
        Consumer cosumer = consumerDTOMapper.fromDtoMap(consumerDTO);
        Consumer consUpdt = consumerService.update(cosumer);

        return consumerDTOMapper.inDtoMap(consUpdt);
    }

    @GetMapping("/{id}")
    public ConsumerDTO getById(@PathVariable int id) {
        Consumer con = consumerService.getById(id);

        return consumerDTOMapper.inDtoMap(con);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        consumerService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getAllTasksOfUserById(@PathVariable int id) {
        List<Task> taskList = taskService.findAllTaskById(id);

        return taskDTOMapper.inDtoMap(taskList);
    }

    @PostMapping("/{id}/tasks")
    public TaskDTO createTask(@PathVariable int id,
                              @Validated (OnCreate.class) @RequestBody TaskDTO taskDto) {
        Task task = taskDTOMapper.fromDtoMap(taskDto);
        Task createdTask = taskService.create(task, id);

        return taskDTOMapper.inDtoMap(createdTask);
    }
}
