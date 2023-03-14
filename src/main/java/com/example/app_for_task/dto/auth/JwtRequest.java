package com.example.app_for_task.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {
    @NotNull(message = "Заполните поле")
    private String userName;
    @NotNull(message = "Заполните поле")
    private String password;
}
