package com.finflow.wallet.service;

import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.service.*;
import com.finflow.wallet.repository.*;
import jakarta.transaction.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.finflow.wallet.entity.UserEntity;


@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserEntity createUser(
            String username,
            String rawPassword,
            UserEntity.Role role
    ) {
        String hashedPassword = passwordEncoder.encode(rawPassword);

        UserEntity user = new UserEntity(
                username,
                hashedPassword,
                role
        );

        return userRepository.save(user);
    }
}

