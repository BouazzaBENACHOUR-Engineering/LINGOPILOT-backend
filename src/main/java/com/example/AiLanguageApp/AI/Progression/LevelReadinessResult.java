package com.example.AiLanguageApp.AI.Progression;

import java.math.BigDecimal;

public class LevelReadinessResult {

    private Long user_id;

    private Long language_id;

    private Long current_level_id;

    private String current_level_code;

    private Long next_level_id;

    private String next_level_code;

    private BigDecimal grammar_score;

    private BigDecimal vocabulary_score;

    private BigDecimal fluency_score;

    private BigDecimal average_skill_score;

    private Long completed_lessons;

    private BigDecimal recent_lesson_average;

    private Boolean ready_for_promotion;

    private String reason;

    public LevelReadinessResult() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Long language_id) {
        this.language_id = language_id;
    }

    public Long getCurrent_level_id() {
        return current_level_id;
    }

    public void setCurrent_level_id(Long current_level_id) {
        this.current_level_id = current_level_id;
    }

    public String getCurrent_level_code() {
        return current_level_code;
    }

    public void setCurrent_level_code(String current_level_code) {
        this.current_level_code = current_level_code;
    }

    public Long getNext_level_id() {
        return next_level_id;
    }

    public void setNext_level_id(Long next_level_id) {
        this.next_level_id = next_level_id;
    }

    public String getNext_level_code() {
        return next_level_code;
    }

    public void setNext_level_code(String next_level_code) {
        this.next_level_code = next_level_code;
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

    public BigDecimal getAverage_skill_score() {
        return average_skill_score;
    }

    public void setAverage_skill_score(BigDecimal average_skill_score) {
        this.average_skill_score = average_skill_score;
    }

    public Long getCompleted_lessons() {
        return completed_lessons;
    }

    public void setCompleted_lessons(Long completed_lessons) {
        this.completed_lessons = completed_lessons;
    }

    public BigDecimal getRecent_lesson_average() {
        return recent_lesson_average;
    }

    public void setRecent_lesson_average(BigDecimal recent_lesson_average) {
        this.recent_lesson_average = recent_lesson_average;
    }

    public Boolean getReady_for_promotion() {
        return ready_for_promotion;
    }

    public void setReady_for_promotion(Boolean ready_for_promotion) {
        this.ready_for_promotion = ready_for_promotion;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}