package com.joucode.campus_x_jobs.user.application.services;

import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.input.CreateUserUseCase;
import com.joucode.campus_x_jobs.user.domain.ports.input.RetrieveUserUseCase;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class UserService implements CreateUserUseCase, RetrieveUserUseCase {

    private final CreateUserUseCase createUser;

    private final RetrieveUserUseCase retrieveUser;

    @Override
    public User save(User user) {
        return createUser.save(user);
    }

    @Override
    public User getById(Long id) {
        return retrieveUser.getById(id);
    }

    @Override
    public User findByEmail(String email) {
        return retrieveUser.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return retrieveUser.findAll();
    }
}
