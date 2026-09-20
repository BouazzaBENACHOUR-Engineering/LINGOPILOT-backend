package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "message", nullable = false, columnDefinition = "nvarchar(max)")
    private String message;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "is_read", nullable = false)
    private Boolean is_read;

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDateTime created_at;

    // Constructor

    public Notification() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    public java.time.LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(java.time.LocalDateTime created_at) {
        this.created_at = created_at;
    }
}