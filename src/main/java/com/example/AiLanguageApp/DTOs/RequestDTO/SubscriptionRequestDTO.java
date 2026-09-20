package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotNull;

public class SubscriptionRequestDTO {

    @NotNull(message = "Subscription plan ID is required")
    private Long subscription_plan_id;

    public Long getSubscription_plan_id() {
        return subscription_plan_id;
    }

    public void setSubscription_plan_id(Long subscription_plan_id) {
        this.subscription_plan_id = subscription_plan_id;
    }
}