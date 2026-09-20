package com.example.AiLanguageApp.AI.Service;

import com.example.AiLanguageApp.model.Skill;

public interface SkillResolver {

    Skill resolve(
            String skillName
    );
}