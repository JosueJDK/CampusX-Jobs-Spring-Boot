package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity;

import com.joucode.campus_x_jobs.user.domain.enums.RoleName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ROLES_TABLE")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20, nullable = false)
    private RoleName roleName;

}
