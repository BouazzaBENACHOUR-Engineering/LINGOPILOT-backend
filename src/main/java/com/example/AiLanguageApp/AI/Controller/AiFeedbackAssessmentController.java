package com.example.AiLanguageApp.AI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.AI.Service.AiAssessmentEngine;
import com.example.AiLanguageApp.DTO.Response.ConversationFeedbackResponse;

@RestController
@RequestMapping("/api/ai/assessment")
public class AiFeedbackAssessmentController {

    private final AiAssessmentEngine aiAssessmentEngine;

    public AiFeedbackAssessmentController(
            AiAssessmentEngine aiAssessmentEngine) {

        this.aiAssessmentEngine =
                aiAssessmentEngine;
    }

    @PostMapping("/{messageId}")
    public ResponseEntity<ConversationFeedbackResponse> assess(
            @PathVariable Long messageId) {

        return ResponseEntity.ok(
                aiAssessmentEngine.assessMessage(
                        messageId
                )
        );
    }
}