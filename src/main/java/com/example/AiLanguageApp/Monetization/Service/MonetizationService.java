package com.example.AiLanguageApp.Monetization.Service;

import com.example.AiLanguageApp.Monetization.DTO.Response.MonetizationStatusResponse;

public interface MonetizationService {

    MonetizationStatusResponse getStatus(Long userId);

    void assertCanStartAiConversation(Long userId);
}
