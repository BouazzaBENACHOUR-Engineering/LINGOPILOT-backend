package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VocabularyRequest {

    @NotNull(message = "language_id is required")
    private Long language_id;

    @NotBlank(message = "word is required")
    @Size(max = 255, message = "word must not exceed 255 characters")
    private String word;

    @Size(max = 255, message = "translation must not exceed 255 characters")
    private String translation;

    private String definition;

    private String example_sentence;

    @Size(max = 1000, message = "audio_url must not exceed 1000 characters")
    private String audio_url;

    @NotNull(message = "difficulty is required")
    @Min(value = 1, message = "difficulty must be at least 1")
    @Max(value = 10, message = "difficulty must not exceed 10")
    private Integer difficulty;

    public Long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Long language_id) {
        this.language_id = language_id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample_sentence() {
        return example_sentence;
    }

    public void setExample_sentence(String example_sentence) {
        this.example_sentence = example_sentence;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}