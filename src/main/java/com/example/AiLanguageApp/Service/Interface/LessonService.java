package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.LessonRequest;
import com.example.AiLanguageApp.DTO.Response.LessonResponse;
import com.example.AiLanguageApp.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    Lesson save(Lesson lesson);

    Lesson update(Lesson lesson);

    Optional<Lesson> findById(Long id);

    List<Lesson> findAll();
    
    LessonResponse create(LessonRequest request);

    void deleteById(Long id);
}