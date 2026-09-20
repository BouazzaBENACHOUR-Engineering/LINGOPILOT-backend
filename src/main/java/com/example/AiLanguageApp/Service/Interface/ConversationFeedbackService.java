package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.ConversationFeedbackRequest;
import com.example.AiLanguageApp.DTO.Response.ConversationFeedbackResponse;
import com.example.AiLanguageApp.model.ConversationFeedback;

import java.util.List;
import java.util.Optional;

public interface ConversationFeedbackService {

    ConversationFeedback save(ConversationFeedback conversationFeedback);

    ConversationFeedback update(ConversationFeedback conversationFeedback);

    Optional<ConversationFeedback> findById(Long id);

    List<ConversationFeedback> findAll();
    
    ConversationFeedbackResponse create(ConversationFeedbackRequest request);

    void deleteById(Long id);
}