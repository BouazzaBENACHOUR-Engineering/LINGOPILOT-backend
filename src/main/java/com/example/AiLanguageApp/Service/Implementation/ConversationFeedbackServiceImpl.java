package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.ConversationFeedbackRequest;
import com.example.AiLanguageApp.DTO.Response.ConversationFeedbackResponse;
import com.example.AiLanguageApp.Repository.ConversationFeedbackRepository;
import com.example.AiLanguageApp.Service.Interface.ConversationFeedbackService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.ConversationFeedback;

@Service
public class ConversationFeedbackServiceImpl
        implements ConversationFeedbackService {

    private final ConversationFeedbackRepository conversationFeedbackRepository;

    public ConversationFeedbackServiceImpl(
            ConversationFeedbackRepository conversationFeedbackRepository) {

        this.conversationFeedbackRepository =
                conversationFeedbackRepository;
    }

    @Override
    public ConversationFeedbackResponse create(
            ConversationFeedbackRequest request) {

        ConversationFeedback conversationFeedback =
                new ConversationFeedback();

        ConversationFeedback savedConversationFeedback =
                conversationFeedbackRepository.save(
                        conversationFeedback
                );

        ConversationFeedbackResponse response =
                new ConversationFeedbackResponse();

        response.setId(savedConversationFeedback.getId());

        return response;
    }

    @Override
    public ConversationFeedback save(
            ConversationFeedback conversationFeedback) {

        return conversationFeedbackRepository.save(
                conversationFeedback
        );
    }

    @Override
    public ConversationFeedback update(
            ConversationFeedback conversationFeedback) {

        if (conversationFeedback.getId() == null ||
                !conversationFeedbackRepository.existsById(
                        conversationFeedback.getId())) {

            throw new ResourceNotFoundException(
                    "Conversation feedback does not exist"
            );
        }

        return conversationFeedbackRepository.save(
                conversationFeedback
        );
    }

    @Override
    public Optional<ConversationFeedback> findById(Long id) {

        ConversationFeedback conversationFeedback =
                conversationFeedbackRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Conversation feedback does not exist"
                                )
                        );

        return Optional.of(conversationFeedback);
    }

    @Override
    public List<ConversationFeedback> findAll() {

        return conversationFeedbackRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        ConversationFeedback conversationFeedback =
                conversationFeedbackRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Conversation feedback does not exist"
                                )
                        );

        conversationFeedbackRepository.delete(
                conversationFeedback
        );
    }
}