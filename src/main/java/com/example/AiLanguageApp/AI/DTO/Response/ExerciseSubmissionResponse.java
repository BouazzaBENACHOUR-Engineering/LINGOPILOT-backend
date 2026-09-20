package com.example.AiLanguageApp.AI.DTO.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExerciseSubmissionResponse {

    private Long attempt_id;
    private Long exercise_id;
    private String exercise_type;
    private String answer;
    private Boolean is_correct;
    private BigDecimal score;
    private LocalDateTime attempted_at;

    private Long lesson_id;
    private String lesson_status;
    private BigDecimal completion_percentage;
    private BigDecimal lesson_score;

    public Long getAttempt_id() {
        return attempt_id;
    }

    public void setAttempt_id(Long attempt_id) {
        this.attempt_id = attempt_id;
    }

    public Long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(String exercise_type) {
        this.exercise_type = exercise_type;
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

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_status() {
        return lesson_status;
    }

    public void setLesson_status(String lesson_status) {
        this.lesson_status = lesson_status;
    }

    public BigDecimal getCompletion_percentage() {
        return completion_percentage;
    }

    public void setCompletion_percentage(
            BigDecimal completion_percentage) {

        this.completion_percentage =
                completion_percentage;
    }

    public BigDecimal getLesson_score() {
        return lesson_score;
    }

    public void setLesson_score(BigDecimal lesson_score) {
        this.lesson_score = lesson_score;
    }
}