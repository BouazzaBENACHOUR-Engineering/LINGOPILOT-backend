package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.UserVocabularyRequest;
import com.example.AiLanguageApp.DTO.Response.UserVocabularyResponse;
import com.example.AiLanguageApp.model.UserVocabulary;

import java.util.List;
import java.util.Optional;

public interface UserVocabularyService {

    UserVocabulary save(UserVocabulary userVocabulary);

    UserVocabulary update(UserVocabulary userVocabulary);

    Optional<UserVocabulary> findById(Long id);

    List<UserVocabulary> findAll();
    
    UserVocabularyResponse create(UserVocabularyRequest request);

    void deleteById(Long id);
}