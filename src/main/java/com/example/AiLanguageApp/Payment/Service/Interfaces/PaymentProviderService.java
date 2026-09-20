package com.example.AiLanguageApp.Payment.Service.Interfaces;

import com.example.AiLanguageApp.Payment.PaymentResult;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import com.example.AiLanguageApp.model.User;

public interface PaymentProviderService {

    PaymentResult processPayment(
            User user,
            SubscriptionPlan plan
    );
}