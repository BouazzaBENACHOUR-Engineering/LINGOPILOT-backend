package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExerciseAttemptRequestDTO {

    @NotNull(message = "Exercise ID is required")
    private Long exercise_id;

    @NotBlank(message = "Answer is required")
    private String answer;

    public Long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}