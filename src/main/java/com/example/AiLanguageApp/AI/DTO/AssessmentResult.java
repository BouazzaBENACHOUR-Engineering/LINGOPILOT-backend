package com.example.AiLanguageApp.AI.DTO;

import java.math.BigDecimal;

public class AssessmentResult {

    private BigDecimal grammar_score;
    private BigDecimal vocabulary_score;
    private BigDecimal fluency_score;
    private String feedback;

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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}