package com.example.AiLanguageApp.Payment.DTO.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentCheckoutResponse {

    private Long payment_id;
    private Long subscription_id;
    private Long plan_id;
    private String plan_name;
    private BigDecimal amount;
    private String currency;
    private String payment_status;
    private String payment_provider;
    private String transaction_reference;
    private String subscription_status;
    private LocalDateTime subscription_started_at;
    private LocalDateTime subscription_expires_at;
    private String message;

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_provider() {
        return payment_provider;
    }

    public void setPayment_provider(String payment_provider) {
        this.payment_provider = payment_provider;
    }

    public String getTransaction_reference() {
        return transaction_reference;
    }

    public void setTransaction_reference(String transaction_reference) {
        this.transaction_reference = transaction_reference;
    }

    public String getSubscription_status() {
        return subscription_status;
    }

    public void setSubscription_status(String subscription_status) {
        this.subscription_status = subscription_status;
    }

    public LocalDateTime getSubscription_started_at() {
        return subscription_started_at;
    }

    public void setSubscription_started_at(LocalDateTime subscription_started_at) {
        this.subscription_started_at = subscription_started_at;
    }

    public LocalDateTime getSubscription_expires_at() {
        return subscription_expires_at;
    }

    public void setSubscription_expires_at(LocalDateTime subscription_expires_at) {
        this.subscription_expires_at = subscription_expires_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}