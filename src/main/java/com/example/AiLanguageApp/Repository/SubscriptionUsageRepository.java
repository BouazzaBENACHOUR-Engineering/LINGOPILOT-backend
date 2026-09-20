package com.example.AiLanguageApp.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.SubscriptionUsage;

public interface SubscriptionUsageRepository
        extends JpaRepository<SubscriptionUsage, Long> {

    @Query("""
            SELECT su
            FROM SubscriptionUsage su
            WHERE su.subscription_id.id = :subscriptionId
              AND su.period_started_at <= :now
              AND (
                    su.period_ends_at IS NULL
                    OR su.period_ends_at > :now
                  )
            """)
    Optional<SubscriptionUsage> findCurrentUsage(
            @Param("subscriptionId") Long subscriptionId,
            @Param("now") LocalDateTime now
    );
}