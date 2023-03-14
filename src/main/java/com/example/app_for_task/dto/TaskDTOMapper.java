package com.example.app_for_task.dto;

import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.model.tasks.Task;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface TaskDTOMapper {
    TaskDTO inDtoMap(Task task);
    Task fromDtoMap(TaskDTO taskDTO);
    List<TaskDTO> inDtoMap(List<Task> taskList);
}
