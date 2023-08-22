package com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.request;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRefreshTokenRequest {

    private String refreshToken;

}
