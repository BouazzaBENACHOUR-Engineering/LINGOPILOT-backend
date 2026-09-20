package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.Context.LearnerContext;
import com.example.AiLanguageApp.AI.DTO.LessonRecommendation;
import com.example.AiLanguageApp.AI.Service.LearnerContextService;
import com.example.AiLanguageApp.AI.Service.LessonRecommendationService;
import com.example.AiLanguageApp.AI.Service.SkillResolver;
import com.example.AiLanguageApp.Repository.AiConversationRepository;
import com.example.AiLanguageApp.Repository.LessonRepository;
import com.example.AiLanguageApp.Repository.UserLanguageRepository;
import com.example.AiLanguageApp.Repository.UserLessonProgressRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Course;
import com.example.AiLanguageApp.model.Lesson;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.Module;
import com.example.AiLanguageApp.model.Skill;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserLanguage;
import com.example.AiLanguageApp.model.UserLessonProgress;

@Service
public class LessonRecommendationServiceImpl
        implements LessonRecommendationService {

    private final AiConversationRepository aiConversationRepository;
    private final LessonRepository lessonRepository;
    private final UserLessonProgressRepository userLessonProgressRepository;
    private final UserLanguageRepository userLanguageRepository;
    private final LearnerContextService learnerContextService;
    private final SkillResolver skillResolver;

    public LessonRecommendationServiceImpl(
            AiConversationRepository aiConversationRepository,
            LessonRepository lessonRepository,
            UserLessonProgressRepository userLessonProgressRepository,
            UserLanguageRepository userLanguageRepository,
            LearnerContextService learnerContextService,
            SkillResolver skillResolver) {

        this.aiConversationRepository =
                aiConversationRepository;

        this.lessonRepository =
                lessonRepository;

        this.userLessonProgressRepository =
                userLessonProgressRepository;

        this.userLanguageRepository =
                userLanguageRepository;

        this.learnerContextService =
                learnerContextService;

        this.skillResolver =
                skillResolver;
    }

    @Override
    @Transactional(readOnly = true)
    public LessonRecommendation recommend(
            Long conversationId) {

        AiConversation conversation =
                aiConversationRepository
                        .findById(conversationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI conversation does not exist"
                                )
                        );

        User user =
                conversation.getUser_id();

        Level level =
                resolveLevel(
                        conversation,
                        user
                );

        LearnerContext learnerContext =
                learnerContextService
                        .buildContext(
                                conversation
                        );

        List<SkillPriority> priorities =
                buildSkillPriorities(
                        learnerContext
                );

        if (priorities.isEmpty()) {

            throw new ResourceNotFoundException(
                    "No learner skill progress available"
            );
        }

        for (SkillPriority priority : priorities) {

            Skill skill =
                    skillResolver.resolve(
                            priority.getSkillName()
                    );

            List<Lesson> candidates =
                    lessonRepository
                            .findRecommendationCandidates(
                                    conversation
                                            .getLanguage_id()
                                            .getId(),
                                    level.getId(),
                                    skill.getId()
                            );

            if (candidates.isEmpty()) {
                continue;
            }

            RecommendationSelection selection =
                    selectLesson(
                            candidates,
                            user
                    );

            if (selection != null) {

                return buildRecommendation(
                        selection.getLesson(),
                        selection.getProgress(),
                        priority
                );
            }
        }

        throw new ResourceNotFoundException(
                "No suitable lesson available for the learner's current level"
        );
    }

    private RecommendationSelection selectLesson(
            List<Lesson> candidates,
            User user) {

        for (Lesson lesson : candidates) {

            UserLessonProgress progress =
                    userLessonProgressRepository
                            .findForUserAndLesson(
                                    user.getId(),
                                    lesson.getId()
                            )
                            .orElse(null);

            if (progress != null
                    && "IN_PROGRESS".equalsIgnoreCase(
                            progress.getStatus()
                    )) {

                return new RecommendationSelection(
                        lesson,
                        progress
                );
            }
        }

        for (Lesson lesson : candidates) {

            UserLessonProgress progress =
                    userLessonProgressRepository
                            .findForUserAndLesson(
                                    user.getId(),
                                    lesson.getId()
                            )
                            .orElse(null);

            if (progress == null) {

                return new RecommendationSelection(
                        lesson,
                        null
                );
            }

            if (!"COMPLETED".equalsIgnoreCase(
                    progress.getStatus()
            )) {

                return new RecommendationSelection(
                        lesson,
                        progress
                );
            }
        }

        return null;
    }

    private List<SkillPriority> buildSkillPriorities(
            LearnerContext context) {

        List<SkillPriority> priorities =
                new ArrayList<>();

        if (context.getGrammar_score() != null) {

            priorities.add(
                    new SkillPriority(
                            "Grammar",
                            context.getGrammar_score(),
                            1
                    )
            );
        }

        if (context.getVocabulary_score() != null) {

            priorities.add(
                    new SkillPriority(
                            "Vocabulary",
                            context.getVocabulary_score(),
                            2
                    )
            );
        }

        if (context.getFluency_score() != null) {

            priorities.add(
                    new SkillPriority(
                            "Fluency",
                            context.getFluency_score(),
                            3
                    )
            );
        }

        priorities.sort(
                Comparator
                        .comparing(
                                SkillPriority::getScore
                        )
                        .thenComparing(
                                SkillPriority::getTieBreaker
                        )
        );

        return priorities;
    }

    private Level resolveLevel(
            AiConversation conversation,
            User user) {

        UserLanguage userLanguage =
                userLanguageRepository
                        .findByUserAndLanguage(
                                user.getId(),
                                conversation
                                        .getLanguage_id()
                                        .getId()
                        )
                        .orElse(null);

        if (userLanguage != null
                && userLanguage.getLevel_id() != null) {

            return userLanguage
                    .getLevel_id();
        }

        if (conversation.getLevel_id() != null) {

            return conversation
                    .getLevel_id();
        }

        throw new ResourceNotFoundException(
                "Learner level does not exist for this language"
        );
    }

    private LessonRecommendation buildRecommendation(
            Lesson lesson,
            UserLessonProgress progress,
            SkillPriority priority) {

        Module module =
                lesson.getModule_id();

        Course course =
                module.getCourse_id();

        LessonRecommendation recommendation =
                new LessonRecommendation();

        recommendation.setLesson_id(
                lesson.getId()
        );

        recommendation.setLesson_title(
                lesson.getTitle()
        );

        recommendation.setLesson_description(
                lesson.getDescription()
        );

        recommendation.setEstimated_minutes(
                lesson.getEstimated_minutes()
        );

        recommendation.setSkill_id(
                lesson.getSkill_id()
                        .getId()
        );

        recommendation.setSkill_name(
                lesson.getSkill_id()
                        .getName()
        );

        recommendation.setSkill_score(
                priority.getScore()
        );

        recommendation.setModule_id(
                module.getId()
        );

        recommendation.setModule_title(
                module.getTitle()
        );

        recommendation.setCourse_id(
                course.getId()
        );

        recommendation.setCourse_title(
                course.getTitle()
        );

        if (progress != null) {

            recommendation.setProgress_status(
                    progress.getStatus()
            );

            recommendation.setCompletion_percentage(
                    progress.getCompletion_percentage()
            );

        } else {

            recommendation.setProgress_status(
                    "NOT_STARTED"
            );

            recommendation.setCompletion_percentage(
                    BigDecimal.ZERO
            );
        }

        if (progress != null
                && "IN_PROGRESS".equalsIgnoreCase(
                        progress.getStatus()
                )) {

            recommendation.setReason(
                    "Resume this lesson because "
                            + priority.getSkillName()
                            + " is one of your current priority skills."
            );

        } else {

            recommendation.setReason(
                    "Recommended because "
                            + priority.getSkillName()
                            + " is one of your current priority skills."
            );
        }

        return recommendation;
    }

    private static class SkillPriority {

        private final String skillName;
        private final BigDecimal score;
        private final Integer tieBreaker;

        public SkillPriority(
                String skillName,
                BigDecimal score,
                Integer tieBreaker) {

            this.skillName =
                    skillName;

            this.score =
                    score;

            this.tieBreaker =
                    tieBreaker;
        }

        public String getSkillName() {
            return skillName;
        }

        public BigDecimal getScore() {
            return score;
        }

        public Integer getTieBreaker() {
            return tieBreaker;
        }
    }

    private static class RecommendationSelection {

        private final Lesson lesson;
        private final UserLessonProgress progress;

        public RecommendationSelection(
                Lesson lesson,
                UserLessonProgress progress) {

            this.lesson =
                    lesson;

            this.progress =
                    progress;
        }

        public Lesson getLesson() {
            return lesson;
        }

        public UserLessonProgress getProgress() {
            return progress;
        }
    }
}