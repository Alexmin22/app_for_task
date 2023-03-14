package com.example.app_for_task.security;

import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final ConsumerService consumerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Consumer consumer = consumerService.getByName(username);
        return JwtEntityFactory.create(consumer);
    }
}
