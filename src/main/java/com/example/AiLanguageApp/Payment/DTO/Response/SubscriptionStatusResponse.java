package com.example.AiLanguageApp.Payment.DTO.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SubscriptionStatusResponse {

    private Long subscription_id;

    private Long plan_id;

    private String plan_name;

    private BigDecimal price;

    private String currency;

    private String billing_period;

    private String subscription_status;

    private LocalDateTime started_at;

    private LocalDateTime expires_at;

    private Boolean auto_renew;

    private Integer ai_messages_limit;

    private Integer ai_messages_used;

    private Integer ai_messages_remaining;

    private Integer speaking_limit;

    private Integer speaking_used;

    private Integer speaking_remaining;

    private Boolean ai_access_allowed;

    private Boolean speaking_access_allowed;

    private Boolean upgrade_required;

    public Long getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(Long subscription_id) {
        this.subscription_id = subscription_id;
    }

    public Long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBilling_period() {
        return billing_period;
    }

    public void setBilling_period(String billing_period) {
        this.billing_period = billing_period;
    }

    public String getSubscription_status() {
        return subscription_status;
    }

    public void setSubscription_status(String subscription_status) {
        this.subscription_status = subscription_status;
    }

    public LocalDateTime getStarted_at() {
        return started_at;
    }

    public void setStarted_at(LocalDateTime started_at) {
        this.started_at = started_at;
    }

    public LocalDateTime getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(LocalDateTime expires_at) {
        this.expires_at = expires_at;
    }

    public Boolean getAuto_renew() {
        return auto_renew;
    }

    public void setAuto_renew(Boolean auto_renew) {
        this.auto_renew = auto_renew;
    }

    public Integer getAi_messages_limit() {
        return ai_messages_limit;
    }

    public void setAi_messages_limit(Integer ai_messages_limit) {
        this.ai_messages_limit = ai_messages_limit;
    }

    public Integer getAi_messages_used() {
        return ai_messages_used;
    }

    public void setAi_messages_used(Integer ai_messages_used) {
        this.ai_messages_used = ai_messages_used;
    }

    public Integer getAi_messages_remaining() {
        return ai_messages_remaining;
    }

    public void setAi_messages_remaining(Integer ai_messages_remaining) {
        this.ai_messages_remaining = ai_messages_remaining;
    }

    public Integer getSpeaking_limit() {
        return speaking_limit;
    }

    public void setSpeaking_limit(Integer speaking_limit) {
        this.speaking_limit = speaking_limit;
    }

    public Integer getSpeaking_used() {
        return speaking_used;
    }

    public void setSpeaking_used(Integer speaking_used) {
        this.speaking_used = speaking_used;
    }

    public Integer getSpeaking_remaining() {
        return speaking_remaining;
    }

    public void setSpeaking_remaining(Integer speaking_remaining) {
        this.speaking_remaining = speaking_remaining;
    }

    public Boolean getAi_access_allowed() {
        return ai_access_allowed;
    }

    public void setAi_access_allowed(Boolean ai_access_allowed) {
        this.ai_access_allowed = ai_access_allowed;
    }

    public Boolean getSpeaking_access_allowed() {
        return speaking_access_allowed;
    }

    public void setSpeaking_access_allowed(Boolean speaking_access_allowed) {
        this.speaking_access_allowed = speaking_access_allowed;
    }

    public Boolean getUpgrade_required() {
        return upgrade_required;
    }

    public void setUpgrade_required(Boolean upgrade_required) {
        this.upgrade_required = upgrade_required;
    }
}