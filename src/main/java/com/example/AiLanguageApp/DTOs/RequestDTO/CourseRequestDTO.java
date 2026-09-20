package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseRequestDTO {

    @NotNull(message = "Language ID is required")
    private Long language_id;

    @NotNull(message = "Level ID is required")
    private Long level_id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}