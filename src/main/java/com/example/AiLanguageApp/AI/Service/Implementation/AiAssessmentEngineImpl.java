package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.Client.LlmClient;
import com.example.AiLanguageApp.AI.Context.LearnerContext;
import com.example.AiLanguageApp.AI.DTO.AssessmentResult;
import com.example.AiLanguageApp.AI.Prompt.AssessmentPromptBuilder;
import com.example.AiLanguageApp.AI.Service.AiAssessmentEngine;
import com.example.AiLanguageApp.AI.Service.LearnerContextService;
import com.example.AiLanguageApp.AI.Service.SkillResolver;
import com.example.AiLanguageApp.AI.Service.UserSkillProgressUpdater;
import com.example.AiLanguageApp.DTO.Response.ConversationFeedbackResponse;
import com.example.AiLanguageApp.Repository.AiMessageRepository;
import com.example.AiLanguageApp.Repository.ConversationFeedbackRepository;
import com.example.AiLanguageApp.Repository.UserLanguageRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.AiMessage;
import com.example.AiLanguageApp.model.ConversationFeedback;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.Skill;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserLanguage;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

@Service
public class AiAssessmentEngineImpl
        implements AiAssessmentEngine {

    private final AiMessageRepository aiMessageRepository;
    private final ConversationFeedbackRepository conversationFeedbackRepository;
    private final LearnerContextService learnerContextService;
    private final AssessmentPromptBuilder assessmentPromptBuilder;
    private final LlmClient llmClient;
    private final JsonMapper jsonMapper;
    private final SkillResolver skillResolver;
    private final UserSkillProgressUpdater userSkillProgressUpdater;
    private final UserLanguageRepository userLanguageRepository;

    public AiAssessmentEngineImpl(
            AiMessageRepository aiMessageRepository,
            ConversationFeedbackRepository conversationFeedbackRepository,
            LearnerContextService learnerContextService,
            AssessmentPromptBuilder assessmentPromptBuilder,
            LlmClient llmClient,
            JsonMapper jsonMapper,
            SkillResolver skillResolver,
            UserSkillProgressUpdater userSkillProgressUpdater,
            UserLanguageRepository userLanguageRepository) {

        this.aiMessageRepository =
                aiMessageRepository;

        this.conversationFeedbackRepository =
                conversationFeedbackRepository;

        this.learnerContextService =
                learnerContextService;

        this.assessmentPromptBuilder =
                assessmentPromptBuilder;

        this.llmClient =
                llmClient;

        this.jsonMapper =
                jsonMapper;

        this.skillResolver =
                skillResolver;

        this.userSkillProgressUpdater =
                userSkillProgressUpdater;

        this.userLanguageRepository =
                userLanguageRepository;
    }

    @Override
    @Transactional
    public ConversationFeedbackResponse assessMessage(
            Long messageId) {

        ConversationFeedback existingFeedback =
                conversationFeedbackRepository
                        .findByMessageId(messageId)
                        .orElse(null);

        if (existingFeedback != null) {

            return toResponse(
                    existingFeedback
            );
        }

        AiMessage message =
                aiMessageRepository
                        .findById(messageId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI message does not exist"
                                )
                        );

        if (!"USER".equalsIgnoreCase(
                message.getRole())) {

            throw new IllegalArgumentException(
                    "Only USER messages can be assessed"
            );
        }

        AiConversation conversation =
                message.getConversation_id();

        User user =
                conversation.getUser_id();

        LearnerContext learnerContext =
                learnerContextService
                        .buildContext(
                                conversation
                        );

        String prompt =
                assessmentPromptBuilder
                        .build(
                                message,
                                learnerContext
                        );

        String rawAssessment =
                llmClient.generateResponse(
                        prompt
                );

        AssessmentResult result =
                parseAssessment(
                        rawAssessment
                );

        validateScore(
                result.getGrammar_score(),
                "grammar_score"
        );

        validateScore(
                result.getVocabulary_score(),
                "vocabulary_score"
        );

        validateScore(
                result.getFluency_score(),
                "fluency_score"
        );

        ConversationFeedback feedback =
                new ConversationFeedback();

        feedback.setMessage_id(
                message
        );

        feedback.setGrammar_score(
                result.getGrammar_score()
        );

        feedback.setVocabulary_score(
                result.getVocabulary_score()
        );

        feedback.setFluency_score(
                result.getFluency_score()
        );

        feedback.setPronunciation_score(
                null
        );

        feedback.setFeedback(
                result.getFeedback()
        );

        feedback.setCreated_at(
                LocalDateTime.now()
        );

        ConversationFeedback savedFeedback =
                conversationFeedbackRepository
                        .save(
                                feedback
                        );

        Level level =
                resolveLevel(
                        conversation,
                        user
                );

        Skill grammarSkill =
                skillResolver.resolve(
                        "Grammar"
                );

        Skill vocabularySkill =
                skillResolver.resolve(
                        "Vocabulary"
                );

        Skill fluencySkill =
                skillResolver.resolve(
                        "Fluency"
                );

        userSkillProgressUpdater.update(
                user,
                conversation,
                grammarSkill,
                level,
                result.getGrammar_score()
        );

        userSkillProgressUpdater.update(
                user,
                conversation,
                vocabularySkill,
                level,
                result.getVocabulary_score()
        );

        userSkillProgressUpdater.update(
                user,
                conversation,
                fluencySkill,
                level,
                result.getFluency_score()
        );

        return toResponse(
                savedFeedback
        );
    }

    private Level resolveLevel(
            AiConversation conversation,
            User user) {

        if (conversation.getLevel_id() != null) {

            return conversation.getLevel_id();
        }

        UserLanguage userLanguage =
                userLanguageRepository
                        .findByUserAndLanguage(
                                user.getId(),
                                conversation
                                        .getLanguage_id()
                                        .getId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Learner level does not exist for this language"
                                )
                        );

        return userLanguage.getLevel_id();
    }

    private AssessmentResult parseAssessment(
            String rawAssessment) {

        String json =
                rawAssessment.trim();

        if (json.startsWith("```json")) {

            json =
                    json.substring(7);

        } else if (json.startsWith("```")) {

            json =
                    json.substring(3);
        }

        if (json.endsWith("```")) {

            json =
                    json.substring(
                            0,
                            json.length() - 3
                    );
        }

        try {

            return jsonMapper.readValue(
                    json.trim(),
                    AssessmentResult.class
            );

        } catch (JacksonException exception) {

            throw new IllegalStateException(
                    "Invalid AI assessment response",
                    exception
            );
        }
    }

    private void validateScore(
            BigDecimal score,
            String fieldName) {

        if (score == null ||
                score.compareTo(
                        BigDecimal.ZERO
                ) < 0 ||
                score.compareTo(
                        new BigDecimal("100.00")
                ) > 0) {

            throw new IllegalStateException(
                    "Invalid " + fieldName
            );
        }
    }

    private ConversationFeedbackResponse toResponse(
            ConversationFeedback feedback) {

        ConversationFeedbackResponse response =
                new ConversationFeedbackResponse();

        response.setId(
                feedback.getId()
        );

        response.setMessage_id(
                feedback.getMessage_id()
                        .getId()
        );

        response.setGrammar_score(
                feedback.getGrammar_score()
        );

        response.setVocabulary_score(
                feedback.getVocabulary_score()
        );

        response.setFluency_score(
                feedback.getFluency_score()
        );

        response.setPronunciation_score(
                feedback.getPronunciation_score()
        );

        response.setFeedback(
                feedback.getFeedback()
        );

        response.setCreated_at(
                feedback.getCreated_at()
        );

        return response;
    }
}