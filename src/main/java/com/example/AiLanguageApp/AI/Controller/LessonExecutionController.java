package com.example.AiLanguageApp.AI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.AI.DTO.LessonExecutionResponse;
import com.example.AiLanguageApp.AI.Service.LessonExecutionService;

@RestController
@RequestMapping("/api/ai/lessons")
public class LessonExecutionController {

    private final LessonExecutionService
            lessonExecutionService;

    public LessonExecutionController(
            LessonExecutionService lessonExecutionService) {

        this.lessonExecutionService =
                lessonExecutionService;
    }

    @PostMapping(
            "/{lessonId}/start/{conversationId}"
    )
    public ResponseEntity<LessonExecutionResponse> startLesson(
            @PathVariable Long lessonId,
            @PathVariable Long conversationId) {

        return ResponseEntity.ok(
                lessonExecutionService
                        .startLesson(
                                conversationId,
                                lessonId
                        )
        );
    }
}