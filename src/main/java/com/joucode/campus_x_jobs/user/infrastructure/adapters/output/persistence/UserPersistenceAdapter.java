package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence;

import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.mappers.UserMapper;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Override
    public User save(User user) {
        return mapper.toModel(repository.save(mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        return user.map(mapper::toModel);
    }

    @Override
    public List<User> findAll() {
        return mapper.toList(repository.findAll());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> user = repository.findByEmailUser(email);
        return user.map(mapper::toModel);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmailUser(email);
    }
}
