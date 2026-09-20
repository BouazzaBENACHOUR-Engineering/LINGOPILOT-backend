package com.example.AiLanguageApp.Monetization.Controller;

import com.example.AiLanguageApp.Monetization.DTO.Response.MonetizationStatusResponse;
import com.example.AiLanguageApp.Monetization.Service.MonetizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monetization")
public class MonetizationController {

    private final MonetizationService monetizationService;

    public MonetizationController(MonetizationService monetizationService) {
        this.monetizationService = monetizationService;
    }

    @GetMapping("/users/{userId}/status")
    public ResponseEntity<MonetizationStatusResponse> getStatus(@PathVariable Long userId) {
        return ResponseEntity.ok(monetizationService.getStatus(userId));
    }
}
