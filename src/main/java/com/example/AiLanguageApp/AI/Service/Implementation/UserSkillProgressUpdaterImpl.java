package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.AI.Service.UserSkillProgressUpdater;
import com.example.AiLanguageApp.Repository.UserSkillProgressRepository;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.Skill;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserSkillProgress;

@Service
public class UserSkillProgressUpdaterImpl
        implements UserSkillProgressUpdater {

    private final UserSkillProgressRepository
            userSkillProgressRepository;

    public UserSkillProgressUpdaterImpl(
            UserSkillProgressRepository userSkillProgressRepository) {

        this.userSkillProgressRepository =
                userSkillProgressRepository;
    }

    @Override
    public void update(
            User user,
            AiConversation conversation,
            Skill skill,
            Level level,
            BigDecimal assessmentScore) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "User cannot be null"
            );
        }

        if (conversation == null) {
            throw new IllegalArgumentException(
                    "AI conversation cannot be null"
            );
        }

        if (skill == null) {
            throw new IllegalArgumentException(
                    "Skill cannot be null"
            );
        }

        if (level == null) {
            throw new IllegalArgumentException(
                    "Level cannot be null"
            );
        }

        if (assessmentScore == null) {
            throw new IllegalArgumentException(
                    "Assessment score cannot be null"
            );
        }

        if (assessmentScore.compareTo(
                BigDecimal.ZERO) < 0 ||
                assessmentScore.compareTo(
                        new BigDecimal("100.00")) > 0) {

            throw new IllegalArgumentException(
                    "Assessment score must be between 0 and 100"
            );
        }

        UserSkillProgress progress =
                userSkillProgressRepository
                        .findByUserLanguageAndSkill(
                                user.getId(),
                                conversation
                                        .getLanguage_id()
                                        .getId(),
                                skill.getId()
                        )
                        .orElse(null);

        BigDecimal updatedScore;

        if (progress == null) {

            progress =
                    new UserSkillProgress();

            progress.setUser_id(
                    user
            );

            progress.setLanguage_id(
                    conversation.getLanguage_id()
            );

            progress.setSkill_id(
                    skill
            );

            progress.setLevel_id(
                    level
            );

            updatedScore =
                    assessmentScore
                            .setScale(
                                    2,
                                    RoundingMode.HALF_UP
                            );

        } else {

            BigDecimal currentScore =
                    progress.getScore();

            BigDecimal currentWeight =
                    new BigDecimal("0.70");

            BigDecimal newAssessmentWeight =
                    new BigDecimal("0.30");

            updatedScore =
                    currentScore
                            .multiply(
                                    currentWeight
                            )
                            .add(
                                    assessmentScore
                                            .multiply(
                                                    newAssessmentWeight
                                            )
                            )
                            .setScale(
                                    2,
                                    RoundingMode.HALF_UP
                            );

            progress.setLevel_id(
                    level
            );
        }

        progress.setScore(
                updatedScore
        );

        progress.setUpdated_at(
                LocalDateTime.now()
        );

        userSkillProgressRepository.save(
                progress
        );
    }
}