package com.example.AiLanguageApp.Payment.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.Payment.DTO.Response.SubscriptionPlanMobileResponse;
import com.example.AiLanguageApp.Payment.Service.Interfaces.SubscriptionPlanMobileService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionPlanMobileController {

    private final SubscriptionPlanMobileService subscriptionPlanMobileService;

    public SubscriptionPlanMobileController(
            SubscriptionPlanMobileService subscriptionPlanMobileService) {

        this.subscriptionPlanMobileService =
                subscriptionPlanMobileService;
    }

    @GetMapping("/plans")
    public ResponseEntity<List<SubscriptionPlanMobileResponse>> getAvailablePlans() {

        return ResponseEntity.ok(
                subscriptionPlanMobileService.getAvailablePlans()
        );
    }
}