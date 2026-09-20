package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExerciseOptionRequest {

    @NotNull(message = "exercise_id is required")
    private Long exercise_id;

    @NotBlank(message = "option_text is required")
    @Size(max = 1000, message = "option_text must not exceed 1000 characters")
    private String option_text;

    @NotNull(message = "is_correct is required")
    private Boolean is_correct;

    @NotNull(message = "sequence is required")
    @Min(value = 1, message = "sequence must be at least 1")
    private Integer sequence;

    public Long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getOption_text() {
        return option_text;
    }

    public void setOption_text(String option_text) {
        this.option_text = option_text;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}