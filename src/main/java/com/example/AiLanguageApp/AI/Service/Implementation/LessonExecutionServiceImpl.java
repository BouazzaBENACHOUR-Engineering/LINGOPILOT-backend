package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.DTO.LessonExerciseItem;
import com.example.AiLanguageApp.AI.DTO.LessonExecutionResponse;
import com.example.AiLanguageApp.AI.Service.LessonExecutionService;
import com.example.AiLanguageApp.Repository.AiConversationRepository;
import com.example.AiLanguageApp.Repository.ExerciseRepository;
import com.example.AiLanguageApp.Repository.LessonRepository;
import com.example.AiLanguageApp.Repository.UserLessonProgressRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Exercise;
import com.example.AiLanguageApp.model.Lesson;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserLessonProgress;

@Service
public class LessonExecutionServiceImpl
        implements LessonExecutionService {

    private final AiConversationRepository aiConversationRepository;
    private final LessonRepository lessonRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserLessonProgressRepository userLessonProgressRepository;

    public LessonExecutionServiceImpl(
            AiConversationRepository aiConversationRepository,
            LessonRepository lessonRepository,
            ExerciseRepository exerciseRepository,
            UserLessonProgressRepository userLessonProgressRepository) {

        this.aiConversationRepository =
                aiConversationRepository;

        this.lessonRepository =
                lessonRepository;

        this.exerciseRepository =
                exerciseRepository;

        this.userLessonProgressRepository =
                userLessonProgressRepository;
    }

    @Override
    @Transactional
    public LessonExecutionResponse startLesson(
            Long conversationId,
            Long lessonId) {

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

        Lesson lesson =
                lessonRepository
                        .findById(lessonId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson does not exist"
                                )
                        );

        if (!lesson.getModule_id()
                .getCourse_id()
                .getLanguage_id()
                .getId()
                .equals(
                        conversation
                                .getLanguage_id()
                                .getId()
                )) {

            throw new IllegalArgumentException(
                    "Lesson language does not match conversation language"
            );
        }

        UserLessonProgress progress =
                userLessonProgressRepository
                        .findForUserAndLesson(
                                user.getId(),
                                lesson.getId()
                        )
                        .orElse(null);

        if (progress == null) {

            progress =
                    new UserLessonProgress();

            progress.setUser_id(
                    user
            );

            progress.setLesson_id(
                    lesson
            );

            progress.setStatus(
                    "IN_PROGRESS"
            );

            progress.setCompletion_percentage(
                    BigDecimal.ZERO
            );

            progress.setScore(
                    null
            );

            progress.setStarted_at(
                    LocalDateTime.now()
            );

            progress.setCompleted_at(
                    null
            );

            progress =
                    userLessonProgressRepository
                            .save(progress);

        } else if ("COMPLETED".equalsIgnoreCase(
                progress.getStatus())) {

            throw new IllegalStateException(
                    "Lesson is already completed"
            );

        } else {

            progress.setStatus(
                    "IN_PROGRESS"
            );

            if (progress.getStarted_at() == null) {

                progress.setStarted_at(
                        LocalDateTime.now()
                );
            }

            progress =
                    userLessonProgressRepository
                            .save(progress);
        }

        List<Exercise> exercises =
                exerciseRepository
                        .findByLessonOrdered(
                                lesson.getId()
                        );

        List<LessonExerciseItem> exerciseItems =
                exercises.stream()
                        .map(this::toExerciseItem)
                        .toList();

        LessonExecutionResponse response =
                new LessonExecutionResponse();

        response.setLesson_progress_id(
                progress.getId()
        );

        response.setLesson_id(
                lesson.getId()
        );

        response.setLesson_title(
                lesson.getTitle()
        );

        response.setSkill_name(
                lesson.getSkill_id()
                        .getName()
        );

        response.setStatus(
                progress.getStatus()
        );

        response.setCompletion_percentage(
                progress.getCompletion_percentage()
        );

        response.setStarted_at(
                progress.getStarted_at()
        );

        response.setExercises(
                exerciseItems
        );

        return response;
    }

    private LessonExerciseItem toExerciseItem(
            Exercise exercise) {

        LessonExerciseItem item =
                new LessonExerciseItem();

        item.setExercise_id(
                exercise.getId()
        );

        item.setType(
                exercise.getType()
        );

        item.setQuestion(
                exercise.getQuestion()
        );

        item.setDifficulty(
                exercise.getDifficulty()
        );

        item.setPoints(
                exercise.getPoints()
        );

        item.setSequence(
                exercise.getSequence()
        );

        return item;
    }
}