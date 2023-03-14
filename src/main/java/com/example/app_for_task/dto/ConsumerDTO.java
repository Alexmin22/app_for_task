package com.example.app_for_task.dto;

import com.example.app_for_task.model.consumer.Role;
import com.example.app_for_task.validation.OnCreate;
import com.example.app_for_task.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Set;

@Data
public class ConsumerDTO {
    @NotNull(message = "Id не может быть пустым", groups = OnUpdate.class)
    private int id;
    @NotNull(message = "Имя не может быть пустым", groups = {OnUpdate.class, OnCreate.class})
    private String firstName;
    @NotNull(message = "Фамилия не может быть пустой", groups = {OnUpdate.class, OnCreate.class})
    private String lastName;
    @NotNull(message = "Пароль не может быть пустым", groups = {OnUpdate.class, OnCreate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String  password;
    @NotNull(message = "Повтор пароля не может быть пустым", groups =  OnCreate.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String  passwordConf;

}
