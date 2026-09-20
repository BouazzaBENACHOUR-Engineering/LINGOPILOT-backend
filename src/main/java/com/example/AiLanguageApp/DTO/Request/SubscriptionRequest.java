package com.example.AiLanguageApp.DTO.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SubscriptionRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "plan_id is required")
    private Long plan_id;

    @NotNull(message = "status is required")
    @Size(max = 20, message = "status must not exceed 20 characters")
    private String status;

    @NotNull(message = "started_at is required")
    private LocalDateTime startedAt;

    private LocalDateTime expiresAt;

    @NotNull(message = "autoRenew is required")
    private Boolean autoRenew;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }
}