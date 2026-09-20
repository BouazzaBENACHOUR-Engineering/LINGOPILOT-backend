package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.Context.LearnerContext;
import com.example.AiLanguageApp.AI.Service.LearnerContextService;
import com.example.AiLanguageApp.Repository.AiConversationRepository;
import com.example.AiLanguageApp.Repository.UserLanguageRepository;
import com.example.AiLanguageApp.Repository.UserSkillProgressRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserLanguage;
import com.example.AiLanguageApp.model.UserSkillProgress;

@Service
public class LearnerContextServiceImpl implements LearnerContextService {

    private final AiConversationRepository aiConversationRepository;
    private final UserLanguageRepository userLanguageRepository;
    private final UserSkillProgressRepository userSkillProgressRepository;

    public LearnerContextServiceImpl(
            AiConversationRepository aiConversationRepository,
            UserLanguageRepository userLanguageRepository,
            UserSkillProgressRepository userSkillProgressRepository) {

        this.aiConversationRepository =
                aiConversationRepository;

        this.userLanguageRepository =
                userLanguageRepository;

        this.userSkillProgressRepository =
                userSkillProgressRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public LearnerContext buildContext(
            AiConversation conversation) {

        if (conversation == null ||
                conversation.getId() == null) {

            throw new IllegalArgumentException(
                    "AI conversation cannot be null"
            );
        }

        AiConversation persistedConversation =
                aiConversationRepository
                        .findById(
                                conversation.getId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI conversation does not exist"
                                )
                        );

        User user =
                persistedConversation.getUser_id();

        LearnerContext context =
                new LearnerContext();

        context.setUser_id(
                user.getId()
        );

        context.setFirst_name(
                user.getFirst_name()
        );

        if (user.getNative_language_id() != null) {

            context.setNative_language(
                    user.getNative_language_id()
                            .getName()
            );
        }

        if (persistedConversation.getLanguage_id() != null) {

            context.setTarget_language(
                    persistedConversation
                            .getLanguage_id()
                            .getName()
            );
        }

        Level level =
                persistedConversation.getLevel_id();

        if (level == null) {

            UserLanguage userLanguage =
                    userLanguageRepository
                            .findByUserAndLanguage(
                                    user.getId(),
                                    persistedConversation
                                            .getLanguage_id()
                                            .getId()
                            )
                            .orElse(null);

            if (userLanguage != null) {

                level =
                        userLanguage.getLevel_id();
            }
        }

        if (level != null) {

            context.setLevel(
                    level.getCode()
            );
        }

        List<UserSkillProgress> skillProgress =
                userSkillProgressRepository
                        .findByUserAndLanguage(
                                user.getId(),
                                persistedConversation
                                        .getLanguage_id()
                                        .getId()
                        );

        for (UserSkillProgress progress : skillProgress) {

            String skillName =
                    progress.getSkill_id()
                            .getName();

            if ("Grammar".equalsIgnoreCase(skillName)) {

                context.setGrammar_score(
                        progress.getScore()
                );

            } else if ("Vocabulary".equalsIgnoreCase(skillName)) {

                context.setVocabulary_score(
                        progress.getScore()
                );

            } else if ("Fluency".equalsIgnoreCase(skillName)) {

                context.setFluency_score(
                        progress.getScore()
                );
            }
        }

        context.setWeakest_skill(
                determineWeakestSkill(
                        context
                )
        );

        return context;
    }

    private String determineWeakestSkill(
            LearnerContext context) {

        BigDecimal grammar =
                context.getGrammar_score();

        BigDecimal vocabulary =
                context.getVocabulary_score();

        BigDecimal fluency =
                context.getFluency_score();

        String weakestSkill = null;
        BigDecimal weakestScore = null;

        if (grammar != null) {

            weakestSkill =
                    "Grammar";

            weakestScore =
                    grammar;
        }

        if (vocabulary != null &&
                (weakestScore == null ||
                        vocabulary.compareTo(
                                weakestScore
                        ) < 0)) {

            weakestSkill =
                    "Vocabulary";

            weakestScore =
                    vocabulary;
        }

        if (fluency != null &&
                (weakestScore == null ||
                        fluency.compareTo(
                                weakestScore
                        ) < 0)) {

            weakestSkill =
                    "Fluency";
        }

        return weakestSkill;
    }
}