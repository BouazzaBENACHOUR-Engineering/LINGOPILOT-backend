package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.LanguageRequest;
import com.example.AiLanguageApp.DTO.Response.LanguageResponse;
import com.example.AiLanguageApp.Service.Interface.LanguageService;
import com.example.AiLanguageApp.model.Language;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    public ResponseEntity<LanguageResponse> create(
            @Valid @RequestBody LanguageRequest request) {
        return ResponseEntity.ok(languageService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(languageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return languageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> update(
            @PathVariable Long id,
            @RequestBody Language language) {

        language.setId(id);
        return ResponseEntity.ok(languageService.update(language));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        languageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}