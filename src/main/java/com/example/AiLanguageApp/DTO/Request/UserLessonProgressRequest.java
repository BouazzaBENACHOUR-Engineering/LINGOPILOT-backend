package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.NotNull;

public class UserLessonProgressRequest {

    @NotNull(message = "user_id is required")
    private Long user_id;

    @NotNull(message = "lesson_id is required")
    private Long lesson_id;

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
}