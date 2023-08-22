package com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
