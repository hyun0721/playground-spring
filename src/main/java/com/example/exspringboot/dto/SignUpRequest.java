package com.example.exspringboot.dto;

import com.example.exspringboot.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    private String username;
    private String password;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .username(username)
                .password(encodedPassword)
                .build();
    }
}
