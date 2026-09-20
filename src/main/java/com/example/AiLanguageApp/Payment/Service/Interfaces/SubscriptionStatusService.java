package com.example.AiLanguageApp.Payment.Service.Interfaces;

import com.example.AiLanguageApp.Payment.DTO.Response.SubscriptionStatusResponse;

public interface SubscriptionStatusService {

    SubscriptionStatusResponse getStatus(
            Long userId
    );
}