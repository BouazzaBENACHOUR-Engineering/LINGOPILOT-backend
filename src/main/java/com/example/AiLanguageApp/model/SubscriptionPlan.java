package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "subscription_plans")
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "currency", nullable = false, columnDefinition = "char(3)")
    private String currency;

    @Column(name = "billing_period", nullable = false, length = 20)
    private String billing_period;

    @Column(name = "ai_messages_limit")
    private Integer ai_messages_limit;

    @Column(name = "speaking_limit")
    private Integer speaking_limit;

    @Column(name = "conversation_limit")
    private Integer conversation_limit;

    @Column(name = "ads_enabled", nullable = false)
    private Boolean ads_enabled;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    public SubscriptionPlan() {
    }

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
