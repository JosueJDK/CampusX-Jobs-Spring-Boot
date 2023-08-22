package com.joucode.campus_x_jobs.auth.application.use_cases;

import com.joucode.campus_x_jobs.auth.application.services.JwtService;
import com.joucode.campus_x_jobs.auth.domain.ports.input.AuthCheckTokenUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthCheckTokenUseCaseImpl implements AuthCheckTokenUseCase {

    private final JwtService jwtService;

    @Override
    public Boolean checkAvailableToken(String token) {
        return jwtService.validateToken(token);
    }

}
