package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.AiConversationRequest;
import com.example.AiLanguageApp.DTO.Response.AiConversationResponse;
import com.example.AiLanguageApp.model.AiConversation;

import java.util.List;
import java.util.Optional;

public interface AiConversationService {

    AiConversation save(AiConversation aiConversation);

    Optional<AiConversation> findById(Long id);
    
    AiConversationResponse create(AiConversationRequest request);

    List<AiConversation> findAll();
    
    AiConversation update(AiConversation aiConversation);


    void deleteById(Long id);
}