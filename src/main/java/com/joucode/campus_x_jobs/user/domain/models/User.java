package com.joucode.campus_x_jobs.user.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

//ID SERIAL PRIMARY KEY,
//ROLE_ID INT NOT NULL REFERENCES USER_ROLES(ID),
//EMAIL VARCHAR(100) NOT NULL,
//PASSWORD VARCHAR(100) NOT NULL,
//IS_ACTIVE BOOLEAN DEFAULT TRUE,
//CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long idUser;

    private Role roleUser;

    private String emailUser;

    private String passwordUser;

    private Boolean isActiveUser = true;

    private LocalDateTime createdAtUser;

    private LocalDateTime updatedAtUser;

}
