package com.joucode.campus_x_jobs.user.domain.ports.output;

import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.domain.models.Role;
import java.util.Optional;

public interface RoleRepositoryPort {

    Optional<Role> findByName(RoleName roleName);

}
