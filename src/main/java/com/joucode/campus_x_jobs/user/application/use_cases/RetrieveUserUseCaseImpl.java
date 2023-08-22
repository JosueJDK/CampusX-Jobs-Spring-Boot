package com.joucode.campus_x_jobs.user.application.use_cases;

import com.joucode.campus_x_jobs.common.exceptions.NotFoundException;
import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.input.RetrieveUserUseCase;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserRepositoryPort repositoryPort;

    @Override
    public User getById(Long id) {
        return repositoryPort.findById(id).orElseThrow(
                () -> new NotFoundException("El usuario no existe.")
        );
    }

    @Override
    public User findByEmail(String email) {
        return repositoryPort.findByEmail(email).orElseThrow(
                () -> new NotFoundException("El usuario no existe.")
        );
    }

    @Override
    public List<User> findAll() {
        return repositoryPort.findAll();
    }
}
