package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.SubscriptionRequest;
import com.example.AiLanguageApp.DTO.Response.SubscriptionResponse;
import com.example.AiLanguageApp.Repository.SubscriptionRepository;
import com.example.AiLanguageApp.Service.Interface.SubscriptionService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Subscription;

@Service
public class SubscriptionServiceImpl
        implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(
            SubscriptionRepository subscriptionRepository) {

        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public SubscriptionResponse create(
            SubscriptionRequest request) {

        Subscription subscription = new Subscription();

        Subscription savedSubscription =
                subscriptionRepository.save(subscription);

        SubscriptionResponse response =
                new SubscriptionResponse();

        response.setId(savedSubscription.getId());

        return response;
    }

    @Override
    public Subscription save(
            Subscription subscription) {

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription update(
            Subscription subscription) {

        if (subscription.getId() == null ||
                !subscriptionRepository.existsById(
                        subscription.getId())) {

            throw new ResourceNotFoundException(
                    "Subscription does not exist"
            );
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Optional<Subscription> findById(Long id) {

        Subscription subscription =
                subscriptionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subscription does not exist"
                                )
                        );

        return Optional.of(subscription);
    }

    @Override
    public List<Subscription> findAll() {

        return subscriptionRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Subscription subscription =
                subscriptionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subscription does not exist"
                                )
                        );

        subscriptionRepository.delete(subscription);
    }
}