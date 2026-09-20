package com.example.AiLanguageApp.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.PaymentRequest;
import com.example.AiLanguageApp.DTO.Response.PaymentResponse;
import com.example.AiLanguageApp.Mapper.PaymentMapper;
import com.example.AiLanguageApp.Repository.PaymentRepository;
import com.example.AiLanguageApp.Repository.SubscriptionRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Service.Interface.PaymentService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Payment;
import com.example.AiLanguageApp.model.Subscription;
import com.example.AiLanguageApp.model.User;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            SubscriptionRepository subscriptionRepository,
            PaymentMapper paymentMapper) {

        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentResponse create(PaymentRequest request) {

        User user =
                userRepository.findById(request.getUser_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User does not exist"
                                )
                        );

        Subscription subscription = null;

        if (request.getSubscription_id() != null) {

            subscription =
                    subscriptionRepository
                            .findById(request.getSubscription_id())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Subscription does not exist"
                                    )
                            );
        }

        Payment payment = new Payment();

        payment.setUser(user);
        payment.setSubscription(subscription);
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setStatus(request.getStatus());
        payment.setPaymentProvider(request.getPaymentProvider());
        payment.setTransactionReference(request.getTransactionReference());
        payment.setCreatedAt(LocalDateTime.now());

        Payment savedPayment =
                paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public Payment save(Payment payment) {

        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment) {

        if (payment.getId() == null ||
                !paymentRepository.existsById(payment.getId())) {

            throw new ResourceNotFoundException(
                    "Payment does not exist"
            );
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment does not exist"
                                )
                        );

        return Optional.of(payment);
    }

    @Override
    public List<Payment> findAll() {

        return paymentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment does not exist"
                                )
                        );

        paymentRepository.delete(payment);
    }
}