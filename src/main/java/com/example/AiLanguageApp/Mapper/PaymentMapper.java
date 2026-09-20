package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.PaymentResponse;
import com.example.AiLanguageApp.model.Payment;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {

        if (payment == null) {
            return null;
        }

        PaymentResponse response = new PaymentResponse();

        response.setId(payment.getId());

        if (payment.getUser() != null) {
            response.setUser_id(
                    payment.getUser().getId()
            );
        }

        if (payment.getSubscription() != null) {
            response.setSubscription_id(
                    payment.getSubscription().getId()
            );
        }

        response.setAmount(payment.getAmount());
        response.setCurrency(payment.getCurrency());
        response.setStatus(payment.getStatus());
        response.setPaymentProvider(payment.getPaymentProvider());
        response.setTransactionReference(payment.getTransactionReference());
        response.setCreatedAt(payment.getCreatedAt());

        return response;
    }
}