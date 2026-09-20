package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.AI.DTO.Request.AiChatRequest;
import com.example.AiLanguageApp.AI.DTO.Response.AiChatResponse;

public interface AiConversationOrchestrator {

    AiChatResponse chat(AiChatRequest request);
}