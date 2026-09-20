package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.NotificationRequest;
import com.example.AiLanguageApp.DTO.Response.NotificationResponse;
import com.example.AiLanguageApp.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Notification save(Notification notification);

    Notification update(Notification notification);

    Optional<Notification> findById(Long id);

    List<Notification> findAll();
    
    NotificationResponse create(NotificationRequest request);

    void deleteById(Long id);
}