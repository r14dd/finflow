package com.finflow.wallet.controller;

import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.service.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.finflow.wallet.dto.CreateAdminUserRequest;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody CreateAdminUserRequest request) {

        UserEntity user = adminUserService.createUser(
                request.getUsername(),
                request.getPassword(),
                UserEntity.Role.ROLE_USER
        );

        return user.getId();
    }
}
