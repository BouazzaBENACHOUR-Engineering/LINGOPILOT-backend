package com.example.AiLanguageApp.AI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.AI.DTO.Request.ExerciseSubmissionRequest;
import com.example.AiLanguageApp.AI.DTO.Response.ExerciseSubmissionResponse;
import com.example.AiLanguageApp.AI.Service.ExerciseSubmissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai/exercises")
public class ExerciseSubmissionController {

    private final ExerciseSubmissionService
            exerciseSubmissionService;

    public ExerciseSubmissionController(
            ExerciseSubmissionService exerciseSubmissionService) {

        this.exerciseSubmissionService =
                exerciseSubmissionService;
    }

    @PostMapping("/{exerciseId}/submit")
    public ResponseEntity<ExerciseSubmissionResponse> submit(
            @PathVariable Long exerciseId,
            @Valid @RequestBody ExerciseSubmissionRequest request) {

        return ResponseEntity.ok(
                exerciseSubmissionService.submit(
                        exerciseId,
                        request
                )
        );
    }
}