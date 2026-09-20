package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.AiAssessmentRequest;
import com.example.AiLanguageApp.DTO.Response.AiAssessmentResponse;
import com.example.AiLanguageApp.Repository.AiAssessmentRepository;
import com.example.AiLanguageApp.Service.Interface.AiAssessmentService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiAssessment;

@Service
public class AiAssessmentServiceImpl implements AiAssessmentService {

    private final AiAssessmentRepository aiAssessmentRepository;

    public AiAssessmentServiceImpl(
            AiAssessmentRepository aiAssessmentRepository) {

        this.aiAssessmentRepository = aiAssessmentRepository;
    }

    @Override
    public AiAssessmentResponse create(
            AiAssessmentRequest request) {

        AiAssessment aiAssessment = new AiAssessment();

        AiAssessment savedAiAssessment =
                aiAssessmentRepository.save(aiAssessment);

        AiAssessmentResponse response =
                new AiAssessmentResponse();

        response.setId(savedAiAssessment.getId());

        return response;
    }

    @Override
    public AiAssessment save(
            AiAssessment aiAssessment) {

        return aiAssessmentRepository.save(aiAssessment);
    }

    @Override
    public AiAssessment update(
            AiAssessment aiAssessment) {

        if (aiAssessment.getId() == null ||
                !aiAssessmentRepository.existsById(
                        aiAssessment.getId())) {

            throw new ResourceNotFoundException(
                    "AI assessment does not exist"
            );
        }

        return aiAssessmentRepository.save(aiAssessment);
    }

    @Override
    public Optional<AiAssessment> findById(Long id) {

        AiAssessment aiAssessment =
                aiAssessmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI assessment does not exist"
                                )
                        );

        return Optional.of(aiAssessment);
    }

    @Override
    public List<AiAssessment> findAll() {

        return aiAssessmentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        AiAssessment aiAssessment =
                aiAssessmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI assessment does not exist"
                                )
                        );

        aiAssessmentRepository.delete(aiAssessment);
    }
}