package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_notification_settings")
public class UserNotificationSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "daily_reminder", nullable = false)
    private Boolean daily_reminder;

    @Column(name = "weekly_report", nullable = false)
    private Boolean weekly_report;

    @Column(name = "review_reminder", nullable = false)
    private Boolean review_reminder;

    @Column(name = "ai_notifications", nullable = false)
    private Boolean ai_notifications;

    // Constructor

    public UserNotificationSettings() {
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

    public Boolean getDaily_reminder() {
        return daily_reminder;
    }

    public void setDaily_reminder(Boolean daily_reminder) {
        this.daily_reminder = daily_reminder;
    }

    public Boolean getWeekly_report() {
        return weekly_report;
    }

    public void setWeekly_report(Boolean weekly_report) {
        this.weekly_report = weekly_report;
    }

    public Boolean getReview_reminder() {
        return review_reminder;
    }

    public void setReview_reminder(Boolean review_reminder) {
        this.review_reminder = review_reminder;
    }

    public Boolean getAi_notifications() {
        return ai_notifications;
    }

    public void setAi_notifications(Boolean ai_notifications) {
        this.ai_notifications = ai_notifications;
    }
}