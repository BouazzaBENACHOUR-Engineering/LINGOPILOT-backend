package com.example.AiLanguageApp.Learning.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LearningExerciseSubmissionRequest {

    @NotNull
    private Long user_id;

    @NotBlank
    private String answer;

    public Long getUser_id() { return user_id; }
    public void setUser_id(Long user_id) { this.user_id = user_id; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
