package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NotificationRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotBlank(message = "title is required")
    @Size(max = 255, message = "title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "message is required")
    private String message;

    @NotBlank(message = "type is required")
    @Size(max = 50, message = "type must not exceed 50 characters")
    private String type;

    @NotNull(message = "is_read is required")
    private Boolean is_read;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }
}