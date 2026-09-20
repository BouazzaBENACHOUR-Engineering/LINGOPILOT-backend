package com.example.AiLanguageApp.AI.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExerciseSubmissionRequest {

    @NotNull(message = "conversation_id is required")
    private Long conversation_id;

    @NotBlank(message = "answer is required")
    private String answer;

    public Long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(Long conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}