package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.NotificationRequest;
import com.example.AiLanguageApp.DTO.Response.NotificationResponse;
import com.example.AiLanguageApp.Service.Interface.NotificationService;
import com.example.AiLanguageApp.model.Notification;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> create(
            @Valid @RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return notificationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(
            @PathVariable Long id,
            @RequestBody Notification notification) {

        notification.setId(id);
        return ResponseEntity.ok(notificationService.update(notification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        notificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}