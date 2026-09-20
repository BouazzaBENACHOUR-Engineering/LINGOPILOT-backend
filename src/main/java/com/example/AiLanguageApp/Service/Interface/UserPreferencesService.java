package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.UserPreferencesRequest;
import com.example.AiLanguageApp.DTO.Response.UserPreferencesResponse;
import com.example.AiLanguageApp.model.UserPreferences;

import java.util.List;
import java.util.Optional;

public interface UserPreferencesService {

    UserPreferences save(UserPreferences userPreferences);

    UserPreferences update(UserPreferences userPreferences);

    Optional<UserPreferences> findById(Long id);

    List<UserPreferences> findAll();
    
    UserPreferencesResponse create(UserPreferencesRequest request);

    void deleteById(Long id);
}