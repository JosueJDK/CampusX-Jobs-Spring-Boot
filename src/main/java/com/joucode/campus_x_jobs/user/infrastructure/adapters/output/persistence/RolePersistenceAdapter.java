package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence;

import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.domain.models.Role;
import com.joucode.campus_x_jobs.user.domain.ports.output.RoleRepositoryPort;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity.RoleEntity;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.mappers.RoleMapper;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.repository.RoleRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class RolePersistenceAdapter implements RoleRepositoryPort {

    private final RoleRepository repository;

    private final RoleMapper mapper;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        Optional<RoleEntity> role = repository.findByRoleName(roleName);
        return role.map(mapper::toModel);
    }
}
