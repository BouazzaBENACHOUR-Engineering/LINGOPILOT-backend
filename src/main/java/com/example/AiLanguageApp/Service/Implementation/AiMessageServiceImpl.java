package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.AiMessageRequest;
import com.example.AiLanguageApp.DTO.Response.AiMessageResponse;
import com.example.AiLanguageApp.Mapper.AiMessageMapper;
import com.example.AiLanguageApp.Repository.AiConversationRepository;
import com.example.AiLanguageApp.Repository.AiMessageRepository;
import com.example.AiLanguageApp.Service.Interface.AiMessageService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.AiMessage;

@Service
public class AiMessageServiceImpl implements AiMessageService {

    private final AiMessageRepository aiMessageRepository;
    private final AiConversationRepository aiConversationRepository;
    private final AiMessageMapper aiMessageMapper;

    public AiMessageServiceImpl(
            AiMessageRepository aiMessageRepository,
            AiConversationRepository aiConversationRepository,
            AiMessageMapper aiMessageMapper) {

        this.aiMessageRepository = aiMessageRepository;
        this.aiConversationRepository = aiConversationRepository;
        this.aiMessageMapper = aiMessageMapper;
    }

    @Override
    public AiMessageResponse create(
            AiMessageRequest request) {

        AiConversation conversation =
                aiConversationRepository
                        .findById(request.getConversation_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI conversation does not exist"
                                )
                        );

        AiMessage aiMessage = new AiMessage();

        aiMessage.setConversation_id(conversation);
        aiMessage.setRole(request.getRole());
        aiMessage.setContent(request.getContent());
        aiMessage.setCreated_at(request.getCreated_at());

        AiMessage savedAiMessage =
                aiMessageRepository.save(aiMessage);

        return aiMessageMapper.toResponse(savedAiMessage);
    }

    @Override
    public AiMessage save(
            AiMessage aiMessage) {

        return aiMessageRepository.save(aiMessage);
    }

    @Override
    public AiMessage update(
            AiMessage aiMessage) {

        if (aiMessage.getId() == null ||
                !aiMessageRepository.existsById(
                        aiMessage.getId())) {

            throw new ResourceNotFoundException(
                    "AI message does not exist"
            );
        }

        return aiMessageRepository.save(aiMessage);
    }

    @Override
    public Optional<AiMessage> findById(Long id) {

        AiMessage aiMessage =
                aiMessageRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI message does not exist"
                                )
                        );

        return Optional.of(aiMessage);
    }

    @Override
    public List<AiMessage> findAll() {

        return aiMessageRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        AiMessage aiMessage =
                aiMessageRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI message does not exist"
                                )
                        );

        aiMessageRepository.delete(aiMessage);
    }
}