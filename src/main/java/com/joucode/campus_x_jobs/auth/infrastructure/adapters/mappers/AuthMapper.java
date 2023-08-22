package com.joucode.campus_x_jobs.auth.infrastructure.adapters.mappers;

import com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.request.AuthLoginRequest;
import com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.request.AuthRegisterRequest;
import com.joucode.campus_x_jobs.user.domain.models.User;

import java.time.LocalDateTime;

public class AuthMapper {

    public User toModel(AuthLoginRequest authLoginRequest){

        return User.builder()
                .emailUser(authLoginRequest.getEmail())
                .passwordUser(authLoginRequest.getPassword())
                .build();
    }

    public User toModel(AuthRegisterRequest request){

        return User.builder()
                .emailUser(request.getEmail())
                .passwordUser(request.getPassword())
                .createdAtUser(LocalDateTime.now())
                .isActiveUser(true)
                .updatedAtUser(LocalDateTime.now())
                .build();
    }


}