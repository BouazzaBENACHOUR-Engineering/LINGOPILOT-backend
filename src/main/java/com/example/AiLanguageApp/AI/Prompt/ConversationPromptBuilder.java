package com.example.AiLanguageApp.AI.Prompt;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.AI.Context.LearnerContext;
import com.example.AiLanguageApp.AI.Service.LearnerContextService;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.AiMessage;

@Component
public class ConversationPromptBuilder {

    private final SystemPromptProvider systemPromptProvider;
    private final LearnerContextService learnerContextService;

    public ConversationPromptBuilder(
            SystemPromptProvider systemPromptProvider,
            LearnerContextService learnerContextService) {

        this.systemPromptProvider =
                systemPromptProvider;

        this.learnerContextService =
                learnerContextService;
    }

    public String build(
            AiConversation conversation,
            List<AiMessage> messages) {

        LearnerContext learnerContext =
                learnerContextService.buildContext(
                        conversation
                );

        String systemPrompt =
                systemPromptProvider.getSystemPrompt(
                        learnerContext
                );

        StringBuilder prompt =
                new StringBuilder();

        prompt.append("SYSTEM:\n")
                .append(systemPrompt)
                .append("\n\n");

        prompt.append("CONVERSATION HISTORY:\n");

        for (AiMessage message : messages) {

            prompt.append(message.getRole())
                    .append(": ")
                    .append(message.getContent())
                    .append("\n");
        }

        prompt.append("ASSISTANT:");

        return prompt.toString();
    }
}