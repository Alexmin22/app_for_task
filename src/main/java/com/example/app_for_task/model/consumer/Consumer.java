package com.example.app_for_task.model.consumer;

import lombok.Data;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Set;

@Data
public class Consumer {
    private int id;
    private String firstName;
    private String lastName;
    private String  password;
    private String  passwordConf;
    private Set<Role> roleSet;
    private List<Task> taskList;
}
