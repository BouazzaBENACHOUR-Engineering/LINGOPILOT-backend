package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.AiAssessmentRequest;
import com.example.AiLanguageApp.DTO.Response.AiAssessmentResponse;
import com.example.AiLanguageApp.Service.Interface.AiAssessmentService;
import com.example.AiLanguageApp.model.AiAssessment;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai-assessments")
public class AiAssessmentController {

    private final AiAssessmentService aiAssessmentService;

    public AiAssessmentController(
            AiAssessmentService aiAssessmentService) {

        this.aiAssessmentService = aiAssessmentService;
    }

    @PostMapping
    public ResponseEntity<AiAssessmentResponse> create(
            @Valid @RequestBody AiAssessmentRequest request) {

        return ResponseEntity.ok(
                aiAssessmentService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(
                aiAssessmentService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Long id) {

        return aiAssessmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiAssessment> update(
            @PathVariable Long id,
            @RequestBody AiAssessment aiAssessment) {

        aiAssessment.setId(id);

        return ResponseEntity.ok(
                aiAssessmentService.update(aiAssessment)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) {

        aiAssessmentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}