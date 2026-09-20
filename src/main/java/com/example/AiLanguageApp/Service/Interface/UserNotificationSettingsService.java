package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.UserNotificationSettingsRequest;
import com.example.AiLanguageApp.DTO.Response.UserNotificationSettingsResponse;
import com.example.AiLanguageApp.model.UserNotificationSettings;

import java.util.List;
import java.util.Optional;

public interface UserNotificationSettingsService {

    UserNotificationSettings save(UserNotificationSettings userNotificationSettings);

    UserNotificationSettings update(UserNotificationSettings userNotificationSettings);

    Optional<UserNotificationSettings> findById(Long id);

    List<UserNotificationSettings> findAll();
    
    UserNotificationSettingsResponse create(UserNotificationSettingsRequest request);

    void deleteById(Long id);
}