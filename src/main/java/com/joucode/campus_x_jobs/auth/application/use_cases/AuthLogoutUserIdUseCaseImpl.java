package com.joucode.campus_x_jobs.auth.application.use_cases;

import com.joucode.campus_x_jobs.auth.domain.ports.input.AuthLogoutUserIdUseCase;
import com.joucode.campus_x_jobs.common.exceptions.NotFoundException;
import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthLogoutUserIdUseCaseImpl implements AuthLogoutUserIdUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void authLogoutUserId(Long user_id) {

        User user = userRepositoryPort.findById(user_id)
                .orElseThrow(() -> new NotFoundException("El usuario no existe."));
        userRepositoryPort.save(user);

    }
}
