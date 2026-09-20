package com.example.AiLanguageApp.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.Subscription;

public interface SubscriptionRepository
        extends JpaRepository<Subscription, Long> {

    @Query("""
            SELECT s
            FROM Subscription s
            JOIN FETCH s.plan
            WHERE s.user.id = :userId
              AND UPPER(s.status) = 'ACTIVE'
              AND s.startedAt <= :now
              AND (
                    s.expiresAt IS NULL
                    OR s.expiresAt > :now
                  )
            ORDER BY s.startedAt DESC
            """)
    List<Subscription> findActiveSubscriptions(
            @Param("userId") Long userId,
            @Param("now") LocalDateTime now
    );
}