package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.UserExerciseAttemptRequest;
import com.example.AiLanguageApp.DTO.Response.UserExerciseAttemptResponse;
import com.example.AiLanguageApp.model.UserExerciseAttempt;

import java.util.List;
import java.util.Optional;

public interface UserExerciseAttemptService {

    UserExerciseAttempt save(UserExerciseAttempt userExerciseAttempt);

    UserExerciseAttempt update(UserExerciseAttempt userExerciseAttempt);

    Optional<UserExerciseAttempt> findById(Long id);

    List<UserExerciseAttempt> findAll();
    
    UserExerciseAttemptResponse create(UserExerciseAttemptRequest request);

    void deleteById(Long id);
}