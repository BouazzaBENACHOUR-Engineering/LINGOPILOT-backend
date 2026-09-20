package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.SubscriptionPlanRequest;
import com.example.AiLanguageApp.DTO.Response.SubscriptionPlanResponse;
import com.example.AiLanguageApp.Mapper.SubscriptionPlanMapper;
import com.example.AiLanguageApp.Repository.SubscriptionPlanRepository;
import com.example.AiLanguageApp.Service.Interface.SubscriptionPlanService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.SubscriptionPlan;

@Service
public class SubscriptionPlanServiceImpl
        implements SubscriptionPlanService {

    private final SubscriptionPlanRepository
            subscriptionPlanRepository;

    private final SubscriptionPlanMapper
            subscriptionPlanMapper;

    public SubscriptionPlanServiceImpl(
            SubscriptionPlanRepository subscriptionPlanRepository,
            SubscriptionPlanMapper subscriptionPlanMapper) {

        this.subscriptionPlanRepository =
                subscriptionPlanRepository;

        this.subscriptionPlanMapper =
                subscriptionPlanMapper;
    }

    @Override
    public SubscriptionPlanResponse create(
            SubscriptionPlanRequest request) {

        SubscriptionPlan subscriptionPlan =
                new SubscriptionPlan();

        subscriptionPlan.setName(
                request.getName()
        );

        subscriptionPlan.setPrice(
                request.getPrice()
        );

        subscriptionPlan.setCurrency(
                request.getCurrency()
        );

        subscriptionPlan.setBilling_period(
                request.getBilling_period()
        );

        subscriptionPlan.setAi_messages_limit(
                request.getAi_messages_limit()
        );

        subscriptionPlan.setSpeaking_limit(
                request.getSpeaking_limit()
        );

        subscriptionPlan.setIs_active(
                request.getIs_active()
        );

        SubscriptionPlan savedSubscriptionPlan =
                subscriptionPlanRepository.save(
                        subscriptionPlan
                );

        return subscriptionPlanMapper.toResponse(
                savedSubscriptionPlan
        );
    }

    @Override
    public SubscriptionPlan save(
            SubscriptionPlan subscriptionPlan) {

        return subscriptionPlanRepository.save(
                subscriptionPlan
        );
    }

    @Override
    public SubscriptionPlan update(
            SubscriptionPlan subscriptionPlan) {

        if (subscriptionPlan.getId() == null ||
                !subscriptionPlanRepository.existsById(
                        subscriptionPlan.getId())) {

            throw new ResourceNotFoundException(
                    "Subscription plan does not exist"
            );
        }

        return subscriptionPlanRepository.save(
                subscriptionPlan
        );
    }

    @Override
    public Optional<SubscriptionPlan> findById(Long id) {

        SubscriptionPlan subscriptionPlan =
                subscriptionPlanRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subscription plan does not exist"
                                )
                        );

        return Optional.of(subscriptionPlan);
    }

    @Override
    public List<SubscriptionPlan> findAll() {

        return subscriptionPlanRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        SubscriptionPlan subscriptionPlan =
                subscriptionPlanRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subscription plan does not exist"
                                )
                        );

        subscriptionPlanRepository.delete(
                subscriptionPlan
        );
    }
}