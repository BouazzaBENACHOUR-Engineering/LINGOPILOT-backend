package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.UserLanguageRequest;
import com.example.AiLanguageApp.DTO.Response.UserLanguageResponse;
import com.example.AiLanguageApp.model.UserLanguage;

import java.util.List;
import java.util.Optional;

public interface UserLanguageService {

    UserLanguage save(UserLanguage userLanguage);

    UserLanguage update(UserLanguage userLanguage);

    Optional<UserLanguage> findById(Long id);

    List<UserLanguage> findAll();
    
    UserLanguageResponse create(UserLanguageRequest request);

    void deleteById(Long id);
}