package com.example.AiLanguageApp.Learning.DTO.Request;

import jakarta.validation.constraints.NotNull;

public class StartLessonRequest {

    @NotNull
    private Long user_id;

    public Long getUser_id() { return user_id; }
    public void setUser_id(Long user_id) { this.user_id = user_id; }
}
