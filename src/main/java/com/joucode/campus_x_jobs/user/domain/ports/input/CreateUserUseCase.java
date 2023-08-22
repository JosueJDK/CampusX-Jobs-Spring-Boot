package com.joucode.campus_x_jobs.user.domain.ports.input;

import com.joucode.campus_x_jobs.user.domain.models.User;

public interface CreateUserUseCase {

    User save(User user);

}
