package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "daily_goal_minutes", nullable = false)
    private Integer daily_goal_minutes;

    @Column(name = "preferred_learning_time")
    private LocalTime preferred_learning_time;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    // Constructor

    public UserPreferences() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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