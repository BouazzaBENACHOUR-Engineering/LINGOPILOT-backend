package com.example.AiLanguageApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.AiLanguageApp.model.SubscriptionPlan;

public interface SubscriptionPlanRepository
        extends JpaRepository<SubscriptionPlan, Long> {

    @Query("""
            SELECT sp
            FROM SubscriptionPlan sp
            WHERE sp.is_active = true
            ORDER BY sp.price ASC
            """)
    List<SubscriptionPlan> findActivePlans();
}