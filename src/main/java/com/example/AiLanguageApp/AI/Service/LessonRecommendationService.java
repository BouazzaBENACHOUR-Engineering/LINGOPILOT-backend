package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.AI.DTO.LessonRecommendation;

public interface LessonRecommendationService {

    LessonRecommendation recommend(
            Long conversationId
    );
}