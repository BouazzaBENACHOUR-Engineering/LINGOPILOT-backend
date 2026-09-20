package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.DTO.Response.ConversationFeedbackResponse;

public interface AiAssessmentEngine {

    ConversationFeedbackResponse assessMessage(
            Long messageId
    );
}