package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_languages",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_user_languages",
            columnNames = {"user_id", "language_id"}
        )
    }
)
public class UserLanguage {

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level_id;

    @Column(name = "is_primary", nullable = false)
    private Boolean is_primary;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime started_at;


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

    public Level getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Level level_id) {
        this.level_id = level_id;
    }

    public Boolean getIs_primary() {
        return is_primary;
    }

    public void setIs_primary(Boolean is_primary) {
        this.is_primary = is_primary;
    }

    public LocalDateTime getStarted_at() {
        return started_at;
    }

    public void setStarted_at(LocalDateTime started_at) {
        this.started_at = started_at;
    }
}