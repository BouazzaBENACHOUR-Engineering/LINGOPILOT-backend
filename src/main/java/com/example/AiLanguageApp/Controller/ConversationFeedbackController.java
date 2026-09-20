package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.ConversationFeedbackRequest;
import com.example.AiLanguageApp.DTO.Response.ConversationFeedbackResponse;
import com.example.AiLanguageApp.Service.Interface.ConversationFeedbackService;
import com.example.AiLanguageApp.model.ConversationFeedback;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/conversation-feedback")
public class ConversationFeedbackController {

    private final ConversationFeedbackService conversationFeedbackService;

    public ConversationFeedbackController(
            ConversationFeedbackService conversationFeedbackService) {
        this.conversationFeedbackService = conversationFeedbackService;
    }

    @PostMapping
    public ResponseEntity<ConversationFeedbackResponse> create(
            @Valid @RequestBody ConversationFeedbackRequest request) {
        return ResponseEntity.ok(conversationFeedbackService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(conversationFeedbackService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return conversationFeedbackService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversationFeedback> update(
            @PathVariable Long id,
            @RequestBody ConversationFeedback conversationFeedback) {

        conversationFeedback.setId(id);
        return ResponseEntity.ok(
                conversationFeedbackService.update(conversationFeedback)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        conversationFeedbackService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}