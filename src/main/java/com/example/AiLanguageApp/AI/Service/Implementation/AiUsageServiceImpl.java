package com.example.AiLanguageApp.AI.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.Service.AiUsageService;
import com.example.AiLanguageApp.Repository.SubscriptionRepository;
import com.example.AiLanguageApp.Repository.SubscriptionUsageRepository;
import com.example.AiLanguageApp.exception.AiUsageLimitExceededException;
import com.example.AiLanguageApp.model.Subscription;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import com.example.AiLanguageApp.model.SubscriptionUsage;

@Service
public class AiUsageServiceImpl
        implements AiUsageService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionUsageRepository subscriptionUsageRepository;

    public AiUsageServiceImpl(
            SubscriptionRepository subscriptionRepository,
            SubscriptionUsageRepository subscriptionUsageRepository) {

        this.subscriptionRepository =
                subscriptionRepository;

        this.subscriptionUsageRepository =
                subscriptionUsageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void checkAiMessageAccess(
            Long userId) {

        LocalDateTime now =
                LocalDateTime.now();

        Subscription subscription =
                resolveActiveSubscription(
                        userId,
                        now
                );

        SubscriptionPlan plan =
                subscription.getPlan();

        if (!Boolean.TRUE.equals(
                plan.getIs_active())) {

        	throw new AiUsageLimitExceededException(
        	        "AI message limit reached"
        	);
        }

        Integer limit =
                plan.getAi_messages_limit();

        if (limit == null) {
            return;
        }

        SubscriptionUsage usage =
                subscriptionUsageRepository
                        .findCurrentUsage(
                                subscription.getId(),
                                now
                        )
                        .orElse(null);

        int used =
                usage == null
                        ? 0
                        : usage.getAi_messages_used();

        if (used >= limit) {

        	throw new AiUsageLimitExceededException(
        	        "AI message limit reached"
        	);
        }
    }

    @Override
    @Transactional
    public void consumeAiMessage(
            Long userId) {

        LocalDateTime now =
                LocalDateTime.now();

        Subscription subscription =
                resolveActiveSubscription(
                        userId,
                        now
                );

        SubscriptionPlan plan =
                subscription.getPlan();

        Integer limit =
                plan.getAi_messages_limit();

        SubscriptionUsage usage =
                subscriptionUsageRepository
                        .findCurrentUsage(
                                subscription.getId(),
                                now
                        )
                        .orElseGet(() ->
                                createUsage(
                                        subscription,
                                        now
                                )
                        );

        int used =
                usage.getAi_messages_used();

        if (limit != null
                && used >= limit) {

            throw new IllegalStateException(
                    "AI message limit reached"
            );
        }

        usage.setAi_messages_used(
                used + 1
        );

        usage.setUpdated_at(
                now
        );

        subscriptionUsageRepository.save(
                usage
        );
    }

    private Subscription resolveActiveSubscription(
            Long userId,
            LocalDateTime now) {

        List<Subscription> subscriptions =
                subscriptionRepository
                        .findActiveSubscriptions(
                                userId,
                                now
                        );

        if (subscriptions.isEmpty()) {

            throw new IllegalStateException(
                    "No active subscription"
            );
        }

        return subscriptions.get(0);
    }

    private SubscriptionUsage createUsage(
            Subscription subscription,
            LocalDateTime now) {

        SubscriptionUsage usage =
                new SubscriptionUsage();

        usage.setSubscription_id(
                subscription
        );

        usage.setAi_messages_used(
                0
        );

        usage.setSpeaking_used(
                0
        );

        usage.setPeriod_started_at(
                subscription.getStartedAt()
        );

        usage.setPeriod_ends_at(
                subscription.getExpiresAt()
        );

        usage.setUpdated_at(
                now
        );

        return subscriptionUsageRepository.save(
                usage
        );
    }
}