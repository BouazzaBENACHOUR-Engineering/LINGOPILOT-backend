package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.PaymentRequest;
import com.example.AiLanguageApp.DTO.Response.PaymentResponse;
import com.example.AiLanguageApp.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment save(Payment payment);

    Payment update(Payment payment);

    Optional<Payment> findById(Long id);

    List<Payment> findAll();
    
    PaymentResponse create(PaymentRequest request);

    void deleteById(Long id);
}