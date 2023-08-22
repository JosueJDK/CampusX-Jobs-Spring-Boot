package com.joucode.campus_x_jobs.common.config;

import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity.RoleEntity;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;


    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
        if (!roleRepository.existsByRoleName(RoleName.ROLE_USER) && !roleRepository.existsByRoleName(RoleName.ROLE_ADMIN)) {
            roleRepository.save(new RoleEntity(1L, RoleName.ROLE_USER));
            roleRepository.save(new RoleEntity(2L, RoleName.ROLE_ADMIN));
        }
    }
}