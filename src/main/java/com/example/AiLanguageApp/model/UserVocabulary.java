package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_vocabulary",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_user_vocabulary",
            columnNames = {"user_id", "vocabulary_id"}
        )
    }
)
public class UserVocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vocabulary_id", nullable = false)
    private Vocabulary vocabulary_id;

    @Column(name = "mastery_level", nullable = false)
    private Integer mastery_level;

    @Column(name = "correct_count", nullable = false)
    private Integer correct_count;

    @Column(name = "wrong_count", nullable = false)
    private Integer wrong_count;

    @Column(name = "last_reviewed_at")
    private LocalDateTime last_reviewed_at;

    @Column(name = "next_review_at")
    private LocalDateTime next_review_at;


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

    public Vocabulary getVocabulary_id() {
        return vocabulary_id;
    }

    public void setVocabulary_id(Vocabulary vocabulary_id) {
        this.vocabulary_id = vocabulary_id;
    }

    public Integer getMastery_level() {
        return mastery_level;
    }

    public void setMastery_level(Integer mastery_level) {
        this.mastery_level = mastery_level;
    }

    public Integer getCorrect_count() {
        return correct_count;
    }

    public void setCorrect_count(Integer correct_count) {
        this.correct_count = correct_count;
    }

    public Integer getWrong_count() {
        return wrong_count;
    }

    public void setWrong_count(Integer wrong_count) {
        this.wrong_count = wrong_count;
    }

    public LocalDateTime getLast_reviewed_at() {
        return last_reviewed_at;
    }

    public void setLast_reviewed_at(LocalDateTime last_reviewed_at) {
        this.last_reviewed_at = last_reviewed_at;
    }

    public LocalDateTime getNext_review_at() {
        return next_review_at;
    }

    public void setNext_review_at(LocalDateTime next_review_at) {
        this.next_review_at = next_review_at;
    }
}