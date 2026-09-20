package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.AI.DTO.LessonExecutionResponse;

public interface LessonExecutionService {

    LessonExecutionResponse startLesson(
            Long conversationId,
            Long lessonId
    );
}