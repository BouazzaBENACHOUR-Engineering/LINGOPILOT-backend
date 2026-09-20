package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.SubscriptionPlanRequest;
import com.example.AiLanguageApp.DTO.Response.SubscriptionPlanResponse;
import com.example.AiLanguageApp.Service.Interface.SubscriptionPlanService;
import com.example.AiLanguageApp.model.SubscriptionPlan;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subscription-plans")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    public SubscriptionPlanController(
            SubscriptionPlanService subscriptionPlanService) {
        this.subscriptionPlanService = subscriptionPlanService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionPlanResponse> create(
            @Valid @RequestBody SubscriptionPlanRequest request) {
        return ResponseEntity.ok(subscriptionPlanService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(subscriptionPlanService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return subscriptionPlanService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionPlan> update(
            @PathVariable Long id,
            @RequestBody SubscriptionPlan subscriptionPlan) {

        subscriptionPlan.setId(id);
        return ResponseEntity.ok(
                subscriptionPlanService.update(subscriptionPlan)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subscriptionPlanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}