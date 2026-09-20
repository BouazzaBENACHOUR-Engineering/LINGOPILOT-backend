package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotNull;

public class PaymentRequestDTO {

    @NotNull(message = "Subscription ID is required")
    private Long subscription_id;

    @NotNull(message = "Payment provider ID is required")
    private Long payment_provider_id;

    public Long getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(Long subscription_id) {
        this.subscription_id = subscription_id;
    }

    public Long getPayment_provider_id() {
        return payment_provider_id;
    }

    public void setPayment_provider_id(Long payment_provider_id) {
        this.payment_provider_id = payment_provider_id;
    }
}