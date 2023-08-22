package com.joucode.campus_x_jobs.user.domain.ports.output;

import com.joucode.campus_x_jobs.user.domain.models.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
