package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.UserNotificationSettingsRequest;
import com.example.AiLanguageApp.DTO.Response.UserNotificationSettingsResponse;
import com.example.AiLanguageApp.Service.Interface.UserNotificationSettingsService;
import com.example.AiLanguageApp.model.UserNotificationSettings;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-notification-settings")
public class UserNotificationSettingsController {

    private final UserNotificationSettingsService userNotificationSettingsService;

    public UserNotificationSettingsController(
            UserNotificationSettingsService userNotificationSettingsService) {
        this.userNotificationSettingsService = userNotificationSettingsService;
    }

    @PostMapping
    public ResponseEntity<UserNotificationSettingsResponse> create(
            @Valid @RequestBody UserNotificationSettingsRequest request) {
        return ResponseEntity.ok(
                userNotificationSettingsService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userNotificationSettingsService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userNotificationSettingsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserNotificationSettings> update(
            @PathVariable Long id,
            @RequestBody UserNotificationSettings userNotificationSettings) {

        userNotificationSettings.setId(id);

        return ResponseEntity.ok(
                userNotificationSettingsService.update(
                        userNotificationSettings
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userNotificationSettingsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}