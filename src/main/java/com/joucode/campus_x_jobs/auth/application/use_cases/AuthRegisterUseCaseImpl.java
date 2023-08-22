package com.joucode.campus_x_jobs.auth.application.use_cases;

import com.joucode.campus_x_jobs.auth.application.services.JwtService;
import com.joucode.campus_x_jobs.auth.domain.models.Auth;
import com.joucode.campus_x_jobs.auth.domain.ports.input.AuthRegisterUseCase;
import com.joucode.campus_x_jobs.common.exceptions.NotFoundException;
import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.domain.models.Role;
import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.output.RoleRepositoryPort;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Map;

@AllArgsConstructor
public class AuthRegisterUseCaseImpl implements AuthRegisterUseCase {

    private final UserRepositoryPort userRepositoryPort;

    private final RoleRepositoryPort roleRepositoryPort;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public Auth authRegister(User user) {
        Role userRole = roleRepositoryPort.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new NotFoundException("Not found role: ROLE_USER"));

        user.setRoleUser(userRole);

        user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));

        User userCreated = userRepositoryPort.save(user);

        Map<String, String> tokens = jwtService.generateTokens(userCreated.getIdUser().toString(), userCreated.getEmailUser(), userCreated.getRoleUser().getRoleName());

        return new Auth(userCreated, tokens.get("accessToken"), tokens.get("refreshToken"));

    }


}
