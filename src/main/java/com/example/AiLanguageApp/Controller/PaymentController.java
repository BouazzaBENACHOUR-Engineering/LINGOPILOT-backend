package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.PaymentRequest;
import com.example.AiLanguageApp.DTO.Response.PaymentResponse;
import com.example.AiLanguageApp.Service.Interface.PaymentService;
import com.example.AiLanguageApp.model.Payment;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(
            @Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return paymentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(
            @PathVariable Long id,
            @RequestBody Payment payment) {

        payment.setId(id);
        return ResponseEntity.ok(paymentService.update(payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}