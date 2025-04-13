package com.example.tmstraining.security;

import com.example.tmstraining.enums.Role;
import com.example.tmstraining.security.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("auth/**").permitAll()
                        .requestMatchers("api/items/**").hasAuthority(Role.ROLE_ADMIN.name())
                        .anyRequest().hasAuthority(Role.ROLE_CUSTOMER.name())
                )
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(ExceptionHandling.accessDeniedHandler());
                    exceptionHandling.authenticationEntryPoint(ExceptionHandling.authenticationEntryPoint());

                });

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
