package com.joucode.campus_x_jobs.auth.infrastructure.adapters.config;

import com.joucode.campus_x_jobs.auth.application.services.AuthService;
import com.joucode.campus_x_jobs.auth.application.services.JwtService;
import com.joucode.campus_x_jobs.auth.application.use_cases.*;
import com.joucode.campus_x_jobs.auth.infrastructure.adapters.mappers.AuthMapper;
import com.joucode.campus_x_jobs.user.domain.ports.output.RoleRepositoryPort;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {

    @Bean
    public AuthMapper authMapper(){

        return new AuthMapper();

    }

    @Bean
    public AuthService authService(

            UserRepositoryPort userRepositoryPort,
            RoleRepositoryPort roleRepositoryPort,
            JwtService jwtService,
            PasswordEncoder encryptService

    ){
        return new AuthService(

                new AuthLoginUseCaseImpl(
                        userRepositoryPort,
                        encryptService,
                        jwtService
                ),

                new AuthRegisterUseCaseImpl(
                        userRepositoryPort,
                        roleRepositoryPort,
                        encryptService,
                        jwtService
                ),

                new AuthCheckEmailUseCaseImpl(
                        userRepositoryPort
                ),

                new AuthCheckTokenUseCaseImpl(
                        jwtService
                )
                ,

                new AuthRefreshTokenUseCaseImpl(
                        userRepositoryPort,
                        jwtService
                ),

                new AuthLogoutUserIdUseCaseImpl(
                        userRepositoryPort
                )

        );
    }

}
