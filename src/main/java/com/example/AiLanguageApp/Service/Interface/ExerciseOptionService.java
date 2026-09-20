package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.ExerciseOptionRequest;
import com.example.AiLanguageApp.DTO.Response.ExerciseOptionResponse;
import com.example.AiLanguageApp.model.ExerciseOption;

import java.util.List;
import java.util.Optional;

public interface ExerciseOptionService {

    ExerciseOption save(ExerciseOption exerciseOption);

    ExerciseOption update(ExerciseOption exerciseOption);

    Optional<ExerciseOption> findById(Long id);

    List<ExerciseOption> findAll();
    
    ExerciseOptionResponse create(ExerciseOptionRequest request);

    void deleteById(Long id);
}