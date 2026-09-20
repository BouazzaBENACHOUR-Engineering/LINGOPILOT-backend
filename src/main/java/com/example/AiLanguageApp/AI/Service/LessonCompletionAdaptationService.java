package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.model.UserLessonProgress;

public interface LessonCompletionAdaptationService {

    void adapt(
            UserLessonProgress lessonProgress
    );
}