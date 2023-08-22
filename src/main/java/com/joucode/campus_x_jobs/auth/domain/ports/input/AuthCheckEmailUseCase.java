package com.joucode.campus_x_jobs.auth.domain.ports.input;

public interface AuthCheckEmailUseCase {
    Boolean checkAvailableEmail(String email);

}
