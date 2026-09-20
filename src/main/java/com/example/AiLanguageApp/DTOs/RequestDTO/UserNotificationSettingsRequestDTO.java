package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotNull;

public class UserNotificationSettingsRequestDTO {

    @NotNull(message = "User ID is required")
    private Long user_id;

    @NotNull(message = "Daily reminder setting is required")
    private Boolean daily_reminder;

    @NotNull(message = "Weekly report setting is required")
    private Boolean weekly_report;

    @NotNull(message = "Review reminder setting is required")
    private Boolean review_reminder;

    @NotNull(message = "AI notifications setting is required")
    private Boolean ai_notifications;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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