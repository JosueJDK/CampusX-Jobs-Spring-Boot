package com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.response;

import com.joucode.campus_x_jobs.user.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthResponse {

    private User user;

    private String accessToken;

    private String refreshToken;

}
