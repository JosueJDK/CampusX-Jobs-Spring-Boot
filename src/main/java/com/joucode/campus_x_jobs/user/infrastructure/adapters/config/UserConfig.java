package com.joucode.campus_x_jobs.user.infrastructure.adapters.config;

import com.joucode.campus_x_jobs.user.application.services.UserService;
import com.joucode.campus_x_jobs.user.application.use_cases.CreateUserUseCaseImpl;
import com.joucode.campus_x_jobs.user.application.use_cases.RetrieveUserUseCaseImpl;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.RolePersistenceAdapter;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.UserPersistenceAdapter;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.mappers.RoleMapper;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.mappers.UserMapper;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.repository.RoleRepository;
import com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }

    @Bean
    public RoleMapper roleMapper(){
        return new RoleMapper();
    }

    @Bean
    public UserPersistenceAdapter userPersistenceAdapter(UserRepository repository){
        return new UserPersistenceAdapter(repository, userMapper());
    }

    @Bean
    public RolePersistenceAdapter rolePersistenceAdapter(RoleRepository repository){
        return new RolePersistenceAdapter(repository, roleMapper());
    }

    @Bean
    public UserService userService(UserRepositoryPort repositoryPort){
        return new UserService(
          new CreateUserUseCaseImpl(repositoryPort),
          new RetrieveUserUseCaseImpl(repositoryPort)
        );
    }

}
