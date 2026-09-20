package com.example.AiLanguageApp.Payment.DTO.Request;

import jakarta.validation.constraints.NotNull;

public class PaymentCheckoutRequest {

    @NotNull(message = "plan_id is required")
    private Long plan_id;

    public Long getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Long plan_id) {
        this.plan_id = plan_id;
    }
}