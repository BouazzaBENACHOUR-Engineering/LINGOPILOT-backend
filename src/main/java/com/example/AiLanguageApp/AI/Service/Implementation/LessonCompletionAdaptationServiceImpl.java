package com.example.AiLanguageApp.AI.Service.Implementation;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.AI.Progression.LevelReadinessResult;
import com.example.AiLanguageApp.AI.Service.LessonCompletionAdaptationService;
import com.example.AiLanguageApp.AI.Service.LevelProgressionService;
import com.example.AiLanguageApp.AI.Service.UserSkillProgressUpdater;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Course;
import com.example.AiLanguageApp.model.Lesson;
import com.example.AiLanguageApp.model.Level;
import com.example.AiLanguageApp.model.Module;
import com.example.AiLanguageApp.model.Skill;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserLessonProgress;

@Service
public class LessonCompletionAdaptationServiceImpl
        implements LessonCompletionAdaptationService {

    private final UserSkillProgressUpdater userSkillProgressUpdater;
    private final LevelProgressionService levelProgressionService;

    public LessonCompletionAdaptationServiceImpl(
            UserSkillProgressUpdater userSkillProgressUpdater,
            LevelProgressionService levelProgressionService) {

        this.userSkillProgressUpdater =
                userSkillProgressUpdater;

        this.levelProgressionService =
                levelProgressionService;
    }

    @Override
    public void adapt(
            UserLessonProgress lessonProgress) {

        if (lessonProgress == null) {

            throw new IllegalArgumentException(
                    "Lesson progress cannot be null"
            );
        }

        if (!"COMPLETED".equalsIgnoreCase(
                lessonProgress.getStatus())) {

            throw new IllegalStateException(
                    "Lesson must be completed before adaptation"
            );
        }

        if (lessonProgress.getScore() == null) {

            throw new IllegalStateException(
                    "Completed lesson has no score"
            );
        }

        User user =
                lessonProgress.getUser_id();

        Lesson lesson =
                lessonProgress.getLesson_id();

        Skill skill =
                lesson.getSkill_id();

        Module module =
                lesson.getModule_id();

        Course course =
                module.getCourse_id();

        Level level =
                course.getLevel_id();

        AiConversation conversation =
                new AiConversation();

        conversation.setUser_id(
                user
        );

        conversation.setLanguage_id(
                course.getLanguage_id()
        );

        conversation.setLevel_id(
                level
        );

        userSkillProgressUpdater.update(
                user,
                conversation,
                skill,
                level,
                lessonProgress.getScore()
        );

        LevelReadinessResult readiness =
                levelProgressionService
                        .evaluateReadiness(
                                user.getId(),
                                course
                                        .getLanguage_id()
                                        .getId()
                        );

        if (Boolean.TRUE.equals(
                readiness.getReady_for_promotion())) {

            levelProgressionService
                    .promoteIfReady(
                            user.getId(),
                            course
                                    .getLanguage_id()
                                    .getId()
                    );
        }
    }
}