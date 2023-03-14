package com.example.app_for_task.controllers;

import com.example.app_for_task.dto.ConsumerDTO;
import com.example.app_for_task.dto.ConsumerDTOMapper;
import com.example.app_for_task.dto.auth.JwtRequest;
import com.example.app_for_task.dto.auth.JwtResponse;
import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.services.AuthService;
import com.example.app_for_task.services.ConsumerService;
import com.example.app_for_task.validation.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final ConsumerService consumerService;
    private final ConsumerDTOMapper consumerDTOMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginrequest) {

        return authService.login(loginrequest);
    }

    @PostMapping("/register")
    public ConsumerDTO register(@Validated(OnCreate.class) @RequestBody ConsumerDTO consumerDTO) {
        Consumer consumer = consumerService.create(consumerDTOMapper.fromDtoMap(consumerDTO));

        return consumerDTOMapper.inDtoMap(consumer);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {

        return authService.refresh(refreshToken);
    }
}
