package com.joucode.campus_x_jobs.auth.domain.ports.input;

import com.joucode.campus_x_jobs.auth.domain.models.Auth;
import com.joucode.campus_x_jobs.user.domain.models.User;

public interface AuthRegisterUseCase {

    Auth authRegister(User user);

}
