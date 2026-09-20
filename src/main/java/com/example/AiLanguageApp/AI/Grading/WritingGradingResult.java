package com.example.AiLanguageApp.AI.Grading;

import java.math.BigDecimal;

public class WritingGradingResult {

    private BigDecimal score_percentage;

    private Boolean is_correct;

    private String feedback;

    public BigDecimal getScore_percentage() {
        return score_percentage;
    }

    public void setScore_percentage(BigDecimal score_percentage) {
        this.score_percentage = score_percentage;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}