package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.SubscriptionPlanResponse;
import com.example.AiLanguageApp.model.SubscriptionPlan;

@Component
public class SubscriptionPlanMapper {

    public SubscriptionPlanResponse toResponse(
            SubscriptionPlan subscriptionPlan) {

        if (subscriptionPlan == null) {
            return null;
        }

        SubscriptionPlanResponse response =
                new SubscriptionPlanResponse();

        response.setId(subscriptionPlan.getId());
        response.setName(subscriptionPlan.getName());
        response.setPrice(subscriptionPlan.getPrice());
        response.setCurrency(subscriptionPlan.getCurrency());
        response.setBilling_period(
                subscriptionPlan.getBilling_period()
        );
        response.setAi_messages_limit(
                subscriptionPlan.getAi_messages_limit()
        );
        response.setSpeaking_limit(
                subscriptionPlan.getSpeaking_limit()
        );
        response.setIs_active(
                subscriptionPlan.getIs_active()
        );

        return response;
    }
}