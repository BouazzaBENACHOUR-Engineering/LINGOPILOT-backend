package com.example.AiLanguageApp.Payment.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.Payment.DTO.Response.SubscriptionStatusResponse;
import com.example.AiLanguageApp.Payment.Service.Interfaces.SubscriptionStatusService;
import com.example.AiLanguageApp.Repository.SubscriptionRepository;
import com.example.AiLanguageApp.Repository.SubscriptionUsageRepository;
import com.example.AiLanguageApp.model.Subscription;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import com.example.AiLanguageApp.model.SubscriptionUsage;

@Service
public class SubscriptionStatusServiceImpl
        implements SubscriptionStatusService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionUsageRepository subscriptionUsageRepository;

    public SubscriptionStatusServiceImpl(
            SubscriptionRepository subscriptionRepository,
            SubscriptionUsageRepository subscriptionUsageRepository) {

        this.subscriptionRepository =
                subscriptionRepository;

        this.subscriptionUsageRepository =
                subscriptionUsageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionStatusResponse getStatus(
            Long userId) {

        LocalDateTime now =
                LocalDateTime.now();

        List<Subscription> subscriptions =
                subscriptionRepository
                        .findActiveSubscriptions(
                                userId,
                                now
                        );

        if (subscriptions.isEmpty()) {

            return buildNoSubscriptionResponse();
        }

        Subscription subscription =
                subscriptions.get(0);

        SubscriptionPlan plan =
                subscription.getPlan();

        SubscriptionUsage usage =
                subscriptionUsageRepository
                        .findCurrentUsage(
                                subscription.getId(),
                                now
                        )
                        .orElse(null);

        int aiUsed =
                usage != null
                        ? usage.getAi_messages_used()
                        : 0;

        int speakingUsed =
                usage != null
                        ? usage.getSpeaking_used()
                        : 0;

        Integer aiLimit =
                plan.getAi_messages_limit();

        Integer speakingLimit =
                plan.getSpeaking_limit();

        Integer aiRemaining =
                aiLimit == null
                        ? null
                        : Math.max(
                                aiLimit - aiUsed,
                                0
                        );

        Integer speakingRemaining =
                speakingLimit == null
                        ? null
                        : Math.max(
                                speakingLimit - speakingUsed,
                                0
                        );

        boolean aiAllowed =
                aiLimit == null
                        || aiUsed < aiLimit;

        boolean speakingAllowed =
                speakingLimit == null
                        || speakingUsed < speakingLimit;

        SubscriptionStatusResponse response =
                new SubscriptionStatusResponse();

        response.setSubscription_id(
                subscription.getId()
        );

        response.setPlan_id(
                plan.getId()
        );

        response.setPlan_name(
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

        response.setSubscription_status(
                subscription.getStatus()
        );

        response.setStarted_at(
                subscription.getStartedAt()
        );

        response.setExpires_at(
                subscription.getExpiresAt()
        );

        response.setAuto_renew(
                subscription.getAutoRenew()
        );

        response.setAi_messages_limit(
                aiLimit
        );

        response.setAi_messages_used(
                aiUsed
        );

        response.setAi_messages_remaining(
                aiRemaining
        );

        response.setSpeaking_limit(
                speakingLimit
        );

        response.setSpeaking_used(
                speakingUsed
        );

        response.setSpeaking_remaining(
                speakingRemaining
        );

        response.setAi_access_allowed(
                aiAllowed
        );

        response.setSpeaking_access_allowed(
                speakingAllowed
        );

        response.setUpgrade_required(
                !aiAllowed
        );

        return response;
    }

    private SubscriptionStatusResponse buildNoSubscriptionResponse() {

        SubscriptionStatusResponse response =
                new SubscriptionStatusResponse();

        response.setSubscription_status(
                "NONE"
        );

        response.setAi_messages_used(
                0
        );

        response.setSpeaking_used(
                0
        );

        response.setAi_access_allowed(
                false
        );

        response.setSpeaking_access_allowed(
                false
        );

        response.setUpgrade_required(
                true
        );

        return response;
    }
}