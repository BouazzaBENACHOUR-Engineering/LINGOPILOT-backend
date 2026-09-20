package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.Progression.LevelReadinessResult;
import com.example.AiLanguageApp.AI.Service.LevelProgressionService;
import com.example.AiLanguageApp.Repository.LevelRepository;
import com.example.AiLanguageApp.Repository.UserLanguageRepository;
import com.example.AiLanguageApp.Repository.UserLessonProgressRepository;
import com.example.AiLanguageApp.Repository.UserSkillProgressRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.UserLanguage;
import com.example.AiLanguageApp.model.UserLessonProgress;
import com.example.AiLanguageApp.model.UserSkillProgress;

@Service
public class LevelProgressionServiceImpl
        implements LevelProgressionService {

    private static final BigDecimal MINIMUM_SKILL_SCORE =
            new BigDecimal("80.00");

    private static final BigDecimal MINIMUM_AVERAGE_SKILL_SCORE =
            new BigDecimal("82.00");

    private static final long MINIMUM_COMPLETED_LESSONS =
            5L;

    private static final BigDecimal MINIMUM_RECENT_LESSON_AVERAGE =
            new BigDecimal("80.00");

    private static final int RECENT_LESSON_LIMIT =
            5;

    private final UserLanguageRepository userLanguageRepository;
    private final UserSkillProgressRepository userSkillProgressRepository;
    private final UserLessonProgressRepository userLessonProgressRepository;
    private final LevelRepository levelRepository;

    public LevelProgressionServiceImpl(
            UserLanguageRepository userLanguageRepository,
            UserSkillProgressRepository userSkillProgressRepository,
            UserLessonProgressRepository userLessonProgressRepository,
            LevelRepository levelRepository) {

        this.userLanguageRepository =
                userLanguageRepository;

        this.userSkillProgressRepository =
                userSkillProgressRepository;

        this.userLessonProgressRepository =
                userLessonProgressRepository;

        this.levelRepository =
                levelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public LevelReadinessResult evaluateReadiness(
            Long userId,
            Long languageId) {

        UserLanguage userLanguage =
                userLanguageRepository
                        .findByUserAndLanguage(
                                userId,
                                languageId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Learner language profile does not exist"
                                )
                        );

        Level currentLevel =
                userLanguage.getLevel_id();

        if (currentLevel == null) {

            throw new ResourceNotFoundException(
                    "Learner current level does not exist"
            );
        }

        Level nextLevel =
                resolveNextLevel(
                        currentLevel
                );

        List<UserSkillProgress> skillProgress =
                userSkillProgressRepository
                        .findByUserAndLanguage(
                                userId,
                                languageId
                        );

        BigDecimal grammarScore =
                getSkillScore(
                        skillProgress,
                        "Grammar"
                );

        BigDecimal vocabularyScore =
                getSkillScore(
                        skillProgress,
                        "Vocabulary"
                );

        BigDecimal fluencyScore =
                getSkillScore(
                        skillProgress,
                        "Fluency"
                );

        BigDecimal averageSkillScore =
                calculateAverageSkillScore(
                        grammarScore,
                        vocabularyScore,
                        fluencyScore
                );

        List<UserLessonProgress> completedLessons =
                userLessonProgressRepository
                        .findCompletedByUserLanguageAndLevel(
                                userId,
                                languageId,
                                currentLevel.getId()
                        );

        long completedLessonCount =
                completedLessons.size();

        BigDecimal recentLessonAverage =
                calculateRecentLessonAverage(
                        completedLessons
                );

        boolean readyForPromotion =
                nextLevel != null
                        && grammarScore != null
                        && vocabularyScore != null
                        && fluencyScore != null
                        && grammarScore.compareTo(
                                MINIMUM_SKILL_SCORE
                        ) >= 0
                        && vocabularyScore.compareTo(
                                MINIMUM_SKILL_SCORE
                        ) >= 0
                        && fluencyScore.compareTo(
                                MINIMUM_SKILL_SCORE
                        ) >= 0
                        && averageSkillScore != null
                        && averageSkillScore.compareTo(
                                MINIMUM_AVERAGE_SKILL_SCORE
                        ) >= 0
                        && completedLessonCount >=
                                MINIMUM_COMPLETED_LESSONS
                        && recentLessonAverage != null
                        && recentLessonAverage.compareTo(
                                MINIMUM_RECENT_LESSON_AVERAGE
                        ) >= 0;

        LevelReadinessResult result =
                new LevelReadinessResult();

        result.setUser_id(
                userId
        );

        result.setLanguage_id(
                languageId
        );

        result.setCurrent_level_id(
                currentLevel.getId()
        );

        result.setCurrent_level_code(
                currentLevel.getCode()
        );

        if (nextLevel != null) {

            result.setNext_level_id(
                    nextLevel.getId()
            );

            result.setNext_level_code(
                    nextLevel.getCode()
            );
        }

        result.setGrammar_score(
                grammarScore
        );

        result.setVocabulary_score(
                vocabularyScore
        );

        result.setFluency_score(
                fluencyScore
        );

        result.setAverage_skill_score(
                averageSkillScore
        );

        result.setCompleted_lessons(
                completedLessonCount
        );

        result.setRecent_lesson_average(
                recentLessonAverage
        );

        result.setReady_for_promotion(
                readyForPromotion
        );

        result.setReason(
                buildReason(
                        nextLevel,
                        grammarScore,
                        vocabularyScore,
                        fluencyScore,
                        averageSkillScore,
                        completedLessonCount,
                        recentLessonAverage,
                        readyForPromotion
                )
        );

        return result;
    }

    @Override
    @Transactional
    public LevelReadinessResult promoteIfReady(
            Long userId,
            Long languageId) {

        LevelReadinessResult readiness =
                evaluateReadiness(
                        userId,
                        languageId
                );

        if (!Boolean.TRUE.equals(
                readiness.getReady_for_promotion())) {

            return readiness;
        }

        UserLanguage userLanguage =
                userLanguageRepository
                        .findByUserAndLanguage(
                                userId,
                                languageId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Learner language profile does not exist"
                                )
                        );

        Level nextLevel =
                levelRepository
                        .findById(
                                readiness.getNext_level_id()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Next level does not exist"
                                )
                        );

        userLanguage.setLevel_id(
                nextLevel
        );

        userLanguageRepository.save(
                userLanguage
        );

        LevelReadinessResult promoted =
                new LevelReadinessResult();

        promoted.setUser_id(
                readiness.getUser_id()
        );

        promoted.setLanguage_id(
                readiness.getLanguage_id()
        );

        promoted.setCurrent_level_id(
                nextLevel.getId()
        );

        promoted.setCurrent_level_code(
                nextLevel.getCode()
        );

        promoted.setGrammar_score(
                readiness.getGrammar_score()
        );

        promoted.setVocabulary_score(
                readiness.getVocabulary_score()
        );

        promoted.setFluency_score(
                readiness.getFluency_score()
        );

        promoted.setAverage_skill_score(
                readiness.getAverage_skill_score()
        );

        promoted.setCompleted_lessons(
                readiness.getCompleted_lessons()
        );

        promoted.setRecent_lesson_average(
                readiness.getRecent_lesson_average()
        );

        promoted.setReady_for_promotion(
                false
        );

        promoted.setReason(
                "Learner promoted to "
                        + nextLevel.getCode()
                        + "."
        );

        Level levelAfterPromotion =
                resolveNextLevel(
                        nextLevel
                );

        if (levelAfterPromotion != null) {

            promoted.setNext_level_id(
                    levelAfterPromotion.getId()
            );

            promoted.setNext_level_code(
                    levelAfterPromotion.getCode()
            );
        }

        return promoted;
    }

    private Level resolveNextLevel(
            Level currentLevel) {

        String nextCode =
                switch (
                        currentLevel
                                .getCode()
                                .toUpperCase()
                ) {

                    case "A1" -> "A2";

                    case "A2" -> "B1";

                    case "B1" -> "B2";

                    case "B2" -> "C1";

                    case "C1" -> "C2";

                    case "C2" -> null;

                    default ->
                            throw new IllegalStateException(
                                    "Unsupported CEFR level: "
                                            + currentLevel.getCode()
                            );
                };

        if (nextCode == null) {

            return null;
        }

        return levelRepository
                .findByCode(
                        nextCode
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Next CEFR level does not exist: "
                                        + nextCode
                        )
                );
    }

    private BigDecimal getSkillScore(
            List<UserSkillProgress> skillProgress,
            String skillName) {

        return skillProgress.stream()
                .filter(progress ->
                        progress.getSkill_id() != null
                                && progress
                                        .getSkill_id()
                                        .getName()
                                        .equalsIgnoreCase(
                                                skillName
                                        )
                )
                .map(
                        UserSkillProgress::getScore
                )
                .filter(score ->
                        score != null
                )
                .findFirst()
                .orElse(null);
    }

    private BigDecimal calculateAverageSkillScore(
            BigDecimal grammarScore,
            BigDecimal vocabularyScore,
            BigDecimal fluencyScore) {

        if (grammarScore == null
                || vocabularyScore == null
                || fluencyScore == null) {

            return null;
        }

        return grammarScore
                .add(
                        vocabularyScore
                )
                .add(
                        fluencyScore
                )
                .divide(
                        new BigDecimal("3"),
                        2,
                        RoundingMode.HALF_UP
                );
    }

    private BigDecimal calculateRecentLessonAverage(
            List<UserLessonProgress> completedLessons) {

        List<UserLessonProgress> recentLessons =
                completedLessons.stream()
                        .filter(progress ->
                                progress.getScore() != null
                        )
                        .sorted(
                                Comparator.comparing(
                                        UserLessonProgress::getCompleted_at,
                                        Comparator.nullsLast(
                                                Comparator.reverseOrder()
                                        )
                                )
                        )
                        .limit(
                                RECENT_LESSON_LIMIT
                        )
                        .toList();

        if (recentLessons.isEmpty()) {

            return null;
        }

        BigDecimal total =
                recentLessons.stream()
                        .map(
                                UserLessonProgress::getScore
                        )
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        return total.divide(
                BigDecimal.valueOf(
                        recentLessons.size()
                ),
                2,
                RoundingMode.HALF_UP
        );
    }

    private String buildReason(
            Level nextLevel,
            BigDecimal grammarScore,
            BigDecimal vocabularyScore,
            BigDecimal fluencyScore,
            BigDecimal averageSkillScore,
            long completedLessons,
            BigDecimal recentLessonAverage,
            boolean readyForPromotion) {

        if (nextLevel == null) {

            return "Learner is already at the highest supported CEFR level.";
        }

        if (grammarScore == null
                || vocabularyScore == null
                || fluencyScore == null) {

            return "Not enough skill progress data is available.";
        }

        if (grammarScore.compareTo(
                MINIMUM_SKILL_SCORE
        ) < 0) {

            return "Grammar score is below the promotion threshold.";
        }

        if (vocabularyScore.compareTo(
                MINIMUM_SKILL_SCORE
        ) < 0) {

            return "Vocabulary score is below the promotion threshold.";
        }

        if (fluencyScore.compareTo(
                MINIMUM_SKILL_SCORE
        ) < 0) {

            return "Fluency score is below the promotion threshold.";
        }

        if (averageSkillScore == null
                || averageSkillScore.compareTo(
                        MINIMUM_AVERAGE_SKILL_SCORE
                ) < 0) {

            return "Average skill score is below the promotion threshold.";
        }

        if (completedLessons <
                MINIMUM_COMPLETED_LESSONS) {

            return "The learner has not completed enough lessons at the current level.";
        }

        if (recentLessonAverage == null
                || recentLessonAverage.compareTo(
                        MINIMUM_RECENT_LESSON_AVERAGE
                ) < 0) {

            return "Recent lesson performance is below the promotion threshold.";
        }

        if (readyForPromotion) {

            return "Learner satisfies the progression requirements for "
                    + nextLevel.getCode()
                    + ".";
        }

        return "Learner is not ready for promotion.";
    }
}