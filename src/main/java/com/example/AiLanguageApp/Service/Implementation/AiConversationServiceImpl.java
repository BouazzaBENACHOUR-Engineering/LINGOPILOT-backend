package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.AiConversationRequest;
import com.example.AiLanguageApp.DTO.Response.AiConversationResponse;
import com.example.AiLanguageApp.Repository.AiConversationRepository;
import com.example.AiLanguageApp.Repository.LanguageRepository;
import com.example.AiLanguageApp.Repository.LevelRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Service.Interface.AiConversationService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Language;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.User;

@Service
public class AiConversationServiceImpl implements AiConversationService {

    private final AiConversationRepository aiConversationRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final LevelRepository levelRepository;

    public AiConversationServiceImpl(
            AiConversationRepository aiConversationRepository,
            UserRepository userRepository,
            LanguageRepository languageRepository,
            LevelRepository levelRepository) {

        this.aiConversationRepository = aiConversationRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public AiConversationResponse create(AiConversationRequest request) {

        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User does not exist"
                        )
                );

        Language language = languageRepository.findById(request.getLanguage_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Language does not exist"
                        )
                );

        Level level = null;

        if (request.getLevel_id() != null) {
            level = levelRepository.findById(request.getLevel_id())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Level does not exist"
                            )
                    );
        }

        AiConversation aiConversation = new AiConversation();

        aiConversation.setUser_id(user);
        aiConversation.setLanguage_id(language);
        aiConversation.setTitle(request.getTitle());
        aiConversation.setLevel_id(level);
        aiConversation.setStarted_at(request.getStarted_at());
        aiConversation.setEnded_at(request.getEnded_at());

        AiConversation savedAiConversation =
                aiConversationRepository.save(aiConversation);

        AiConversationResponse response =
                new AiConversationResponse();

        response.setId(savedAiConversation.getId());
        response.setUser_id(savedAiConversation.getUser_id().getId());
        response.setLanguage_id(savedAiConversation.getLanguage_id().getId());
        response.setTitle(savedAiConversation.getTitle());

        if (savedAiConversation.getLevel_id() != null) {
            response.setLevel_id(
                    savedAiConversation.getLevel_id().getId()
            );
        }

        response.setStarted_at(savedAiConversation.getStarted_at());
        response.setEnded_at(savedAiConversation.getEnded_at());

        return response;
    }

    @Override
    public AiConversation save(AiConversation aiConversation) {
        return aiConversationRepository.save(aiConversation);
    }

    @Override
    public Optional<AiConversation> findById(Long id) {
        return aiConversationRepository.findById(id);
    }

    @Override
    public List<AiConversation> findAll() {
        return aiConversationRepository.findAll();
    }

    @Override
    public AiConversation update(AiConversation aiConversation) {

        aiConversationRepository.findById(aiConversation.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "AI conversation does not exist"
                        )
                );

        return aiConversationRepository.save(aiConversation);
    }

    @Override
    public void deleteById(Long id) {

        AiConversation aiConversation =
                aiConversationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI conversation does not exist"
                                )
                        );

        aiConversationRepository.delete(aiConversation);
    }
}