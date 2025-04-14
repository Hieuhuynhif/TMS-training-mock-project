package com.example.tmstraining.runner;

import com.example.tmstraining.entities.User;
import com.example.tmstraining.enums.Role;
import com.example.tmstraining.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupRunner {

    private final UserService userService;

    public StartupRunner(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return (args) -> {

            try {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin");
                admin.setRole(Role.ROLE_ADMIN);

                userService.addUser(admin);
            } catch (Exception e) {
                System.out.println(e.getMessage() + ": Can not create admin");
            }
        };
    }
}
