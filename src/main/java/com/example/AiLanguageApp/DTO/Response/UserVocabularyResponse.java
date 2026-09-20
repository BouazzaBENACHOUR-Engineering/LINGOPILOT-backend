package com.example.AiLanguageApp.DTO.Response;

import java.time.LocalDateTime;

public class UserVocabularyResponse {

    private Long id;
    private Long user_id;
    private Long vocabulary_id;
    private Integer mastery_level;
    private Integer correct_count;
    private Integer wrong_count;
    private LocalDateTime last_reviewed_at;
    private LocalDateTime next_review_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getVocabulary_id() {
        return vocabulary_id;
    }

    public void setVocabulary_id(Long vocabulary_id) {
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