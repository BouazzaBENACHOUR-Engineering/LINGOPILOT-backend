package com.example.AiLanguageApp.AI.Context;

import java.math.BigDecimal;

public class LearnerContext {

    private Long user_id;
    private String first_name;
    private String native_language;
    private String target_language;
    private String level;

    private BigDecimal grammar_score;
    private BigDecimal vocabulary_score;
    private BigDecimal fluency_score;

    private String weakest_skill;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getNative_language() {
        return native_language;
    }

    public void setNative_language(String native_language) {
        this.native_language = native_language;
    }

    public String getTarget_language() {
        return target_language;
    }

    public void setTarget_language(String target_language) {
        this.target_language = target_language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getWeakest_skill() {
        return weakest_skill;
    }

    public void setWeakest_skill(String weakest_skill) {
        this.weakest_skill = weakest_skill;
    }
}