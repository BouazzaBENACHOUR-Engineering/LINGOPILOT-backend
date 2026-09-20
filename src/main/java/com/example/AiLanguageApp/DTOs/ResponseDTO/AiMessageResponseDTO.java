package com.example.AiLanguageApp.DTOs.ResponseDTO;

import java.time.LocalDateTime;

public class AiMessageResponseDTO {
	
	private Long id;
	private Long conversation_id;
	private String sender;
	private String message;
	private LocalDateTime created_at;
	
	public AiMessageResponseDTO() {
	}
	
	public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	
	public Long getConversation_id() {
	    return conversation_id;
	}
	
	public void setConversation_id(Long conversation_id) {
	    this.conversation_id = conversation_id;
	}
	
	public String getSender() {
	    return sender;
	}
	
	public void setSender(String sender) {
	    this.sender = sender;
	}
	
	public String getMessage() {
	    return message;
	}
	
	public void setMessage(String message) {
	    this.message = message;
	}
	
	public LocalDateTime getCreated_at() {
	    return created_at;
	}
	
	public void setCreated_at(LocalDateTime created_at) {
	    this.created_at = created_at;
	}

}
