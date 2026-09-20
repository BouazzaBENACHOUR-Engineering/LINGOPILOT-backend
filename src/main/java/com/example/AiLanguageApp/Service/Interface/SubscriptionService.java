package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.SubscriptionRequest;
import com.example.AiLanguageApp.DTO.Response.SubscriptionResponse;
import com.example.AiLanguageApp.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

    Subscription save(Subscription subscription);

    Subscription update(Subscription subscription);

    Optional<Subscription> findById(Long id);

    List<Subscription> findAll();
    
    SubscriptionResponse create(SubscriptionRequest request);

    void deleteById(Long id);
}