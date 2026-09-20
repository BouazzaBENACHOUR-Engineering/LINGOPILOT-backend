package com.example.AiLanguageApp.DTO.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AiConversationRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "language_id is required")
    private Long language_id;

    @Size(max = 255, message = "title must not exceed 255 characters")
    private String title;

    private Long level_id;

    @NotNull(message = "started_at is required")
    private LocalDateTime started_at;

    private LocalDateTime ended_at;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Long level_id) {
        this.level_id = level_id;
    }

    public LocalDateTime getStarted_at() {
        return started_at;
    }

    public void setStarted_at(LocalDateTime started_at) {
        this.started_at = started_at;
    }

    public LocalDateTime getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(LocalDateTime ended_at) {
        this.ended_at = ended_at;
    }
}