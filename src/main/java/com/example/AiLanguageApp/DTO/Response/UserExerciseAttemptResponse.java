package com.example.AiLanguageApp.DTO.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserExerciseAttemptResponse {

    private Long id;
    private Long user_id;
    private Long exercise_id;
    private String answer;
    private Boolean is_correct;
    private BigDecimal score;
    private LocalDateTime attempted_at;

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

    public Long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public LocalDateTime getAttempted_at() {
        return attempted_at;
    }

    public void setAttempted_at(LocalDateTime attempted_at) {
        this.attempted_at = attempted_at;
    }
}