package com.example.AiLanguageApp.AI.Service.Implementation;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.AI.Service.SkillResolver;
import com.example.AiLanguageApp.Repository.SkillRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Skill;

@Service
public class SkillResolverImpl
        implements SkillResolver {

    private final SkillRepository skillRepository;

    public SkillResolverImpl(
            SkillRepository skillRepository) {

        this.skillRepository =
                skillRepository;
    }

    @Override
    public Skill resolve(
            String skillName) {

        if (skillName == null ||
                skillName.isBlank()) {

            throw new IllegalArgumentException(
                    "Skill name cannot be empty"
            );
        }

        return skillRepository
                .findByNameIgnoreCase(
                        skillName.trim()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Skill does not exist: "
                                        + skillName
                        )
                );
    }
}