package com.example.AiLanguageApp.AI.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AiChatRequest {

    @NotNull(message = "conversation_id is required")
    private Long conversation_id;

    @NotBlank(message = "message is required")
    private String message;

    public Long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(Long conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}