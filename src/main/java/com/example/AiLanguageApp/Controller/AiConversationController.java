package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.AiConversationRequest;
import com.example.AiLanguageApp.DTO.Response.AiConversationResponse;
import com.example.AiLanguageApp.Service.Interface.AiConversationService;
import com.example.AiLanguageApp.model.AiConversation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai-conversations")
public class AiConversationController {

    private final AiConversationService aiConversationService;

    public AiConversationController(AiConversationService aiConversationService) {
        this.aiConversationService = aiConversationService;
    }

    @PostMapping
    public ResponseEntity<AiConversationResponse> create(
            @Valid @RequestBody AiConversationRequest request) {
        return ResponseEntity.ok(aiConversationService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(aiConversationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return aiConversationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiConversation> update(
            @PathVariable Long id,
            @RequestBody AiConversation aiConversation) {

        aiConversation.setId(id);
        return ResponseEntity.ok(aiConversationService.update(aiConversation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        aiConversationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}