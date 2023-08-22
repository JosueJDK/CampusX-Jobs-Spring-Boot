package com.joucode.campus_x_jobs.auth.application.use_cases;

import com.joucode.campus_x_jobs.auth.application.services.JwtService;
import com.joucode.campus_x_jobs.auth.domain.models.Auth;
import com.joucode.campus_x_jobs.auth.domain.ports.input.AuthLoginUseCase;
import com.joucode.campus_x_jobs.common.exceptions.NotAuthorizationInvalidException;
import com.joucode.campus_x_jobs.common.exceptions.NotFoundException;
import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AuthLoginUseCaseImpl implements AuthLoginUseCase {

    private final UserRepositoryPort userRepositoryPort;

    private final PasswordEncoder encryptService;

    private final JwtService jwtService;

    @Override
    public Auth authLogin(User user) {

        User userGet = userRepositoryPort.findByEmail(user.getEmailUser())
                .orElseThrow(() -> new NotFoundException("El usuario no existe."));

        if (!encryptService.matches(user.getPasswordUser(), userGet.getPasswordUser())) {
            throw new NotAuthorizationInvalidException("Credenciales incorrectas");
        }

        if (!userGet.getIsActiveUser()) {
            throw new NotAuthorizationInvalidException("Account is not active.");
        }

        userRepositoryPort.save(userGet);

        RoleName rolesNames = userGet.getRoleUser().getRoleName();

        Map<String, String> tokens = jwtService.generateTokens(userGet.getIdUser().toString(), user.getEmailUser(), rolesNames);


        return new Auth(userGet, tokens.get("accessToken"), tokens.get("refreshToken"));
    }







}