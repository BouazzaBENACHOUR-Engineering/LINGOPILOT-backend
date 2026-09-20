package com.example.AiLanguageApp.DTO.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AiMessageRequest {

    @NotNull(message = "conversation_id is required")
    private Long conversation_id;

    @NotBlank(message = "role is required")
    @Size(max = 20, message = "role must not exceed 20 characters")
    private String role;

    @NotBlank(message = "content is required")
    private String content;

    @NotNull(message = "created_at is required")
    private LocalDateTime created_at;

    public Long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(Long conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}