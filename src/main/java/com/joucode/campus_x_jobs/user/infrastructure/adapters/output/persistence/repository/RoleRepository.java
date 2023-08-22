package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.repository;

import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(RoleName roleName);

    Boolean existsByRoleName(RoleName roleName);
}
