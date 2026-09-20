package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.AI.Context.LearnerContext;
import com.example.AiLanguageApp.model.AiConversation;

public interface LearnerContextService {

    LearnerContext buildContext(
            AiConversation conversation
    );
}