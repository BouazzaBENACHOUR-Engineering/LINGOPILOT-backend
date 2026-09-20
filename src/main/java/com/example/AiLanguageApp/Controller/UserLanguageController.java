package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.UserLanguageRequest;
import com.example.AiLanguageApp.DTO.Response.UserLanguageResponse;
import com.example.AiLanguageApp.Service.Interface.UserLanguageService;
import com.example.AiLanguageApp.model.UserLanguage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-languages")
public class UserLanguageController {

    private final UserLanguageService userLanguageService;

    public UserLanguageController(UserLanguageService userLanguageService) {
        this.userLanguageService = userLanguageService;
    }

    @PostMapping
    public ResponseEntity<UserLanguageResponse> create(
            @Valid @RequestBody UserLanguageRequest request) {
        return ResponseEntity.ok(userLanguageService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userLanguageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userLanguageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserLanguage> update(
            @PathVariable Long id,
            @RequestBody UserLanguage userLanguage) {

        userLanguage.setId(id);

        return ResponseEntity.ok(
                userLanguageService.update(userLanguage)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userLanguageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}