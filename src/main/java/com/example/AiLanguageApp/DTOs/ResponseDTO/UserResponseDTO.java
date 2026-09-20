package com.example.AiLanguageApp.DTOs.ResponseDTO;

import java.time.LocalDateTime;

public class UserResponseDTO {

	private Long id;
	private String email;
	private String first_name;
	private String last_name;
	private Long native_language_id;
	private String status;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public UserResponseDTO() {
	}
	
	public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	
	public String getEmail() {
	    return email;
	}
	
	public void setEmail(String email) {
	    this.email = email;
	}
	
	public String getFirst_name() {
	    return first_name;
	}
	
	public void setFirst_name(String first_name) {
	    this.first_name = first_name;
	}
	
	public String getLast_name() {
	    return last_name;
	}
	
	public void setLast_name(String last_name) {
	    this.last_name = last_name;
	}
	
	public Long getNative_language_id() {
	    return native_language_id;
	}
	
	public void setNative_language_id(Long native_language_id) {
	    this.native_language_id = native_language_id;
	}
	
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	
	public LocalDateTime getCreated_at() {
	    return created_at;
	}
	
	public void setCreated_at(LocalDateTime created_at) {
	    this.created_at = created_at;
	}
	
	public LocalDateTime getUpdated_at() {
	    return updated_at;
	}
	
	public void setUpdated_at(LocalDateTime updated_at) {
	    this.updated_at = updated_at;
	}

}
