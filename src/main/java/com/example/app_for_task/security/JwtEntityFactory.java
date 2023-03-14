package com.example.app_for_task.security;

import com.example.app_for_task.model.consumer.Consumer;
import com.example.app_for_task.model.consumer.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(Consumer consumer) {

        return new JwtEntity(
                consumer.getId(),
                consumer.getFirstName(),
                consumer.getLastName(),
                consumer.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(consumer.getRoleSet()))
        );
    }


    public static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roleList) {

        return roleList.stream()
                .map(role -> role.name())
                .map(role1 -> new SimpleGrantedAuthority(role1))
                .collect(Collectors.toList());

    }
}
