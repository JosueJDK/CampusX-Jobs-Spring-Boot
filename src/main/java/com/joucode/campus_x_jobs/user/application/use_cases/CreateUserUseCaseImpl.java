package com.joucode.campus_x_jobs.user.application.use_cases;

import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.input.CreateUserUseCase;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort repositoryPort;

    @Override
    public User save(User user) {
        return repositoryPort.save(user);
    }
}
