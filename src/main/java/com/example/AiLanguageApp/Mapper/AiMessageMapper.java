package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.AiMessageResponse;
import com.example.AiLanguageApp.model.AiMessage;

@Component
public class AiMessageMapper {

    public AiMessageResponse toResponse(AiMessage aiMessage) {

        if (aiMessage == null) {
            return null;
        }

        AiMessageResponse response = new AiMessageResponse();

        response.setId(aiMessage.getId());

        if (aiMessage.getConversation_id() != null) {
            response.setConversation_id(
                    aiMessage.getConversation_id().getId()
            );
        }

        response.setRole(aiMessage.getRole());
        response.setContent(aiMessage.getContent());
        response.setCreated_at(aiMessage.getCreated_at());

        return response;
    }
}