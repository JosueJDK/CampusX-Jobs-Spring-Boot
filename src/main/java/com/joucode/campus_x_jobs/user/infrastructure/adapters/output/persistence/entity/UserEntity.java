package com.joucode.campus_x_jobs.user.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "USER_TABLE")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", nullable = false)
    private RoleEntity roleUser;

    @Column(name = "email_user", nullable = false)
    private String emailUser;

    @Column(name = "password_user", nullable = false)
    private String passwordUser;

    @Column(name = "is_active_user", nullable = false)
    private Boolean isActiveUser;

    @Column(name = "update_at_user")
    private LocalDateTime updatedAtUser;

    @Column(name = "create_at_user", updatable = false)
    private LocalDateTime createdAtUser;

}
