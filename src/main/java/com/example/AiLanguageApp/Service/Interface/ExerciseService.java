package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.ExerciseRequest;
import com.example.AiLanguageApp.DTO.Response.ExerciseResponse;
import com.example.AiLanguageApp.model.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    Exercise save(Exercise exercise);

    Exercise update(Exercise exercise);

    Optional<Exercise> findById(Long id);

    List<Exercise> findAll();
    
    ExerciseResponse create(ExerciseRequest request);

    void deleteById(Long id);
}