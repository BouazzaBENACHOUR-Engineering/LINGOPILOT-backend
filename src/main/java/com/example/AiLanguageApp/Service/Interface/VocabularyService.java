package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.VocabularyRequest;
import com.example.AiLanguageApp.DTO.Response.VocabularyResponse;
import com.example.AiLanguageApp.model.Vocabulary;

import java.util.List;
import java.util.Optional;

public interface VocabularyService {

    Vocabulary save(Vocabulary vocabulary);

    Vocabulary update(Vocabulary vocabulary);

    Optional<Vocabulary> findById(Long id);

    List<Vocabulary> findAll();
    
    VocabularyResponse create(VocabularyRequest request);

    void deleteById(Long id);
}