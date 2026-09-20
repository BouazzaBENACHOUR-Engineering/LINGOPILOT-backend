package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.LanguageRequest;
import com.example.AiLanguageApp.DTO.Response.LanguageResponse;
import com.example.AiLanguageApp.model.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageService {

    Language save(Language language);

    Language update(Language language);

    Optional<Language> findById(Long id);

    List<Language> findAll();
    
    LanguageResponse create(LanguageRequest request);

    void deleteById(Long id);
}