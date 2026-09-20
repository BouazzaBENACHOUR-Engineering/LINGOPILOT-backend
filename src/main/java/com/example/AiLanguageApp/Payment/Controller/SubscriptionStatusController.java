package com.example.AiLanguageApp.Payment.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.Payment.DTO.Response.SubscriptionStatusResponse;
import com.example.AiLanguageApp.Payment.Service.Interfaces.SubscriptionStatusService;
import com.example.AiLanguageApp.security.AuthenticatedUserService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionStatusController {

    private final SubscriptionStatusService subscriptionStatusService;

    private final AuthenticatedUserService authenticatedUserService;

    public SubscriptionStatusController(
            SubscriptionStatusService subscriptionStatusService,
            AuthenticatedUserService authenticatedUserService) {

        this.subscriptionStatusService =
                subscriptionStatusService;

        this.authenticatedUserService =
                authenticatedUserService;
    }

    @GetMapping("/status")
    public ResponseEntity<SubscriptionStatusResponse> getStatus() {

        Long userId =
                authenticatedUserService
                        .getAuthenticatedUserId();

        return ResponseEntity.ok(
                subscriptionStatusService.getStatus(
                        userId
                )
        );
    }
}