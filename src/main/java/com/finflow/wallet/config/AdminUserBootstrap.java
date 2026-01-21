package com.finflow.wallet.config;

import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.repository.UserRepository;
import com.finflow.wallet.service.AdminUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminUserBootstrap {

    @Bean
    public CommandLineRunner createAdminUser(
            AdminUserService adminUserService,
            UserRepository userRepository
    ) {
        return args -> {

            String adminUsername = "admin";

            boolean adminExists = userRepository
                    .findByUsername(adminUsername)
                    .isPresent();

            if (!adminExists) {
                adminUserService.createUser(
                        adminUsername,
                        "admin123",
                        UserEntity.Role.ROLE_ADMIN
                );
            }
        };
    }
}
