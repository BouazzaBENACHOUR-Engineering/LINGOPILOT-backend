package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AiMessageRequestDTO {

    @NotNull(message = "Conversation ID is required")
    private Long conversation_id;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Content is required")
    private String content;

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
}