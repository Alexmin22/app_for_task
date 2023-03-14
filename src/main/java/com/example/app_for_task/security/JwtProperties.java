package com.example.app_for_task.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtProperties {

    @Value("${jwt.access}")
    private long access;
    @Value("${jwt.refresh}")
    private long refresh;
    @Value("${jwt.secret}")
    private String secret;
}
