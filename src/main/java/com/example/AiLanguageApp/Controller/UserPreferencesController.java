package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.UserPreferencesRequest;
import com.example.AiLanguageApp.DTO.Response.UserPreferencesResponse;
import com.example.AiLanguageApp.Service.Interface.UserPreferencesService;
import com.example.AiLanguageApp.model.UserPreferences;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-preferences")
public class UserPreferencesController {

    private final UserPreferencesService userPreferencesService;

    public UserPreferencesController(
            UserPreferencesService userPreferencesService) {
        this.userPreferencesService = userPreferencesService;
    }

    @PostMapping
    public ResponseEntity<UserPreferencesResponse> create(
            @Valid @RequestBody UserPreferencesRequest request) {
        return ResponseEntity.ok(
                userPreferencesService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userPreferencesService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userPreferencesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPreferences> update(
            @PathVariable Long id,
            @RequestBody UserPreferences userPreferences) {

        userPreferences.setId(id);

        return ResponseEntity.ok(
                userPreferencesService.update(userPreferences)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userPreferencesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}