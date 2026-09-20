package com.example.AiLanguageApp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscription_usage")
public class SubscriptionUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription_id;

    @Column(name = "ai_messages_used", nullable = false)
    private Integer ai_messages_used;

    @Column(name = "speaking_used", nullable = false)
    private Integer speaking_used;

    @Column(name = "period_started_at", nullable = false)
    private LocalDateTime period_started_at;

    @Column(name = "period_ends_at")
    private LocalDateTime period_ends_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    public SubscriptionUsage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subscription getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(Subscription subscription_id) {
        this.subscription_id = subscription_id;
    }

    public Integer getAi_messages_used() {
        return ai_messages_used;
    }

    public void setAi_messages_used(Integer ai_messages_used) {
        this.ai_messages_used = ai_messages_used;
    }

    public Integer getSpeaking_used() {
        return speaking_used;
    }

    public void setSpeaking_used(Integer speaking_used) {
        this.speaking_used = speaking_used;
    }

    public LocalDateTime getPeriod_started_at() {
        return period_started_at;
    }

    public void setPeriod_started_at(LocalDateTime period_started_at) {
        this.period_started_at = period_started_at;
    }

    public LocalDateTime getPeriod_ends_at() {
        return period_ends_at;
    }

    public void setPeriod_ends_at(LocalDateTime period_ends_at) {
        this.period_ends_at = period_ends_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}