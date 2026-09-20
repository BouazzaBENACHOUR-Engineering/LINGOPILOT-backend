package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.SkillRequest;
import com.example.AiLanguageApp.DTO.Response.SkillResponse;
import com.example.AiLanguageApp.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    Skill save(Skill skill);

    Skill update(Skill skill);

    Optional<Skill> findById(Long id);

    List<Skill> findAll();
    
    SkillResponse create(SkillRequest request);

    void deleteById(Long id);
}