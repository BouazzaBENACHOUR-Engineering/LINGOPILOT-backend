package com.example.AiLanguageApp.Payment.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.Payment.Service.Interfaces.SubscriptionActivationService;
import com.example.AiLanguageApp.Repository.SubscriptionRepository;
import com.example.AiLanguageApp.model.Subscription;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import com.example.AiLanguageApp.model.User;

@Service
public class SubscriptionActivationServiceImpl
        implements SubscriptionActivationService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionActivationServiceImpl(
            SubscriptionRepository subscriptionRepository) {

        this.subscriptionRepository =
                subscriptionRepository;
    }

    @Override
    @Transactional
    public Subscription activate(
            User user,
            SubscriptionPlan plan) {

        LocalDateTime now =
                LocalDateTime.now();

        closeExistingActiveSubscriptions(
                user.getId(),
                now
        );

        Subscription subscription =
                new Subscription();

        subscription.setUser(
                user
        );

        subscription.setPlan(
                plan
        );

        subscription.setStatus(
                "ACTIVE"
        );

        subscription.setStartedAt(
                now
        );

        subscription.setExpiresAt(
                calculateExpiration(
                        now,
                        plan.getBilling_period()
                )
        );

        subscription.setAutoRenew(
                false
        );

        return subscriptionRepository.save(
                subscription
        );
    }

    private void closeExistingActiveSubscriptions(
            Long userId,
            LocalDateTime now) {

        List<Subscription> activeSubscriptions =
                subscriptionRepository
                        .findActiveSubscriptions(
                                userId,
                                now
                        );

        for (Subscription subscription : activeSubscriptions) {

            subscription.setStatus(
                    "EXPIRED"
            );

            subscription.setExpiresAt(
                    now
            );

            subscription.setAutoRenew(
                    false
            );

            subscriptionRepository.save(
                    subscription
            );
        }
    }

    private LocalDateTime calculateExpiration(
            LocalDateTime startedAt,
            String billingPeriod) {

        if (billingPeriod == null) {

            throw new IllegalArgumentException(
                    "Billing period cannot be null"
            );
        }

        return switch (billingPeriod.toUpperCase()) {

            case "WEEKLY" ->
                    startedAt.plusWeeks(1);

            case "MONTHLY" ->
                    startedAt.plusMonths(1);

            case "YEARLY" ->
                    startedAt.plusYears(1);

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported billing period: "
                                    + billingPeriod
                    );
        };
    }
}