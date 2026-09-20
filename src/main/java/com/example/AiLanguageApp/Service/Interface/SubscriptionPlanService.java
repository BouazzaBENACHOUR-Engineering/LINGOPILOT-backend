package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.SubscriptionPlanRequest;
import com.example.AiLanguageApp.DTO.Response.SubscriptionPlanResponse;
import com.example.AiLanguageApp.model.SubscriptionPlan;

import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanService {

    SubscriptionPlan save(SubscriptionPlan subscriptionPlan);

    SubscriptionPlan update(SubscriptionPlan subscriptionPlan);

    Optional<SubscriptionPlan> findById(Long id);

    List<SubscriptionPlan> findAll();
    
    SubscriptionPlanResponse create(SubscriptionPlanRequest request);

    void deleteById(Long id);
}