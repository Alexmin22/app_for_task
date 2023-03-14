package com.example.app_for_task.services;

import com.example.app_for_task.dto.auth.JwtRequest;
import com.example.app_for_task.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest log);
    JwtResponse refresh(String refreshToken);

}
