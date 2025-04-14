package com.example.tmstraining.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.tmstraining.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final int id;
    private final String username;
    private final String password;
    private final Role role;

    public CustomUserDetails(DecodedJWT decodedJWT) {
        this.id = decodedJWT.getClaim("id").asInt();
        this.username = decodedJWT.getClaim("username").asString();
        this.password = decodedJWT.getClaim("password").asString();
        this.role = decodedJWT.getClaim("role").as(Role.class);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}
