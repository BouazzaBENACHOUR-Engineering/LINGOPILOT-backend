package com.example.AiLanguageApp.Payment.DTO.Response;

import java.math.BigDecimal;

public class SubscriptionPlanMobileResponse {

    private Long plan_id;
    private String name;
    private BigDecimal price;
    private String currency;
    private String billing_period;
    private Integer ai_messages_limit;
    private Integer speaking_limit;
    private Boolean is_active;

    public Long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAi_messages_limit() {
        return ai_messages_limit;
    }

    public void setAi_messages_limit(Integer ai_messages_limit) {
        this.ai_messages_limit = ai_messages_limit;
    }

    public Integer getSpeaking_limit() {
        return speaking_limit;
    }

    public void setSpeaking_limit(Integer speaking_limit) {
        this.speaking_limit = speaking_limit;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}