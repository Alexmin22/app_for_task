package com.example.app_for_task.model.tasks;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private int id;
    private String taskName;
    private String taskDesc;
    private Status status;
    private LocalDateTime deadline;
}
