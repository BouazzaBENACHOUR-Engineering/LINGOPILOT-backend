package com.example.AiLanguageApp.Payment;

public class PaymentResult {

    private Boolean success;

    private String provider;

    private String transaction_reference;

    private String status;

    private String message;

    public PaymentResult() {
    }

    public PaymentResult(
            Boolean success,
            String provider,
            String transaction_reference,
            String status,
            String message) {

        this.success = success;
        this.provider = provider;
        this.transaction_reference = transaction_reference;
        this.status = status;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTransaction_reference() {
        return transaction_reference;
    }

    public void setTransaction_reference(String transaction_reference) {
        this.transaction_reference = transaction_reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}