package com.example.AiLanguageApp.AI.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class LessonExecutionResponse {

    private Long lesson_progress_id;
    private Long lesson_id;
    private String lesson_title;
    private String skill_name;
    private String status;
    private BigDecimal completion_percentage;
    private LocalDateTime started_at;
    private List<LessonExerciseItem> exercises;

    public Long getLesson_progress_id() {
        return lesson_progress_id;
    }

    public void setLesson_progress_id(Long lesson_progress_id) {
        this.lesson_progress_id = lesson_progress_id;
    }

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_title() {
        return lesson_title;
    }

    public void setLesson_title(String lesson_title) {
        this.lesson_title = lesson_title;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
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

    public void setCompletion_percentage(
            BigDecimal completion_percentage) {

        this.completion_percentage =
                completion_percentage;
    }

    public LocalDateTime getStarted_at() {
        return started_at;
    }

    public void setStarted_at(
            LocalDateTime started_at) {

        this.started_at =
                started_at;
    }

    public List<LessonExerciseItem> getExercises() {
        return exercises;
    }

    public void setExercises(
            List<LessonExerciseItem> exercises) {

        this.exercises =
                exercises;
    }
}