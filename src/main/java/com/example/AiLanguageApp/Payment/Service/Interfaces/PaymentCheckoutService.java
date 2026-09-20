package com.example.AiLanguageApp.Payment.Service.Interfaces;

import com.example.AiLanguageApp.Payment.DTO.Response.PaymentCheckoutResponse;

public interface PaymentCheckoutService {

    PaymentCheckoutResponse checkout(
            Long userId,
            Long planId
    );
}