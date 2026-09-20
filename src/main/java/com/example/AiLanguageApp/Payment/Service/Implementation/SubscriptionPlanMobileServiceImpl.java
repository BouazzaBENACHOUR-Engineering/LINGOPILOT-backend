package com.example.AiLanguageApp.Payment.Service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.Payment.DTO.Response.SubscriptionPlanMobileResponse;
import com.example.AiLanguageApp.Payment.Service.Interfaces.SubscriptionPlanMobileService;
import com.example.AiLanguageApp.Repository.SubscriptionPlanRepository;
import com.example.AiLanguageApp.model.SubscriptionPlan;

@Service
public class SubscriptionPlanMobileServiceImpl
        implements SubscriptionPlanMobileService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionPlanMobileServiceImpl(
            SubscriptionPlanRepository subscriptionPlanRepository) {

        this.subscriptionPlanRepository =
                subscriptionPlanRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionPlanMobileResponse> getAvailablePlans() {

        return subscriptionPlanRepository
                .findActivePlans()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private SubscriptionPlanMobileResponse toResponse(
            SubscriptionPlan plan) {

        SubscriptionPlanMobileResponse response =
                new SubscriptionPlanMobileResponse();

        response.setPlan_id(
                plan.getId()
        );

        response.setName(
                plan.getName()
        );

        response.setPrice(
                plan.getPrice()
        );

        response.setCurrency(
                plan.getCurrency()
        );

        response.setBilling_period(
                plan.getBilling_period()
        );

        response.setAi_messages_limit(
                plan.getAi_messages_limit()
        );

        response.setSpeaking_limit(
                plan.getSpeaking_limit()
        );

        response.setIs_active(
                plan.getIs_active()
        );

        return response;
    }
}