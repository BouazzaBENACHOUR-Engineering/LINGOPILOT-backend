package com.example.AiLanguageApp.DTOs.ResponseDTO;

import java.time.LocalDateTime;

public class AiConversationResponseDTO {

	private Long id;
	private Long user_id;
	private Long language_id;
	private Long level_id;
	private String topic;
	private LocalDateTime started_at;
	private LocalDateTime ended_at;
	
	public AiConversationResponseDTO() {
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
	
	public Long getLanguage_id() {
	    return language_id;
	}
	
	public void setLanguage_id(Long language_id) {
	    this.language_id = language_id;
	}
	
	public Long getLevel_id() {
	    return level_id;
	}
	
	public void setLevel_id(Long level_id) {
	    this.level_id = level_id;
	}
	
	public String getTopic() {
	    return topic;
	}
	
	public void setTopic(String topic) {
	    this.topic = topic;
	}
	
	public LocalDateTime getStarted_at() {
	    return started_at;
	}
	
	public void setStarted_at(LocalDateTime started_at) {
	    this.started_at = started_at;
	}
	
	public LocalDateTime getEnded_at() {
	    return ended_at;
	}
	
	public void setEnded_at(LocalDateTime ended_at) {
	    this.ended_at = ended_at;
	}


}
