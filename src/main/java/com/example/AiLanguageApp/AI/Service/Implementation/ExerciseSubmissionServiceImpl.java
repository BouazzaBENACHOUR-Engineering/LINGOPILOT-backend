package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AiLanguageApp.AI.DTO.Request.ExerciseSubmissionRequest;
import com.example.AiLanguageApp.AI.DTO.Response.ExerciseSubmissionResponse;
import com.example.AiLanguageApp.AI.Grading.ExerciseGradeResult;
import com.example.AiLanguageApp.AI.Service.ExerciseGradingService;
import com.example.AiLanguageApp.AI.Service.ExerciseSubmissionService;
import com.example.AiLanguageApp.AI.Service.LessonCompletionAdaptationService;
import com.example.AiLanguageApp.Repository.AiConversationRepository;
import com.example.AiLanguageApp.Repository.ExerciseRepository;
import com.example.AiLanguageApp.Repository.UserExerciseAttemptRepository;
import com.example.AiLanguageApp.Repository.UserLessonProgressRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.AiConversation;
import com.example.AiLanguageApp.model.Exercise;
import com.example.AiLanguageApp.model.Lesson;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserExerciseAttempt;
import com.example.AiLanguageApp.model.UserLessonProgress;

@Service
public class ExerciseSubmissionServiceImpl
        implements ExerciseSubmissionService {

    private final ExerciseRepository exerciseRepository;
    private final UserExerciseAttemptRepository userExerciseAttemptRepository;
    private final UserLessonProgressRepository userLessonProgressRepository;
    private final AiConversationRepository aiConversationRepository;
    private final ExerciseGradingService exerciseGradingService;
    private final LessonCompletionAdaptationService lessonCompletionAdaptationService;

    public ExerciseSubmissionServiceImpl(
            ExerciseRepository exerciseRepository,
            UserExerciseAttemptRepository userExerciseAttemptRepository,
            UserLessonProgressRepository userLessonProgressRepository,
            AiConversationRepository aiConversationRepository,
            ExerciseGradingService exerciseGradingService,
            LessonCompletionAdaptationService lessonCompletionAdaptationService) {

        this.exerciseRepository =
                exerciseRepository;

        this.userExerciseAttemptRepository =
                userExerciseAttemptRepository;

        this.userLessonProgressRepository =
                userLessonProgressRepository;

        this.aiConversationRepository =
                aiConversationRepository;

        this.exerciseGradingService =
                exerciseGradingService;

        this.lessonCompletionAdaptationService =
                lessonCompletionAdaptationService;
    }

    @Override
    @Transactional
    public ExerciseSubmissionResponse submit(
            Long exerciseId,
            ExerciseSubmissionRequest request) {

        AiConversation conversation =
                aiConversationRepository
                        .findById(
                                request.getConversation_id()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "AI conversation does not exist"
                                )
                        );

        User user =
                conversation.getUser_id();

        Exercise exercise =
                exerciseRepository
                        .findById(exerciseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exercise does not exist"
                                )
                        );

        Lesson lesson =
                exercise.getLesson_id();

        validateLessonLanguage(
                conversation,
                lesson
        );

        UserLessonProgress progress =
                userLessonProgressRepository
                        .findForUserAndLesson(
                                user.getId(),
                                lesson.getId()
                        )
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Lesson must be started before submitting an exercise"
                                )
                        );

        if ("COMPLETED".equalsIgnoreCase(
                progress.getStatus())) {

            throw new IllegalStateException(
                    "Lesson is already completed"
            );
        }

        String answer =
                request.getAnswer()
                        .trim();

        ExerciseGradeResult gradeResult =
                exerciseGradingService
                        .grade(
                                exercise,
                                answer
                        );

        UserExerciseAttempt attempt =
                new UserExerciseAttempt();

        attempt.setUser_id(
                user
        );

        attempt.setExercise_id(
                exercise
        );

        attempt.setAnswer(
                answer
        );

        attempt.setIs_correct(
                gradeResult.getCorrect()
        );

        attempt.setScore(
                gradeResult.getScore()
        );

        attempt.setAttempted_at(
                LocalDateTime.now()
        );

        attempt =
                userExerciseAttemptRepository
                        .save(
                                attempt
                        );

        boolean wasCompleted =
                "COMPLETED".equalsIgnoreCase(
                        progress.getStatus()
                );

        updateLessonProgress(
                user,
                lesson,
                progress
        );

        boolean isNowCompleted =
                "COMPLETED".equalsIgnoreCase(
                        progress.getStatus()
                );

        if (!wasCompleted && isNowCompleted) {

            lessonCompletionAdaptationService.adapt(
                    progress
            );
        }

        return buildResponse(
                attempt,
                progress,
                lesson
        );
    }

    private void updateLessonProgress(
            User user,
            Lesson lesson,
            UserLessonProgress progress) {

        List<Exercise> exercises =
                exerciseRepository
                        .findByLessonOrdered(
                                lesson.getId()
                        );

        if (exercises.isEmpty()) {
            return;
        }

        List<UserExerciseAttempt> attempts =
                userExerciseAttemptRepository
                        .findByUserAndLesson(
                                user.getId(),
                                lesson.getId()
                        );

        long attemptedExercises =
                exercises.stream()
                        .filter(exercise ->
                                attempts.stream()
                                        .anyMatch(attempt ->
                                                attempt
                                                        .getExercise_id()
                                                        .getId()
                                                        .equals(
                                                                exercise.getId()
                                                        )
                                        )
                        )
                        .count();

        BigDecimal completionPercentage =
                BigDecimal.valueOf(
                                attemptedExercises
                        )
                        .multiply(
                                BigDecimal.valueOf(100)
                        )
                        .divide(
                                BigDecimal.valueOf(
                                        exercises.size()
                                ),
                                2,
                                RoundingMode.HALF_UP
                        );

        progress.setCompletion_percentage(
                completionPercentage
        );

        BigDecimal totalPossiblePoints =
                exercises.stream()
                        .map(exercise ->
                                BigDecimal.valueOf(
                                        exercise.getPoints()
                                )
                        )
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        BigDecimal earnedPoints =
                exercises.stream()
                        .map(exercise ->
                                latestScoreForExercise(
                                        attempts,
                                        exercise.getId()
                                )
                        )
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        if (totalPossiblePoints.compareTo(
                BigDecimal.ZERO) > 0) {

            BigDecimal lessonScore =
                    earnedPoints
                            .multiply(
                                    BigDecimal.valueOf(100)
                            )
                            .divide(
                                    totalPossiblePoints,
                                    2,
                                    RoundingMode.HALF_UP
                            );

            progress.setScore(
                    lessonScore
            );
        }

        if (attemptedExercises ==
                exercises.size()) {

            progress.setStatus(
                    "COMPLETED"
            );

            if (progress.getCompleted_at() == null) {

                progress.setCompleted_at(
                        LocalDateTime.now()
                );
            }

        } else {

            progress.setStatus(
                    "IN_PROGRESS"
            );
        }

        userLessonProgressRepository.save(
                progress
        );
    }

    private BigDecimal latestScoreForExercise(
            List<UserExerciseAttempt> attempts,
            Long exerciseId) {

        return attempts.stream()
                .filter(attempt ->
                        attempt
                                .getExercise_id()
                                .getId()
                                .equals(
                                        exerciseId
                                )
                )
                .filter(attempt ->
                        attempt.getScore() != null
                )
                .reduce(
                        (first, second) ->
                                second
                )
                .map(
                        UserExerciseAttempt::getScore
                )
                .orElse(
                        BigDecimal.ZERO
                );
    }

    private void validateLessonLanguage(
            AiConversation conversation,
            Lesson lesson) {

        Long conversationLanguageId =
                conversation
                        .getLanguage_id()
                        .getId();

        Long lessonLanguageId =
                lesson
                        .getModule_id()
                        .getCourse_id()
                        .getLanguage_id()
                        .getId();

        if (!conversationLanguageId.equals(
                lessonLanguageId)) {

            throw new IllegalArgumentException(
                    "Exercise does not belong to the conversation language"
            );
        }
    }

    private ExerciseSubmissionResponse buildResponse(
            UserExerciseAttempt attempt,
            UserLessonProgress progress,
            Lesson lesson) {

        ExerciseSubmissionResponse response =
                new ExerciseSubmissionResponse();

        response.setAttempt_id(
                attempt.getId()
        );

        response.setExercise_id(
                attempt
                        .getExercise_id()
                        .getId()
        );

        response.setExercise_type(
                attempt
                        .getExercise_id()
                        .getType()
        );

        response.setAnswer(
                attempt.getAnswer()
        );

        response.setIs_correct(
                attempt.getIs_correct()
        );

        response.setScore(
                attempt.getScore()
        );

        response.setAttempted_at(
                attempt.getAttempted_at()
        );

        response.setLesson_id(
                lesson.getId()
        );

        response.setLesson_status(
                progress.getStatus()
        );

        response.setCompletion_percentage(
                progress.getCompletion_percentage()
        );

        response.setLesson_score(
                progress.getScore()
        );

        return response;
    }
}