package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.UserSkillProgressRequest;
import com.example.AiLanguageApp.DTO.Response.UserSkillProgressResponse;
import com.example.AiLanguageApp.model.UserSkillProgress;

import java.util.List;
import java.util.Optional;

public interface UserSkillProgressService {

    UserSkillProgress save(UserSkillProgress userSkillProgress);

    UserSkillProgress update(UserSkillProgress userSkillProgress);

    Optional<UserSkillProgress> findById(Long id);

    List<UserSkillProgress> findAll();
    
    UserSkillProgressResponse create(UserSkillProgressRequest request);

    void deleteById(Long id);
}