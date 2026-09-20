package com.example.AiLanguageApp.AI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.AI.Progression.LevelReadinessResult;
import com.example.AiLanguageApp.AI.Service.LevelProgressionService;

@RestController
@RequestMapping("/api/ai/progression")
public class LevelProgressionController {

    private final LevelProgressionService levelProgressionService;

    public LevelProgressionController(
            LevelProgressionService levelProgressionService) {

        this.levelProgressionService =
                levelProgressionService;
    }

    @GetMapping("/readiness/{userId}/{languageId}")
    public ResponseEntity<LevelReadinessResult> evaluateReadiness(
            @PathVariable Long userId,
            @PathVariable Long languageId) {

        return ResponseEntity.ok(
                levelProgressionService.evaluateReadiness(
                        userId,
                        languageId
                )
        );
    }

    @PostMapping("/promote/{userId}/{languageId}")
    public ResponseEntity<LevelReadinessResult> promoteIfReady(
            @PathVariable Long userId,
            @PathVariable Long languageId) {

        return ResponseEntity.ok(
                levelProgressionService.promoteIfReady(
                        userId,
                        languageId
                )
        );
    }
}