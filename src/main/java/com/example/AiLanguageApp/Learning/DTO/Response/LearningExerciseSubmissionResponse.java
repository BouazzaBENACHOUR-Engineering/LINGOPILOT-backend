package com.example.AiLanguageApp.Learning.DTO.Response;

import java.math.BigDecimal;

public class LearningExerciseSubmissionResponse {
    private Long attempt_id;
    private Long exercise_id;
    private String answer;
    private Boolean is_correct;
    private BigDecimal score;
    private String explanation;
    private String lesson_status;
    private BigDecimal completion_percentage;
    private BigDecimal lesson_score;

    public Long getAttempt_id() { return attempt_id; }
    public void setAttempt_id(Long attempt_id) { this.attempt_id = attempt_id; }
    public Long getExercise_id() { return exercise_id; }
    public void setExercise_id(Long exercise_id) { this.exercise_id = exercise_id; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public Boolean getIs_correct() { return is_correct; }
    public void setIs_correct(Boolean is_correct) { this.is_correct = is_correct; }
    public BigDecimal getScore() { return score; }
    public void setScore(BigDecimal score) { this.score = score; }
    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public String getLesson_status() { return lesson_status; }
    public void setLesson_status(String lesson_status) { this.lesson_status = lesson_status; }
    public BigDecimal getCompletion_percentage() { return completion_percentage; }
    public void setCompletion_percentage(BigDecimal completion_percentage) { this.completion_percentage = completion_percentage; }
    public BigDecimal getLesson_score() { return lesson_score; }
    public void setLesson_score(BigDecimal lesson_score) { this.lesson_score = lesson_score; }
}
