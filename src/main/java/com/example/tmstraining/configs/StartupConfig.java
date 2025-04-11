package com.example.tmstraining.configs;

import com.example.tmstraining.entities.User;
import com.example.tmstraining.enums.Role;
import com.example.tmstraining.services.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {

    private final UserService userService;

    public StartupConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return (args) -> {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(Role.ROLE_ADMIN);

            userService.addUser(admin);
        };
    }
}
