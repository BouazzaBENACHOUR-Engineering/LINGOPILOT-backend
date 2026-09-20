package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExerciseRequest {

    @NotNull(message = "lesson_id is required")
    private Long lesson_id;

    @NotBlank(message = "type is required")
    @Size(max = 30, message = "type must not exceed 30 characters")
    private String type;

    @NotBlank(message = "question is required")
    private String question;

    @NotNull(message = "difficulty is required")
    @Min(value = 1, message = "difficulty must be at least 1")
    private Integer difficulty;

    @NotNull(message = "points is required")
    @Min(value = 0, message = "points must not be negative")
    private Integer points;

    @NotNull(message = "sequence is required")
    @Min(value = 1, message = "sequence must be at least 1")
    private Integer sequence;

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}