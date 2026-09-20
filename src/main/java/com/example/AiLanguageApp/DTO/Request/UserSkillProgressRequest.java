package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.NotNull;

public class UserSkillProgressRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "language_id is required")
    private Long language_id;

    @NotNull(message = "skill_id is required")
    private Long skill_id;

    @NotNull(message = "level_id is required")
    private Long level_id;

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

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public Long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Long level_id) {
        this.level_id = level_id;
    }
}