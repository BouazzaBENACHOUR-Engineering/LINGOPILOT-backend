package com.example.AiLanguageApp.Learning.Controller;

import com.example.AiLanguageApp.Learning.DTO.Request.LearningExerciseSubmissionRequest;
import com.example.AiLanguageApp.Learning.DTO.Request.StartLessonRequest;
import com.example.AiLanguageApp.Learning.DTO.Response.LearningExerciseSubmissionResponse;
import com.example.AiLanguageApp.Learning.DTO.Response.LearningLessonResponse;
import com.example.AiLanguageApp.Learning.Service.LearningService;
import com.example.AiLanguageApp.model.UserLessonProgress;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learning")
public class LearningController {

    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<LearningLessonResponse> getLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(learningService.getLesson(lessonId));
    }

    @PostMapping("/lessons/{lessonId}/start")
    public ResponseEntity<UserLessonProgress> startLesson(
            @PathVariable Long lessonId,
            @Valid @RequestBody StartLessonRequest request) {
        return ResponseEntity.ok(learningService.startLesson(lessonId, request.getUser_id()));
    }

    @PostMapping("/exercises/{exerciseId}/submit")
    public ResponseEntity<LearningExerciseSubmissionResponse> submit(
            @PathVariable Long exerciseId,
            @Valid @RequestBody LearningExerciseSubmissionRequest request) {
        return ResponseEntity.ok(learningService.submit(exerciseId, request));
    }
}
