package com.example.AiLanguageApp.DTOs.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponseDTO {
	
	private Long id;
	private Long user_id;
	private Long subscription_id;
	private BigDecimal amount;
	private String currency;
	private String status;
	private String payment_provider;
	private String transaction_reference;
	private LocalDateTime created_at;
	
	public PaymentResponseDTO() {
	}
	
	public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	
	public Long getUser_id() {
	    return user_id;
	}
	
	public void setUser_id(Long user_id) {
	    this.user_id = user_id;
	}
	
	public Long getSubscription_id() {
	    return subscription_id;
	}
	
	public void setSubscription_id(Long subscription_id) {
	    this.subscription_id = subscription_id;
	}
	
	public BigDecimal getAmount() {
	    return amount;
	}
	
	public void setAmount(BigDecimal amount) {
	    this.amount = amount;
	}
	
	public String getCurrency() {
	    return currency;
	}
	
	public void setCurrency(String currency) {
	    this.currency = currency;
	}
	
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	
	public String getPayment_provider() {
	    return payment_provider;
	}
	
	public void setPayment_provider(String payment_provider) {
	    this.payment_provider = payment_provider;
	}
	
	public String getTransaction_reference() {
	    return transaction_reference;
	}
	
	public void setTransaction_reference(String transaction_reference) {
	    this.transaction_reference = transaction_reference;
	}
	
	public LocalDateTime getCreated_at() {
	    return created_at;
	}
	
	public void setCreated_at(LocalDateTime created_at) {
	    this.created_at = created_at;
	}

}
