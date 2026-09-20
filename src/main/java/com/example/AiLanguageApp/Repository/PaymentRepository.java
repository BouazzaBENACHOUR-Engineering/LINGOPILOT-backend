package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}