package com.joucode.campus_x_jobs.auth.infrastructure.adapters.input.rest.data.request;

import com.joucode.campus_x_jobs.common.annotations.UniqueEmail;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterRequest {

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Size(min = 8, max = 25)
    private String password;

}
