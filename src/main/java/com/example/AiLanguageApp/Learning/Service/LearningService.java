package com.example.AiLanguageApp.Learning.Service;

import com.example.AiLanguageApp.Learning.DTO.Request.LearningExerciseSubmissionRequest;
import com.example.AiLanguageApp.Learning.DTO.Response.LearningExerciseSubmissionResponse;
import com.example.AiLanguageApp.Learning.DTO.Response.LearningLessonResponse;
import com.example.AiLanguageApp.model.UserLessonProgress;

public interface LearningService {
    LearningLessonResponse getLesson(Long lessonId);
    UserLessonProgress startLesson(Long lessonId, Long userId);
    LearningExerciseSubmissionResponse submit(Long exerciseId, LearningExerciseSubmissionRequest request);
}
