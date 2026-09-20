package com.example.AiLanguageApp.Payment.Service.Interfaces;

import com.example.AiLanguageApp.model.Subscription;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import com.example.AiLanguageApp.model.User;

public interface SubscriptionActivationService {

    Subscription activate(
            User user,
            SubscriptionPlan plan
    );
}