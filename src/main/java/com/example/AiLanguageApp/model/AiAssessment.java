package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_assessments")
public class AiAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private AiConversation conversation_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level_id;

    @Column(name = "score", precision = 5, scale = 2, nullable = false)
    private BigDecimal score;

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

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public AiConversation getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(AiConversation conversation_id) {
        this.conversation_id = conversation_id;
    }

    public Skill getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Skill skill_id) {
        this.skill_id = skill_id;
    }

    public Level getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Level level_id) {
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}