package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.SubscriptionResponse;
import com.example.AiLanguageApp.model.Subscription;

@Component
public class SubscriptionMapper {

    public SubscriptionResponse toResponse(
            Subscription subscription) {

        if (subscription == null) {
            return null;
        }

        SubscriptionResponse response =
                new SubscriptionResponse();

        response.setId(subscription.getId());

        if (subscription.getUser() != null) {
            response.setUser_id(
                    subscription.getUser().getId()
            );
        }

        if (subscription.getPlan() != null) {
            response.setPlan_id(
                    subscription.getPlan().getId()
            );
        }

        response.setStatus(subscription.getStatus());
        response.setStartedAt(subscription.getStartedAt());
        response.setExpiresAt(subscription.getExpiresAt());
        response.setAutoRenew(subscription.getAutoRenew());

        return response;
    }
}