package com.example.AiLanguageApp.Payment.Service.Implementation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.Payment.DTO.Response.PaymentCheckoutResponse;
import com.example.AiLanguageApp.Payment.PaymentResult;
import com.example.AiLanguageApp.Payment.Service.Interfaces.PaymentCheckoutService;
import com.example.AiLanguageApp.Payment.Service.Interfaces.PaymentProviderService;
import com.example.AiLanguageApp.Payment.Service.Interfaces.SubscriptionActivationService;
import com.example.AiLanguageApp.Repository.PaymentRepository;
import com.example.AiLanguageApp.Repository.SubscriptionPlanRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Payment;
import com.example.AiLanguageApp.model.Subscription;
import com.example.AiLanguageApp.model.SubscriptionPlan;
import com.example.AiLanguageApp.model.User;

@Service
public class PaymentCheckoutServiceImpl
        implements PaymentCheckoutService {

    private final UserRepository userRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentProviderService paymentProviderService;
    private final SubscriptionActivationService subscriptionActivationService;

    public PaymentCheckoutServiceImpl(
            UserRepository userRepository,
            SubscriptionPlanRepository subscriptionPlanRepository,
            PaymentRepository paymentRepository,
            PaymentProviderService paymentProviderService,
            SubscriptionActivationService subscriptionActivationService) {

        this.userRepository =
                userRepository;

        this.subscriptionPlanRepository =
                subscriptionPlanRepository;

        this.paymentRepository =
                paymentRepository;

        this.paymentProviderService =
                paymentProviderService;

        this.subscriptionActivationService =
                subscriptionActivationService;
    }

    @Override
    @Transactional
    public PaymentCheckoutResponse checkout(
            Long userId,
            Long planId) {

        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User does not exist"
                                )
                        );

        SubscriptionPlan plan =
                subscriptionPlanRepository
                        .findById(planId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subscription plan does not exist"
                                )
                        );

        if (!Boolean.TRUE.equals(
                plan.getIs_active())) {

            throw new IllegalStateException(
                    "Subscription plan is not active"
            );
        }

        PaymentResult paymentResult =
                paymentProviderService
                        .processPayment(
                                user,
                                plan
                        );

        Payment payment =
                new Payment();

        payment.setUser(
                user
        );

        payment.setAmount(
                plan.getPrice()
        );

        payment.setCurrency(
                plan.getCurrency()
        );

        payment.setStatus(
                paymentResult.getStatus()
        );

        payment.setPaymentProvider(
                paymentResult.getProvider()
        );

        payment.setTransactionReference(
                paymentResult.getTransaction_reference()
        );

        payment.setCreatedAt(
                LocalDateTime.now()
        );

        if (!Boolean.TRUE.equals(
                paymentResult.getSuccess())) {

            Payment savedPayment =
                    paymentRepository.save(
                            payment
                    );

            return buildFailedResponse(
                    savedPayment,
                    plan,
                    paymentResult
            );
        }

        Subscription subscription =
                subscriptionActivationService
                        .activate(
                                user,
                                plan
                        );

        payment.setSubscription(
                subscription
        );

        Payment savedPayment =
                paymentRepository.save(
                        payment
                );

        return buildSuccessfulResponse(
                savedPayment,
                subscription,
                plan,
                paymentResult
        );
    }

    private PaymentCheckoutResponse buildSuccessfulResponse(
            Payment payment,
            Subscription subscription,
            SubscriptionPlan plan,
            PaymentResult paymentResult) {

        PaymentCheckoutResponse response =
                new PaymentCheckoutResponse();

        response.setPayment_id(
                payment.getId()
        );

        response.setSubscription_id(
                subscription.getId()
        );

        response.setPlan_id(
                plan.getId()
        );

        response.setPlan_name(
                plan.getName()
        );

        response.setAmount(
                payment.getAmount()
        );

        response.setCurrency(
                payment.getCurrency()
        );

        response.setPayment_status(
                payment.getStatus()
        );

        response.setPayment_provider(
                payment.getPaymentProvider()
        );

        response.setTransaction_reference(
                payment.getTransactionReference()
        );

        response.setSubscription_status(
                subscription.getStatus()
        );

        response.setSubscription_started_at(
                subscription.getStartedAt()
        );

        response.setSubscription_expires_at(
                subscription.getExpiresAt()
        );

        response.setMessage(
                paymentResult.getMessage()
        );

        return response;
    }

    private PaymentCheckoutResponse buildFailedResponse(
            Payment payment,
            SubscriptionPlan plan,
            PaymentResult paymentResult) {

        PaymentCheckoutResponse response =
                new PaymentCheckoutResponse();

        response.setPayment_id(
                payment.getId()
        );

        response.setSubscription_id(
                null
        );

        response.setPlan_id(
                plan.getId()
        );

        response.setPlan_name(
                plan.getName()
        );

        response.setAmount(
                payment.getAmount()
        );

        response.setCurrency(
                payment.getCurrency()
        );

        response.setPayment_status(
                payment.getStatus()
        );

        response.setPayment_provider(
                payment.getPaymentProvider()
        );

        response.setTransaction_reference(
                payment.getTransactionReference()
        );

        response.setSubscription_status(
                null
        );

        response.setSubscription_started_at(
                null
        );

        response.setSubscription_expires_at(
                null
        );

        response.setMessage(
                paymentResult.getMessage()
        );

        return response;
    }
}