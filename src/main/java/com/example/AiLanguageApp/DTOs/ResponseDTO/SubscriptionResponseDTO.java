package com.example.AiLanguageApp.DTOs.ResponseDTO;

import java.time.LocalDateTime;

public class SubscriptionResponseDTO {
	
	private Long id;
	private Long user_id;
	private Long plan_id;
	private String status;
	private LocalDateTime started_at;
	private LocalDateTime ends_at;
	private LocalDateTime created_at;
	
	public SubscriptionResponseDTO() {
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
	
	public Long getPlan_id() {
	    return plan_id;
	}
	
	public void setPlan_id(Long plan_id) {
	    this.plan_id = plan_id;
	}
	
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	
	public LocalDateTime getStarted_at() {
	    return started_at;
	}
	
	public void setStarted_at(LocalDateTime started_at) {
	    this.started_at = started_at;
	}
	
	public LocalDateTime getEnds_at() {
	    return ends_at;
	}
	
	public void setEnds_at(LocalDateTime ends_at) {
	    this.ends_at = ends_at;
	}
	
	public LocalDateTime getCreated_at() {
	    return created_at;
	}
	
	public void setCreated_at(LocalDateTime created_at) {
	    this.created_at = created_at;
	}

}
