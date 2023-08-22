package com.joucode.campus_x_jobs.auth.application.use_cases;


import com.joucode.campus_x_jobs.auth.application.services.JwtService;
import com.joucode.campus_x_jobs.auth.domain.models.Auth;
import com.joucode.campus_x_jobs.auth.domain.ports.input.AuthRefreshTokenUseCase;
import com.joucode.campus_x_jobs.common.exceptions.CustomAuthenticationException;
import com.joucode.campus_x_jobs.common.exceptions.GlobalException;
import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import com.joucode.campus_x_jobs.user.domain.models.User;
import com.joucode.campus_x_jobs.user.domain.ports.output.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AuthRefreshTokenUseCaseImpl implements AuthRefreshTokenUseCase {

    private final UserRepositoryPort userRepositoryPort;

    private final JwtService jwtService;

    @Override
    public Auth refreshToken(String refreshToken) {
        try {
            if (jwtService.validateToken(refreshToken)) {
                if (jwtService.extractTypeToken(refreshToken).equals("refresh_token") && !jwtService.isTokenExpired(refreshToken)) {
                    String userEmail = jwtService.extractEmail(refreshToken);

                    User user = userRepositoryPort.findByEmail(userEmail)
                            .orElseThrow(() -> new UsernameNotFoundException("Not Found user with this username: " + userEmail));

                    RoleName rolesNames = user.getRoleUser().getRoleName();

                    Map<String, String> tokens = jwtService.generateTokens(user.getIdUser().toString(), userEmail, rolesNames);

                    return new Auth(user, tokens.get("accessToken"), tokens.get("refreshToken"));

                }else{
                    throw new CustomAuthenticationException("Token no valido");
                }
            }
            return null;
        }catch (CustomAuthenticationException e){
            throw new CustomAuthenticationException(e.getMessage());
        }

    }
}
