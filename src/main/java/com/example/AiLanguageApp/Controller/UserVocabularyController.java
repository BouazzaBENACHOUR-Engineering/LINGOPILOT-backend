package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.UserVocabularyRequest;
import com.example.AiLanguageApp.DTO.Response.UserVocabularyResponse;
import com.example.AiLanguageApp.Service.Interface.UserVocabularyService;
import com.example.AiLanguageApp.model.UserVocabulary;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-vocabulary")
public class UserVocabularyController {

    private final UserVocabularyService userVocabularyService;

    public UserVocabularyController(
            UserVocabularyService userVocabularyService) {
        this.userVocabularyService = userVocabularyService;
    }

    @PostMapping
    public ResponseEntity<UserVocabularyResponse> create(
            @Valid @RequestBody UserVocabularyRequest request) {
        return ResponseEntity.ok(
                userVocabularyService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userVocabularyService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userVocabularyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserVocabulary> update(
            @PathVariable Long id,
            @RequestBody UserVocabulary userVocabulary) {

        userVocabulary.setId(id);

        return ResponseEntity.ok(
                userVocabularyService.update(userVocabulary)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userVocabularyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}