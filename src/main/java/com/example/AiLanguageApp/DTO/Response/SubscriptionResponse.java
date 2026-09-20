package com.example.AiLanguageApp.DTO.Response;

import java.time.LocalDateTime;

public class SubscriptionResponse {

    private Long id;
    private Long user_id;
    private Long plan_id;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime expiresAt;
    private Boolean autoRenew;

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