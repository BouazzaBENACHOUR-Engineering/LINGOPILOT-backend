package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_conversations")
public class AiConversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language_id;

    @Column(name = "title", length = 255)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level_id;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime started_at;

    @Column(name = "ended_at")
    private LocalDateTime ended_at;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Language getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Language language_id) {
        this.language_id = language_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Level level_id) {
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