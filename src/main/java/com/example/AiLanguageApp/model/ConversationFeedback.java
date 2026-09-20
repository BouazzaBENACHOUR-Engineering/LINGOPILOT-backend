package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "conversation_feedback",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_feedback_message",
            columnNames = {"message_id"}
        )
    }
)
public class ConversationFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "message_id",
        nullable = false,
        unique = true
    )
    private AiMessage message_id;

    @Column(name = "grammar_score", precision = 5, scale = 2)
    private BigDecimal grammar_score;

    @Column(name = "vocabulary_score", precision = 5, scale = 2)
    private BigDecimal vocabulary_score;

    @Column(name = "fluency_score", precision = 5, scale = 2)
    private BigDecimal fluency_score;

    @Column(name = "pronunciation_score", precision = 5, scale = 2)
    private BigDecimal pronunciation_score;

    @Column(name = "feedback", columnDefinition = "nvarchar(max)")
    private String feedback;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AiMessage getMessage_id() {
        return message_id;
    }

    public void setMessage_id(AiMessage message_id) {
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