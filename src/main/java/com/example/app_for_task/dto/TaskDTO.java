package com.example.app_for_task.dto;

import com.example.app_for_task.model.tasks.Status;
import com.example.app_for_task.validation.OnCreate;
import com.example.app_for_task.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    @NotNull(message = "Id не может быть пустым", groups = OnUpdate.class)
    private int id;
    @NotNull(message = "Название задания не может быть пустым", groups = {OnUpdate.class, OnCreate.class})
    private String taskName;
    private String taskDesc;
    private Status status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-dd-MM HH:mm:ss")
    private LocalDateTime deadline;
}
