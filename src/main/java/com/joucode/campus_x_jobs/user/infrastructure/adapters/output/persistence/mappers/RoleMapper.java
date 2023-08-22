package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.mappers;

import com.joucode.campus_x_jobs.user.domain.models.Role;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleMapper {

    @Autowired
    private ModelMapper mapper;

    public Role toModel(RoleEntity role){
        return mapper.map(role, Role.class);
    }

}
