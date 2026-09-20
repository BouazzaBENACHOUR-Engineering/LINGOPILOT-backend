package com.example.AiLanguageApp.Payment.Service.Implementation;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.Payment.Service.Interfaces.PaymentProviderService;
import com.example.AiLanguageApp.Payment.PaymentResult;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import com.example.AiLanguageApp.model.User;

@Service
public class MockPaymentProviderServiceImpl implements PaymentProviderService {

    public PaymentResult processPayment(
            User user,
            SubscriptionPlan plan) {

        String transactionReference =
                "MOCK-" + UUID.randomUUID();

        return new PaymentResult(
                true,
                "MOCK",
                transactionReference,
                "SUCCESS",
                "Mock payment completed successfully"
        );
    }
}