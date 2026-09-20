package com.example.AiLanguageApp.Monetization.Service;

import com.example.AiLanguageApp.Monetization.DTO.Response.MonetizationStatusResponse;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.model.Subscription;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonetizationServiceImpl implements MonetizationService {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public MonetizationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public MonetizationStatusResponse getStatus(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist");
        }

        SubscriptionPlan plan = resolvePlan(userId);

        Long used = entityManager.createQuery(
                        "select count(c) from AiConversation c where c.user_id.id = :userId",
                        Long.class)
                .setParameter("userId", userId)
                .getSingleResult();

        boolean unlimited = plan.getConversation_limit() == null;
        long remaining = unlimited
                ? Long.MAX_VALUE
                : Math.max(0L, plan.getConversation_limit().longValue() - used);

        MonetizationStatusResponse response = new MonetizationStatusResponse();
        response.setUser_id(userId);
        response.setPlan_id(plan.getId());
        response.setPlan_name(plan.getName());
        response.setPrice(plan.getPrice());
        response.setCurrency(plan.getCurrency());
        response.setBilling_period(plan.getBilling_period());
        response.setConversation_limit(plan.getConversation_limit());
        response.setConversations_used(used);
        response.setConversations_remaining(unlimited ? null : remaining);
        response.setUnlimited_conversations(unlimited);
        response.setAds_enabled(Boolean.TRUE.equals(plan.getAds_enabled()));
        response.setCan_start_ai_conversation(unlimited || remaining > 0);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public void assertCanStartAiConversation(Long userId) {
        MonetizationStatusResponse status = getStatus(userId);
        if (!Boolean.TRUE.equals(status.getCan_start_ai_conversation())) {
            throw new IllegalStateException("Free plan limit reached. Upgrade to continue AI conversations.");
        }
    }

    private SubscriptionPlan resolvePlan(Long userId) {

        List<Subscription> activeSubscriptions = entityManager.createQuery(
                        "select s from Subscription s join fetch s.plan " +
                                "where s.user.id = :userId " +
                                "and upper(s.status) = 'ACTIVE' " +
                                "and (s.expiresAt is null or s.expiresAt > :now) " +
                                "order by s.startedAt desc",
                        Subscription.class)
                .setParameter("userId", userId)
                .setParameter("now", LocalDateTime.now())
                .setMaxResults(1)
                .getResultList();

        if (!activeSubscriptions.isEmpty()) {
            SubscriptionPlan paidPlan = activeSubscriptions.get(0).getPlan();
            if (Boolean.TRUE.equals(paidPlan.getIs_active())) {
                return paidPlan;
            }
        }

        List<SubscriptionPlan> freePlans = entityManager.createQuery(
                        "select p from SubscriptionPlan p where upper(p.name) = 'FREE' and p.is_active = true",
                        SubscriptionPlan.class)
                .setMaxResults(1)
                .getResultList();

        if (freePlans.isEmpty()) {
            throw new IllegalStateException("FREE subscription plan is not configured");
        }

        return freePlans.get(0);
    }
}
