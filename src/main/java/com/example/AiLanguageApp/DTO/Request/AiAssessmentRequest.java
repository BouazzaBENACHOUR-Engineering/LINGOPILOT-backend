package com.example.AiLanguageApp.DTO.Request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AiAssessmentRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "conversation_id is required")
    private Long conversation_id;

    @NotNull(message = "skill_id is required")
    private Long skill_id;

    private Long level_id;

    @NotNull(message = "score is required")
    @DecimalMin(value = "0.00", message = "score must be at least 0.00")
    @DecimalMax(value = "100.00", message = "score must not exceed 100.00")
    private BigDecimal score;

    @Size(max = 4000, message = "feedback must not exceed 4000 characters")
    private String feedback;

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
}