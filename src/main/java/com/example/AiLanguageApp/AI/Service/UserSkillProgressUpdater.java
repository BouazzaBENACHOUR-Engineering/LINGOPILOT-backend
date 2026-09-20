package com.example.AiLanguageApp.AI.Service;

import java.math.BigDecimal;

import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.Skill;
import com.example.AiLanguageApp.model.User;

public interface UserSkillProgressUpdater {

    void update(
            User user,
            AiConversation conversation,
            Skill skill,
            Level level,
            BigDecimal assessmentScore
    );
}