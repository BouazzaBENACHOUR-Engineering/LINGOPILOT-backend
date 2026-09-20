package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.SubscriptionRequest;
import com.example.AiLanguageApp.DTO.Response.SubscriptionResponse;
import com.example.AiLanguageApp.Service.Interface.SubscriptionService;
import com.example.AiLanguageApp.model.Subscription;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> create(
            @Valid @RequestBody SubscriptionRequest request) {
        return ResponseEntity.ok(subscriptionService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return subscriptionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> update(
            @PathVariable Long id,
            @RequestBody Subscription subscription) {

        subscription.setId(id);
        return ResponseEntity.ok(subscriptionService.update(subscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subscriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}