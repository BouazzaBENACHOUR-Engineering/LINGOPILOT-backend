package com.example.AiLanguageApp.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserLanguageRequest;
import com.example.AiLanguageApp.DTO.Response.UserLanguageResponse;
import com.example.AiLanguageApp.Mapper.UserLanguageMapper;
import com.example.AiLanguageApp.Repository.LanguageRepository;
import com.example.AiLanguageApp.Repository.LevelRepository;
import com.example.AiLanguageApp.Repository.UserLanguageRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Service.Interface.UserLanguageService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Language;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserLanguage;

@Service
public class UserLanguageServiceImpl
        implements UserLanguageService {

    private final UserLanguageRepository userLanguageRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final LevelRepository levelRepository;
    private final UserLanguageMapper userLanguageMapper;

    public UserLanguageServiceImpl(
            UserLanguageRepository userLanguageRepository,
            UserRepository userRepository,
            LanguageRepository languageRepository,
            LevelRepository levelRepository,
            UserLanguageMapper userLanguageMapper) {

        this.userLanguageRepository =
                userLanguageRepository;

        this.userRepository =
                userRepository;

        this.languageRepository =
                languageRepository;

        this.levelRepository =
                levelRepository;

        this.userLanguageMapper =
                userLanguageMapper;
    }

    @Override
    public UserLanguageResponse create(
            UserLanguageRequest request) {

        User user =
                userRepository
                        .findById(request.getUser_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User does not exist"
                                )
                        );

        Language language =
                languageRepository
                        .findById(request.getLanguage_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Language does not exist"
                                )
                        );

        Level level =
                levelRepository
                        .findById(request.getLevel_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Level does not exist"
                                )
                        );

        UserLanguage userLanguage =
                new UserLanguage();

        userLanguage.setUser_id(user);
        userLanguage.setLanguage_id(language);
        userLanguage.setLevel_id(level);
        userLanguage.setIs_primary(
                request.getIs_primary()
        );
        userLanguage.setStarted_at(
                LocalDateTime.now()
        );

        UserLanguage savedUserLanguage =
                userLanguageRepository.save(
                        userLanguage
                );

        return userLanguageMapper.toResponse(
                savedUserLanguage
        );
    }

    @Override
    public UserLanguage save(
            UserLanguage userLanguage) {

        return userLanguageRepository.save(
                userLanguage
        );
    }

    @Override
    public UserLanguage update(
            UserLanguage userLanguage) {

        if (userLanguage.getId() == null ||
                !userLanguageRepository.existsById(
                        userLanguage.getId())) {

            throw new ResourceNotFoundException(
                    "User language does not exist"
            );
        }

        return userLanguageRepository.save(
                userLanguage
        );
    }

    @Override
    public Optional<UserLanguage> findById(Long id) {

        UserLanguage userLanguage =
                userLanguageRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User language does not exist"
                                )
                        );

        return Optional.of(userLanguage);
    }

    @Override
    public List<UserLanguage> findAll() {

        return userLanguageRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        UserLanguage userLanguage =
                userLanguageRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User language does not exist"
                                )
                        );

        userLanguageRepository.delete(
                userLanguage
        );
    }
}