package com.joucode.campus_x_jobs.auth.domain.ports.input;

public interface AuthCheckTokenUseCase {
    Boolean checkAvailableToken(String token);
}
