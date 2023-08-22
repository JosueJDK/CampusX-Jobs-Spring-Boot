package com.joucode.campus_x_jobs.user.domain.ports.input;

import com.joucode.campus_x_jobs.user.domain.models.User;

import java.util.List;

public interface RetrieveUserUseCase {

    User getById(Long id);

    User findByEmail(String email);

    List<User> findAll();

}
