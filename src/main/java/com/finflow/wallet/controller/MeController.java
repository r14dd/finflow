package com.finflow.wallet.controller;

import com.finflow.wallet.dto.MeResponse;
import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    private final UserRepository userRepository;

    public MeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/me")
    public MeResponse me(Authentication authentication) {

        String username = authentication.getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        return new MeResponse(
                user.getUsername(),
                user.getRole().name()
        );
    }
}
