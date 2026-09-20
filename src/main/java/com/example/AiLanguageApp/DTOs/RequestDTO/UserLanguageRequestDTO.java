package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotNull;

public class UserLanguageRequestDTO {

    @NotNull(message = "User ID is required")
    private Long user_id;

    @NotNull(message = "Language ID is required")
    private Long language_id;

    @NotNull(message = "Level ID is required")
    private Long level_id;

    @NotNull(message = "Primary language status is required")
    private Boolean is_primary;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Long language_id) {
        this.language_id = language_id;
    }

    public Long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Long level_id) {
        this.level_id = level_id;
    }

    public Boolean getIs_primary() {
        return is_primary;
    }

    public void setIs_primary(Boolean is_primary) {
        this.is_primary = is_primary;
    }
}