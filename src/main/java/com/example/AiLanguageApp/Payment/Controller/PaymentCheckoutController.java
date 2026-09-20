package com.example.AiLanguageApp.Payment.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AiLanguageApp.Payment.DTO.Request.PaymentCheckoutRequest;
import com.example.AiLanguageApp.Payment.DTO.Response.PaymentCheckoutResponse;
import com.example.AiLanguageApp.Payment.Service.Interfaces.PaymentCheckoutService;
import com.example.AiLanguageApp.security.AuthenticatedUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentCheckoutController {

    private final PaymentCheckoutService paymentCheckoutService;

    private final AuthenticatedUserService authenticatedUserService;

    public PaymentCheckoutController(
            PaymentCheckoutService paymentCheckoutService,
            AuthenticatedUserService authenticatedUserService) {

        this.paymentCheckoutService =
                paymentCheckoutService;

        this.authenticatedUserService =
                authenticatedUserService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<PaymentCheckoutResponse> checkout(
            @Valid @RequestBody PaymentCheckoutRequest request) {

        Long userId =
                authenticatedUserService
                        .getAuthenticatedUserId();

        return ResponseEntity.ok(
                paymentCheckoutService.checkout(
                        userId,
                        request.getPlan_id()
                )
        );
    }
}