package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class UserPreferencesRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "daily_goal_minutes is required")
    @Min(value = 1, message = "daily_goal_minutes must be at least 1")
    private Integer daily_goal_minutes;

    private LocalTime preferred_learning_time;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getDaily_goal_minutes() {
        return daily_goal_minutes;
    }

    public void setDaily_goal_minutes(Integer daily_goal_minutes) {
        this.daily_goal_minutes = daily_goal_minutes;
    }

    public LocalTime getPreferred_learning_time() {
        return preferred_learning_time;
    }

    public void setPreferred_learning_time(LocalTime preferred_learning_time) {
        this.preferred_learning_time = preferred_learning_time;
    }
}