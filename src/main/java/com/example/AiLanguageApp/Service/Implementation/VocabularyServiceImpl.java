package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.VocabularyRequest;
import com.example.AiLanguageApp.DTO.Response.VocabularyResponse;
import com.example.AiLanguageApp.Repository.VocabularyRepository;
import com.example.AiLanguageApp.Service.Interface.VocabularyService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Vocabulary;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    public VocabularyServiceImpl(
            VocabularyRepository vocabularyRepository) {

        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public VocabularyResponse create(
            VocabularyRequest request) {

        Vocabulary vocabulary = new Vocabulary();

        Vocabulary savedVocabulary =
                vocabularyRepository.save(vocabulary);

        VocabularyResponse response =
                new VocabularyResponse();

        response.setId(savedVocabulary.getId());

        return response;
    }

    @Override
    public Vocabulary save(
            Vocabulary vocabulary) {

        return vocabularyRepository.save(vocabulary);
    }

    @Override
    public Vocabulary update(
            Vocabulary vocabulary) {

        if (vocabulary.getId() == null ||
                !vocabularyRepository.existsById(
                        vocabulary.getId())) {

            throw new ResourceNotFoundException(
                    "Vocabulary does not exist"
            );
        }

        return vocabularyRepository.save(vocabulary);
    }

    @Override
    public Optional<Vocabulary> findById(Long id) {

        Vocabulary vocabulary =
                vocabularyRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Vocabulary does not exist"
                                )
                        );

        return Optional.of(vocabulary);
    }

    @Override
    public List<Vocabulary> findAll() {

        return vocabularyRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Vocabulary vocabulary =
                vocabularyRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Vocabulary does not exist"
                                )
                        );

        vocabularyRepository.delete(vocabulary);
    }
}