package com.example.AiLanguageApp.DTO.Response;

import java.math.BigDecimal;

public class SubscriptionPlanResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private String currency;
    private String billing_period;
    private Integer ai_messages_limit;
    private Integer speaking_limit;
    private Integer conversation_limit;
    private Boolean ads_enabled;
    private Boolean is_active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getConversation_limit() {
        return conversation_limit;
    }

    public void setConversation_limit(Integer conversation_limit) {
        this.conversation_limit = conversation_limit;
    }

    public Boolean getAds_enabled() {
        return ads_enabled;
    }

    public void setAds_enabled(Boolean ads_enabled) {
        this.ads_enabled = ads_enabled;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}
