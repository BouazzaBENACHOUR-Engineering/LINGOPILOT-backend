package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.AiMessageRequest;
import com.example.AiLanguageApp.DTO.Response.AiMessageResponse;
import com.example.AiLanguageApp.Service.Interface.AiMessageService;
import com.example.AiLanguageApp.model.AiMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai-messages")
public class AiMessageController {

    private final AiMessageService aiMessageService;

    public AiMessageController(AiMessageService aiMessageService) {
        this.aiMessageService = aiMessageService;
    }

    @PostMapping
    public ResponseEntity<AiMessageResponse> create(
            @Valid @RequestBody AiMessageRequest request) {
        return ResponseEntity.ok(aiMessageService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(aiMessageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return aiMessageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiMessage> update(
            @PathVariable Long id,
            @RequestBody AiMessage aiMessage) {

        aiMessage.setId(id);
        return ResponseEntity.ok(aiMessageService.update(aiMessage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        aiMessageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}