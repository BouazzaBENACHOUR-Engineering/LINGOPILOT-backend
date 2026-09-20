package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.AI.DTO.Request.ExerciseSubmissionRequest;
import com.example.AiLanguageApp.AI.DTO.Response.ExerciseSubmissionResponse;

public interface ExerciseSubmissionService {

    ExerciseSubmissionResponse submit(
            Long exerciseId,
            ExerciseSubmissionRequest request
    );
}