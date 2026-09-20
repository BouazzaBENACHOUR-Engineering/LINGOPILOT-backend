package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserVocabularyRequest;
import com.example.AiLanguageApp.DTO.Response.UserVocabularyResponse;
import com.example.AiLanguageApp.Repository.UserVocabularyRepository;
import com.example.AiLanguageApp.Service.Interface.UserVocabularyService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.UserVocabulary;

@Service
public class UserVocabularyServiceImpl
        implements UserVocabularyService {

    private final UserVocabularyRepository userVocabularyRepository;

    public UserVocabularyServiceImpl(
            UserVocabularyRepository userVocabularyRepository) {

        this.userVocabularyRepository =
                userVocabularyRepository;
    }

    @Override
    public UserVocabularyResponse create(
            UserVocabularyRequest request) {

        UserVocabulary userVocabulary =
                new UserVocabulary();

        UserVocabulary savedUserVocabulary =
                userVocabularyRepository.save(
                        userVocabulary
                );

        UserVocabularyResponse response =
                new UserVocabularyResponse();

        response.setId(savedUserVocabulary.getId());

        return response;
    }

    @Override
    public UserVocabulary save(
            UserVocabulary userVocabulary) {

        return userVocabularyRepository.save(
                userVocabulary
        );
    }

    @Override
    public UserVocabulary update(
            UserVocabulary userVocabulary) {

        if (userVocabulary.getId() == null ||
                !userVocabularyRepository.existsById(
                        userVocabulary.getId())) {

            throw new ResourceNotFoundException(
                    "User vocabulary does not exist"
            );
        }

        return userVocabularyRepository.save(
                userVocabulary
        );
    }

    @Override
    public Optional<UserVocabulary> findById(Long id) {

        UserVocabulary userVocabulary =
                userVocabularyRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User vocabulary does not exist"
                                )
                        );

        return Optional.of(userVocabulary);
    }

    @Override
    public List<UserVocabulary> findAll() {

        return userVocabularyRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        UserVocabulary userVocabulary =
                userVocabularyRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User vocabulary does not exist"
                                )
                        );

        userVocabularyRepository.delete(
                userVocabulary
        );
    }
}