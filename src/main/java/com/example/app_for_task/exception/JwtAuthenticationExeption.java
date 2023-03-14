package com.example.app_for_task.exception;

public class JwtAuthenticationExeption extends RuntimeException {
    public JwtAuthenticationExeption(String message) {
        super(message);
    }
}
