package com.example.AiLanguageApp.DTOs.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AiAssessmentResponseDTO {
	
	private Long id;
	private Long user_id;
	private Long conversation_id;
	private Long skill_id;
	private Long level_id;
	private BigDecimal score;
	private String feedback;
	private LocalDateTime created_at;
	
	public AiAssessmentResponseDTO() {
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
	
	public Long getConversation_id() {
	    return conversation_id;
	}
	
	public void setConversation_id(Long conversation_id) {
	    this.conversation_id = conversation_id;
	}
	
	public Long getSkill_id() {
	    return skill_id;
	}
	
	public void setSkill_id(Long skill_id) {
	    this.skill_id = skill_id;
	}
	
	public Long getLevel_id() {
	    return level_id;
	}
	
	public void setLevel_id(Long level_id) {
	    this.level_id = level_id;
	}
	
	public BigDecimal getScore() {
	    return score;
	}
	
	public void setScore(BigDecimal score) {
	    this.score = score;
	}
	
	public String getFeedback() {
	    return feedback;
	}
	
	public void setFeedback(String feedback) {
	    this.feedback = feedback;
	}
	
	public LocalDateTime getCreated_at() {
	    return created_at;
	}
	
	public void setCreated_at(LocalDateTime created_at) {
	    this.created_at = created_at;
	}

}
