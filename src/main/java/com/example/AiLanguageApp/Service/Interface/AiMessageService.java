package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.AiMessageRequest;
import com.example.AiLanguageApp.DTO.Response.AiMessageResponse;
import com.example.AiLanguageApp.model.AiMessage;

import java.util.List;
import java.util.Optional;

public interface AiMessageService {

    AiMessage save(AiMessage aiMessage);

    AiMessage update(AiMessage aiMessage);

    Optional<AiMessage> findById(Long id);
    
    AiMessageResponse create(AiMessageRequest request);

    List<AiMessage> findAll();

    void deleteById(Long id);
}