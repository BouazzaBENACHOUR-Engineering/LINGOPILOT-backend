package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.AI.Grading.ExerciseGradeResult;
import com.example.AiLanguageApp.model.Exercise;

public interface ExerciseGradingService {

    ExerciseGradeResult grade(
            Exercise exercise,
            String answer
    );
}