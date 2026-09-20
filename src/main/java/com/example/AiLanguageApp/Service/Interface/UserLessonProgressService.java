package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.UserLessonProgressRequest;
import com.example.AiLanguageApp.DTO.Response.UserLessonProgressResponse;
import com.example.AiLanguageApp.model.UserLessonProgress;

import java.util.List;
import java.util.Optional;

public interface UserLessonProgressService {

    UserLessonProgress save(UserLessonProgress userLessonProgress);

    UserLessonProgress update(UserLessonProgress userLessonProgress);

    Optional<UserLessonProgress> findById(Long id);

    List<UserLessonProgress> findAll();
    
    UserLessonProgressResponse create(UserLessonProgressRequest request);

    void deleteById(Long id);
}