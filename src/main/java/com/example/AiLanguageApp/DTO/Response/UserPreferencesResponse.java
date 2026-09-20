package com.example.AiLanguageApp.DTO.Response;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserPreferencesResponse {

    private Long id;
    private Long user_id;
    private Integer daily_goal_minutes;
    private LocalTime preferred_learning_time;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}