package com.example.AiLanguageApp.DTOs.RequestDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SubscriptionPlanRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Price cannot be negative"
    )
    private BigDecimal price;

    @NotBlank(message = "Currency is required")
    @Size(
            min = 3,
            max = 3,
            message = "Currency must contain exactly 3 characters"
    )
    private String currency;

    @NotBlank(message = "Billing period is required")
    @Size(
            max = 20,
            message = "Billing period must not exceed 20 characters"
    )
    private String billing_period;

    private Integer ai_messages_limit;

    private Integer speaking_limit;

    @NotNull(message = "Active status is required")
    private Boolean is_active;

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