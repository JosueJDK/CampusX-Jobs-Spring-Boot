package com.joucode.campus_x_jobs.user.domain.models;

import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ID SERIAL PRIMARY KEY,
//NAME VARCHAR(50) NOT NULL,


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long idRole;

    private RoleName roleName;

}
