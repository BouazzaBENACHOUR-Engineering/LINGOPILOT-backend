package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.LanguageRequest;
import com.example.AiLanguageApp.DTO.Response.LanguageResponse;
import com.example.AiLanguageApp.Repository.LanguageRepository;
import com.example.AiLanguageApp.Service.Interface.LanguageService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Language;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(
            LanguageRepository languageRepository) {

        this.languageRepository = languageRepository;
    }

    @Override
    public LanguageResponse create(
            LanguageRequest request) {

        Language language = new Language();

        language.setCode(request.getCode());
        language.setName(request.getName());
        language.setIs_active(request.getIs_active());

        Language savedLanguage =
                languageRepository.save(language);

        LanguageResponse response =
                new LanguageResponse();

        response.setId(savedLanguage.getId());

        return response;
    }

    @Override
    public Language save(
            Language language) {

        return languageRepository.save(language);
    }

    @Override
    public Language update(
            Language language) {

        if (language.getId() == null ||
                !languageRepository.existsById(
                        language.getId())) {

            throw new ResourceNotFoundException(
                    "Language does not exist"
            );
        }

        return languageRepository.save(language);
    }

    @Override
    public Optional<Language> findById(Long id) {

        Language language =
                languageRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Language does not exist"
                                )
                        );

        return Optional.of(language);
    }

    @Override
    public List<Language> findAll() {

        return languageRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Language language =
                languageRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Language does not exist"
                                )
                        );

        languageRepository.delete(language);
    }
}