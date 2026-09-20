package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserPreferencesRequest;
import com.example.AiLanguageApp.DTO.Response.UserPreferencesResponse;
import com.example.AiLanguageApp.Repository.UserPreferencesRepository;
import com.example.AiLanguageApp.Service.Interface.UserPreferencesService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.UserPreferences;

@Service
public class UserPreferencesServiceImpl
        implements UserPreferencesService {

    private final UserPreferencesRepository userPreferencesRepository;

    public UserPreferencesServiceImpl(
            UserPreferencesRepository userPreferencesRepository) {

        this.userPreferencesRepository =
                userPreferencesRepository;
    }

    @Override
    public UserPreferencesResponse create(
            UserPreferencesRequest request) {

        UserPreferences userPreferences =
                new UserPreferences();

        UserPreferences savedUserPreferences =
                userPreferencesRepository.save(
                        userPreferences
                );

        UserPreferencesResponse response =
                new UserPreferencesResponse();

        response.setId(savedUserPreferences.getId());

        return response;
    }

    @Override
    public UserPreferences save(
            UserPreferences userPreferences) {

        return userPreferencesRepository.save(
                userPreferences
        );
    }

    @Override
    public UserPreferences update(
            UserPreferences userPreferences) {

        if (userPreferences.getId() == null ||
                !userPreferencesRepository.existsById(
                        userPreferences.getId())) {

            throw new ResourceNotFoundException(
                    "User preferences do not exist"
            );
        }

        return userPreferencesRepository.save(
                userPreferences
        );
    }

    @Override
    public Optional<UserPreferences> findById(Long id) {

        UserPreferences userPreferences =
                userPreferencesRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User preferences do not exist"
                                )
                        );

        return Optional.of(userPreferences);
    }

    @Override
    public List<UserPreferences> findAll() {

        return userPreferencesRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        UserPreferences userPreferences =
                userPreferencesRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User preferences do not exist"
                                )
                        );

        userPreferencesRepository.delete(
                userPreferences
        );
    }
}