package com.example.AiLanguageApp.AI.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.Client.LlmClient;
import com.example.AiLanguageApp.AI.DTO.Request.AiChatRequest;
import com.example.AiLanguageApp.AI.DTO.Response.AiChatResponse;
import com.example.AiLanguageApp.AI.Prompt.ConversationPromptBuilder;
import com.example.AiLanguageApp.AI.Service.AiConversationOrchestrator;
import com.example.AiLanguageApp.AI.Service.AiUsageService;
import com.example.AiLanguageApp.Repository.AiConversationRepository;
import com.example.AiLanguageApp.Repository.AiMessageRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.AiMessage;
import com.example.AiLanguageApp.model.User;

@Service
public class AiConversationOrchestratorImpl
        implements AiConversationOrchestrator {

    private final AiConversationRepository aiConversationRepository;

    private final AiMessageRepository aiMessageRepository;

    private final LlmClient llmClient;

    private final ConversationPromptBuilder conversationPromptBuilder;

    private final AiUsageService aiUsageService;

    public AiConversationOrchestratorImpl(
            AiConversationRepository aiConversationRepository,
            AiMessageRepository aiMessageRepository,
            LlmClient llmClient,
            ConversationPromptBuilder conversationPromptBuilder,
            AiUsageService aiUsageService) {

        this.aiConversationRepository =
                aiConversationRepository;

        this.aiMessageRepository =
                aiMessageRepository;

        this.llmClient =
                llmClient;

        this.conversationPromptBuilder =
                conversationPromptBuilder;

        this.aiUsageService =
                aiUsageService;
    }

    @Override
    @Transactional
    public AiChatResponse chat(
            AiChatRequest request) {

        AiConversation conversation =
                aiConversationRepository
                        .findById(
                                request.getConversation_id()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI conversation does not exist"
                                )
                        );

        User user =
                conversation.getUser_id();

        Long userId =
                user.getId();

        aiUsageService.checkAiMessageAccess(
                userId
        );

        AiMessage userMessage =
                new AiMessage();

        userMessage.setConversation_id(
                conversation
        );

        userMessage.setRole(
                "USER"
        );

        userMessage.setContent(
                request.getMessage()
        );

        userMessage.setCreated_at(
                LocalDateTime.now()
        );

        aiMessageRepository.save(
                userMessage
        );

        List<AiMessage> conversationHistory =
                aiMessageRepository
                        .findConversationHistory(
                                conversation.getId()
                        );

        String prompt =
                conversationPromptBuilder
                        .build(
                                conversation,
                                conversationHistory
                        );

        String aiResponse =
                llmClient.generateResponse(
                        prompt
                );

        AiMessage assistantMessage =
                new AiMessage();

        assistantMessage.setConversation_id(
                conversation
        );

        assistantMessage.setRole(
                "ASSISTANT"
        );

        assistantMessage.setContent(
                aiResponse
        );

        assistantMessage.setCreated_at(
                LocalDateTime.now()
        );

        AiMessage savedAssistantMessage =
                aiMessageRepository.save(
                        assistantMessage
                );

        aiUsageService.consumeAiMessage(
                userId
        );

        AiChatResponse response =
                new AiChatResponse();

        response.setConversation_id(
                conversation.getId()
        );

        response.setMessage_id(
                savedAssistantMessage.getId()
        );

        response.setResponse(
                savedAssistantMessage.getContent()
        );

        return response;
    }
}