package com.joucode.campus_x_jobs.auth.application.services;

import com.joucode.campus_x_jobs.auth.domain.models.Auth;
import com.joucode.campus_x_jobs.auth.domain.ports.input.*;
import com.joucode.campus_x_jobs.user.domain.models.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthService
        implements

        AuthLoginUseCase,
        AuthRegisterUseCase,
        AuthCheckEmailUseCase,
        AuthCheckTokenUseCase,
        AuthRefreshTokenUseCase,
        AuthLogoutUserIdUseCase {

    private final AuthLoginUseCase authLoginUseCase;

    private final AuthRegisterUseCase authRegisterUseCase;

    private final AuthCheckEmailUseCase authCheckEmailUseCase;

    private final AuthCheckTokenUseCase authCheckTokenUseCase;

    private final AuthRefreshTokenUseCase authRefreshTokenUseCase;

    private final AuthLogoutUserIdUseCase authLogoutUserIdUseCase;

    @Override
    public Auth authLogin(User user) {
        return authLoginUseCase.authLogin(user);
    }

    @Override
    public Auth authRegister(User user) {
        return authRegisterUseCase.authRegister(user);
    }

    @Override
    public Boolean checkAvailableEmail(String email) {
        return authCheckEmailUseCase.checkAvailableEmail(email);
    }


    @Override
    public Boolean checkAvailableToken(String token) {
        return authCheckTokenUseCase.checkAvailableToken(token);
    }

    @Override
    public Auth refreshToken(String refreshToken) {
        return authRefreshTokenUseCase.refreshToken(refreshToken);
    }

    @Override
    public void authLogoutUserId(Long user_id){
        authLogoutUserIdUseCase.authLogoutUserId(user_id);
    }


}
