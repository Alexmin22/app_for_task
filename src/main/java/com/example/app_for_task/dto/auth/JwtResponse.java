package com.example.app_for_task.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {
    private int id;
    private String userName;
    private String accessToken;
    private String refreshToken;

}
