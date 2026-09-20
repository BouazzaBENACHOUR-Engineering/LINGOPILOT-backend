package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.CourseRequest;
import com.example.AiLanguageApp.DTO.Response.CourseResponse;
import com.example.AiLanguageApp.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course save(Course course);

    Course update(Course course);

    Optional<Course> findById(Long id);

    List<Course> findAll();
    
    CourseResponse create(CourseRequest request);

    void deleteById(Long id);
}