package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.AiAssessmentRequest;
import com.example.AiLanguageApp.DTO.Response.AiAssessmentResponse;
import com.example.AiLanguageApp.model.AiAssessment;

import java.util.List;
import java.util.Optional;

public interface AiAssessmentService {

    AiAssessment save(AiAssessment aiAssessment);

    AiAssessment update(AiAssessment aiAssessment);

    Optional<AiAssessment> findById(Long id);
    
    AiAssessmentResponse create(AiAssessmentRequest request);

    List<AiAssessment> findAll();

    void deleteById(Long id);
}