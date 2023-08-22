package com.joucode.campus_x_jobs.auth.domain.models;

import com.joucode.campus_x_jobs.user.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auth {

    private User user;

    private String accessToken;

    private String refreshToken;

}
