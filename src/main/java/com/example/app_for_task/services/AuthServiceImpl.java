package com.example.app_for_task.services;

import com.example.app_for_task.dto.auth.JwtRequest;
import com.example.app_for_task.dto.auth.JwtResponse;
import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final ConsumerService consumerService;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public JwtResponse login(JwtRequest log) {
        JwtResponse response = new JwtResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(log.getUserName(), log.getPassword()));

        Consumer consumer = consumerService.getByName(log.getUserName());
        response.setId(consumer.getId());
        response.setUserName(consumer.getFirstName());
        response.setAccessToken(jwtTokenProvider.createAccessToken(
                consumer.getId(), consumer.getFirstName(), consumer.getRoleSet()
        ));
        response.setRefreshToken(jwtTokenProvider.refreshToken(consumer.getId(), consumer.getFirstName()));

        return response;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {

        return jwtTokenProvider.refreshConsumerToken(refreshToken);
    }
}
