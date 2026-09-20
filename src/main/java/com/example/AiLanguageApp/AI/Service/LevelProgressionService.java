package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.AI.Progression.LevelReadinessResult;

public interface LevelProgressionService {

    LevelReadinessResult evaluateReadiness(
            Long userId,
            Long languageId
    );

    LevelReadinessResult promoteIfReady(
            Long userId,
            Long languageId
    );
}