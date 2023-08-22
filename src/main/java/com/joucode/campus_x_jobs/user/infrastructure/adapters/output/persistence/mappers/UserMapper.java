package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.mappers;

import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public UserEntity toEntity(User user){
        return mapper.map(user, UserEntity.class);
    }

    public User toModel(UserEntity user){
        return mapper.map(user, User.class);
    }

    public List<User> toList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }


}
