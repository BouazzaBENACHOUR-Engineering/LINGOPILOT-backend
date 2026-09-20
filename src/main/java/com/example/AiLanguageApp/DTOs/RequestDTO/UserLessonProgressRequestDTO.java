package com.example.AiLanguageApp.DTOs.RequestDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLessonProgressRequestDTO {

    @NotNull(message = "User ID is required")
    private Long user_id;

    @NotNull(message = "Lesson ID is required")
    private Long lesson_id;

    @NotBlank(message = "Status is required")
    @Size(
            max = 20,
            message = "Status must not exceed 20 characters"
    )
    private String status;

    @NotNull(message = "Completion percentage is required")
    @DecimalMin(
            value = "0.00",
            message = "Completion percentage cannot be less than 0"
    )
    @DecimalMax(
            value = "100.00",
            message = "Completion percentage cannot exceed 100"
    )
    private BigDecimal completion_percentage;

    @DecimalMin(
            value = "0.00",
            message = "Score cannot be less than 0"
    )
    @DecimalMax(
            value = "100.00",
            message = "Score cannot exceed 100"
    )
    private BigDecimal score;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCompletion_percentage() {
        return completion_percentage;
    }

    public void setCompletion_percentage(BigDecimal completion_percentage) {
        this.completion_percentage = completion_percentage;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}