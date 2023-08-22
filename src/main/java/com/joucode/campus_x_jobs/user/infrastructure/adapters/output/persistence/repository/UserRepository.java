package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.repository;

import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailUser(String email);

    Boolean existsByEmailUser(String email);

}
