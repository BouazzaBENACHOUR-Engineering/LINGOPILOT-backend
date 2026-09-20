package com.example.AiLanguageApp.DTOs.ResponseDTO;

public class CourseResponseDTO {
	
	private Long id;
	private Long language_id;
	private Long level_id;
	private String title;
	private String description;
	
	public CourseResponseDTO() {
	}
	
	public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
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
	
	public String getTitle() {
	    return title;
	}
	
	public void setTitle(String title) {
	    this.title = title;
	}
	
	public String getDescription() {
	    return description;
	}
	
	public void setDescription(String description) {
	    this.description = description;
	}

}
