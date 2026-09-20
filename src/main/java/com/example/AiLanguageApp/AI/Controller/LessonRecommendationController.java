package com.example.AiLanguageApp.AI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.AI.DTO.LessonRecommendation;
import com.example.AiLanguageApp.AI.Service.LessonRecommendationService;

@RestController
@RequestMapping("/api/ai/recommendations")
public class LessonRecommendationController {

    private final LessonRecommendationService
            lessonRecommendationService;

    public LessonRecommendationController(
            LessonRecommendationService lessonRecommendationService) {

        this.lessonRecommendationService =
                lessonRecommendationService;
    }

    @GetMapping("/lesson/{conversationId}")
    public ResponseEntity<LessonRecommendation> recommendLesson(
            @PathVariable Long conversationId) {

        return ResponseEntity.ok(
                lessonRecommendationService.recommend(
                        conversationId
                )
        );
    }
}