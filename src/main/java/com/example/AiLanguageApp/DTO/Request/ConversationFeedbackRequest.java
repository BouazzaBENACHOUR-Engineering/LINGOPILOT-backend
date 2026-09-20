package com.example.AiLanguageApp.DTO.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class ConversationFeedbackRequest {

    @NotNull(message = "message_id is required")
    private Long message_id;

    @DecimalMin(value = "0.00", message = "grammar_score must be at least 0.00")
    @DecimalMax(value = "100.00", message = "grammar_score must not exceed 100.00")
    private BigDecimal grammar_score;

    @DecimalMin(value = "0.00", message = "vocabulary_score must be at least 0.00")
    @DecimalMax(value = "100.00", message = "vocabulary_score must not exceed 100.00")
    private BigDecimal vocabulary_score;

    @DecimalMin(value = "0.00", message = "fluency_score must be at least 0.00")
    @DecimalMax(value = "100.00", message = "fluency_score must not exceed 100.00")
    private BigDecimal fluency_score;

    @DecimalMin(value = "0.00", message = "pronunciation_score must be at least 0.00")
    @DecimalMax(value = "100.00", message = "pronunciation_score must not exceed 100.00")
    private BigDecimal pronunciation_score;

    private String feedback;

    @NotNull(message = "created_at is required")
    private LocalDateTime created_at;

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public BigDecimal getGrammar_score() {
        return grammar_score;
    }

    public void setGrammar_score(BigDecimal grammar_score) {
        this.grammar_score = grammar_score;
    }

    public BigDecimal getVocabulary_score() {
        return vocabulary_score;
    }

    public void setVocabulary_score(BigDecimal vocabulary_score) {
        this.vocabulary_score = vocabulary_score;
    }

    public BigDecimal getFluency_score() {
        return fluency_score;
    }

    public void setFluency_score(BigDecimal fluency_score) {
        this.fluency_score = fluency_score;
    }

    public BigDecimal getPronunciation_score() {
        return pronunciation_score;
    }

    public void setPronunciation_score(BigDecimal pronunciation_score) {
        this.pronunciation_score = pronunciation_score;
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