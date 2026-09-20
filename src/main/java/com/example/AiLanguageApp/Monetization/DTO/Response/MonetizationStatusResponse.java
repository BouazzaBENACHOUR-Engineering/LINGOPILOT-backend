package com.example.AiLanguageApp.Monetization.DTO.Response;

import java.math.BigDecimal;

public class MonetizationStatusResponse {

    private Long user_id;
    private Long plan_id;
    private String plan_name;
    private BigDecimal price;
    private String currency;
    private String billing_period;
    private Integer conversation_limit;
    private Long conversations_used;
    private Long conversations_remaining;
    private Boolean unlimited_conversations;
    private Boolean ads_enabled;
    private Boolean can_start_ai_conversation;

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

    public Integer getConversation_limit() {
        return conversation_limit;
    }

    public void setConversation_limit(Integer conversation_limit) {
        this.conversation_limit = conversation_limit;
    }

    public Long getConversations_used() {
        return conversations_used;
    }

    public void setConversations_used(Long conversations_used) {
        this.conversations_used = conversations_used;
    }

    public Long getConversations_remaining() {
        return conversations_remaining;
    }

    public void setConversations_remaining(Long conversations_remaining) {
        this.conversations_remaining = conversations_remaining;
    }

    public Boolean getUnlimited_conversations() {
        return unlimited_conversations;
    }

    public void setUnlimited_conversations(Boolean unlimited_conversations) {
        this.unlimited_conversations = unlimited_conversations;
    }

    public Boolean getAds_enabled() {
        return ads_enabled;
    }

    public void setAds_enabled(Boolean ads_enabled) {
        this.ads_enabled = ads_enabled;
    }

    public Boolean getCan_start_ai_conversation() {
        return can_start_ai_conversation;
    }

    public void setCan_start_ai_conversation(Boolean can_start_ai_conversation) {
        this.can_start_ai_conversation = can_start_ai_conversation;
    }
}
