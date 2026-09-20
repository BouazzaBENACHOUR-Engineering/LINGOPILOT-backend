package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.NotNull;

public class UserRoleRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "role_id is required")
    private Long role_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}