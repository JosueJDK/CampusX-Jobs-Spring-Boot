package com.joucode.campus_x_jobs.auth.application.services;

import com.joucode.campus_x_jobs.user.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long idUser;
    private String usernameUser;
    private String emailUser;
    private String passwordUser;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long idUSer, String emailUser, String passwordUser,
                           Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUSer;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoleUser().getRoleName().toString()));

        return new UserDetailsImpl(
                user.getIdUser(),
                user.getEmailUser(),
                user.getPasswordUser(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordUser;
    }

    @Override
    public String getUsername() {
        return usernameUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
