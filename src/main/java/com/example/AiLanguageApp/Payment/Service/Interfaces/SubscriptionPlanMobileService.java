package com.example.AiLanguageApp.Payment.Service.Interfaces;

import java.util.List;

import com.example.AiLanguageApp.Payment.DTO.Response.SubscriptionPlanMobileResponse;

public interface SubscriptionPlanMobileService {

    List<SubscriptionPlanMobileResponse> getAvailablePlans();
}