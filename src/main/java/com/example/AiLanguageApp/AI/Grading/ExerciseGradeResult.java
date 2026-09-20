package com.example.AiLanguageApp.AI.Grading;

import java.math.BigDecimal;

public class ExerciseGradeResult {

    private Boolean correct;
    private BigDecimal score;
    private String feedback;

    public ExerciseGradeResult() {
    }

    public ExerciseGradeResult(
            Boolean correct,
            BigDecimal score,
            String feedback) {

        this.correct = correct;
        this.score = score;
        this.feedback = feedback;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
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