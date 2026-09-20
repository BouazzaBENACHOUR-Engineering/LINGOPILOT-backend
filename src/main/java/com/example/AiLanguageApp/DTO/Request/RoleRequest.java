package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleRequest {

    @NotBlank(message = "name is required")
    @Size(max = 50, message = "name must not exceed 50 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}