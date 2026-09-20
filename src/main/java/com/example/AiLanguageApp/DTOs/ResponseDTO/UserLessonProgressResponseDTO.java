package com.example.AiLanguageApp.DTOs.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserLessonProgressResponseDTO {
	
	private Long id;
	private Long user_id;
	private Long lesson_id;
	private String status;
	private BigDecimal completion_percentage;
	private BigDecimal score;
	private LocalDateTime started_at;
	private LocalDateTime completed_at;
	
	public UserLessonProgressResponseDTO() {
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
	
	public Long getLesson_id() {
	    return lesson_id;
	}
	
	public void setLesson_id(Long lesson_id) {
	    this.lesson_id = lesson_id;
	}
	
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	
	public BigDecimal getCompletion_percentage() {
	    return completion_percentage;
	}
	
	public void setCompletion_percentage(BigDecimal completion_percentage) {
	    this.completion_percentage = completion_percentage;
	}
	
	public BigDecimal getScore() {
	    return score;
	}
	
	public void setScore(BigDecimal score) {
	    this.score = score;
	}
	
	public LocalDateTime getStarted_at() {
	    return started_at;
	}
	
	public void setStarted_at(LocalDateTime started_at) {
	    this.started_at = started_at;
	}
	
	public LocalDateTime getCompleted_at() {
	    return completed_at;
	}
	
	public void setCompleted_at(LocalDateTime completed_at) {
	    this.completed_at = completed_at;
	}

}
