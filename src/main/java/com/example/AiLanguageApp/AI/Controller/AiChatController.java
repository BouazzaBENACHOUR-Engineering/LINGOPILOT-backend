package com.example.AiLanguageApp.AI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.AI.DTO.Request.AiChatRequest;
import com.example.AiLanguageApp.AI.DTO.Response.AiChatResponse;
import com.example.AiLanguageApp.AI.Service.AiConversationOrchestrator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    private final AiConversationOrchestrator aiConversationOrchestrator;

    public AiChatController(
            AiConversationOrchestrator aiConversationOrchestrator) {

        this.aiConversationOrchestrator =
                aiConversationOrchestrator;
    }

    @PostMapping("/chat")
    public ResponseEntity<AiChatResponse> chat(
            @Valid @RequestBody AiChatRequest request) {

        return ResponseEntity.ok(
                aiConversationOrchestrator.chat(request)
        );
    }
}