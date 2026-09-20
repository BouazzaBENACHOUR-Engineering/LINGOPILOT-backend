package com.example.AiLanguageApp.AI.Service;

public interface AiUsageService {

    void checkAiMessageAccess(Long userId);

    void consumeAiMessage(Long userId);
}