package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.NotNull;

public class UserVocabularyRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "vocabulary_id is required")
    private Long vocabulary_id;

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
}