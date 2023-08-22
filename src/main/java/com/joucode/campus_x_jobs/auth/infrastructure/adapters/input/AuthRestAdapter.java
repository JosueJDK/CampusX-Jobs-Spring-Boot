package com.joucode.campus_x_jobs.auth.infrastructure.adapters.input;

import com.joucode.campus_x_jobs.auth.application.services.AuthService;
import com.joucode.campus_x_jobs.auth.domain.models.Auth;
import com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.request.*;
import com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.response.AuthResponse;
import com.joucode.campus_x_jobs.auth.infrastructure.adapters.mappers.AuthMapper;
import com.joucode.campus_x_jobs.common.mappers.ResponseMapper;
import com.joucode.campus_x_jobs.common.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestAdapter {

    private final AuthService authService;

    private final AuthMapper authMapper;

    private final ResponseMapper responseMapper;

    @PostMapping("/login")
    private ResponseEntity<Response<AuthResponse>> authLogin(@RequestBody @Valid AuthLoginRequest authLoginRequest) {
        Auth authLogin = authService.authLogin(authMapper.toModel(authLoginRequest));
        log.info("---- Authenticate user: {}", authLogin.getUser().getEmailUser());
        return new ResponseEntity<>(responseMapper.toResponse(authLogin, OK), OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Response<Auth>> authRegister(@RequestBody @Valid AuthRegisterRequest authRegisterRequest){
        Auth authResponse = authService.authRegister(authMapper.toModel(authRegisterRequest));
        log.info("---- Create user: {}", authResponse.getUser().getEmailUser());
        return new ResponseEntity(responseMapper.toResponse(authResponse, CREATED), CREATED);
    }

    @GetMapping("/logout/{user_id}")
    private ResponseEntity<?> authLogout(@PathVariable(value = "user_id") Long user_id) {
        authService.authLogoutUserId(user_id);
        log.info("---- Loguot user with id: {}", user_id);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Response<Auth>> authRefreshToken(@RequestBody AuthRefreshTokenRequest request){
        Auth authResponse = authService.refreshToken(request.getRefreshToken());
        log.info("---- Refresh Token user: {}", authResponse.getUser().getEmailUser());
        return new ResponseEntity<>(responseMapper.toResponse(authResponse, CREATED), CREATED);
    }

    @PostMapping("/check-available-token")
    public ResponseEntity<Response> checkAvailableEmail(@RequestBody AuthCheckTokenRequest tokenRequest) {
        Boolean state = authService.checkAvailableToken(tokenRequest.getToken());
        Response response = new Response(OK.value(), OK.name(), Map.of("isValid", state));
        return new ResponseEntity<>(response, OK);
    }

    @PostMapping("/check-available-email")
    public ResponseEntity<Response> checkAvailableEmail(@RequestBody AuthCheckEmailRequest emailRequest) {
        Boolean state = authService.checkAvailableEmail(emailRequest.getEmail());

        Map<String, Object> data = Map.of("email", emailRequest.getEmail(), "state", state);

        Response response = new Response(OK.value(), OK.name(), data);

        return ResponseEntity.ok(response);
    }


}
