package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.VocabularyRequest;
import com.example.AiLanguageApp.DTO.Response.VocabularyResponse;
import com.example.AiLanguageApp.Service.Interface.VocabularyService;
import com.example.AiLanguageApp.model.Vocabulary;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vocabularies")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    public VocabularyController(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    @PostMapping
    public ResponseEntity<VocabularyResponse> create(
            @Valid @RequestBody VocabularyRequest request) {
        return ResponseEntity.ok(
                vocabularyService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                vocabularyService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return vocabularyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vocabulary> update(
            @PathVariable Long id,
            @RequestBody Vocabulary vocabulary) {

        vocabulary.setId(id);

        return ResponseEntity.ok(
                vocabularyService.update(vocabulary)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vocabularyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}