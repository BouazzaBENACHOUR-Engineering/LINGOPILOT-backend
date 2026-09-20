package com.example.AiLanguageApp.Learning.Service;

import com.example.AiLanguageApp.Learning.DTO.Request.LearningExerciseSubmissionRequest;
import com.example.AiLanguageApp.Learning.DTO.Response.LearningExerciseResponse;
import com.example.AiLanguageApp.Learning.DTO.Response.LearningExerciseSubmissionResponse;
import com.example.AiLanguageApp.Learning.DTO.Response.LearningLessonResponse;
import com.example.AiLanguageApp.Learning.DTO.Response.LessonSectionResponse;
import com.example.AiLanguageApp.Repository.ExerciseRepository;
import com.example.AiLanguageApp.Repository.LessonRepository;
import com.example.AiLanguageApp.Repository.LessonSectionRepository;
import com.example.AiLanguageApp.Repository.UserExerciseAttemptRepository;
import com.example.AiLanguageApp.Repository.UserLessonProgressRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.model.Exercise;
import com.example.AiLanguageApp.model.Lesson;
import com.example.AiLanguageApp.model.LessonSection;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserExerciseAttempt;
import com.example.AiLanguageApp.model.UserLessonProgress;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class LearningServiceImpl implements LearningService {

    private final LessonRepository lessonRepository;
    private final LessonSectionRepository lessonSectionRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final UserLessonProgressRepository userLessonProgressRepository;
    private final UserExerciseAttemptRepository userExerciseAttemptRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public LearningServiceImpl(
            LessonRepository lessonRepository,
            LessonSectionRepository lessonSectionRepository,
            ExerciseRepository exerciseRepository,
            UserRepository userRepository,
            UserLessonProgressRepository userLessonProgressRepository,
            UserExerciseAttemptRepository userExerciseAttemptRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonSectionRepository = lessonSectionRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.userLessonProgressRepository = userLessonProgressRepository;
        this.userExerciseAttemptRepository = userExerciseAttemptRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public LearningLessonResponse getLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson does not exist"));

        List<Exercise> exercises = entityManager.createQuery(
                        "select e from Exercise e where e.lesson_id.id = :lessonId order by e.sequence",
                        Exercise.class)
                .setParameter("lessonId", lessonId)
                .getResultList();

        LearningLessonResponse response = new LearningLessonResponse();
        response.setLesson(lesson);
        response.setSections(lessonSectionRepository.findByLessonOrdered(lessonId).stream().map(this::toSection).toList());
        response.setExercises(exercises.stream().map(this::toExercise).toList());
        return response;
    }

    @Override
    @Transactional
    public UserLessonProgress startLesson(Long lessonId, Long userId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson does not exist"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));

        List<UserLessonProgress> existing = entityManager.createQuery(
                        "select p from UserLessonProgress p where p.user_id.id = :userId and p.lesson_id.id = :lessonId",
                        UserLessonProgress.class)
                .setParameter("userId", userId)
                .setParameter("lessonId", lessonId)
                .getResultList();

        if (!existing.isEmpty()) return existing.get(0);

        UserLessonProgress progress = new UserLessonProgress();
        progress.setUser_id(user);
        progress.setLesson_id(lesson);
        progress.setStatus("IN_PROGRESS");
        progress.setCompletion_percentage(BigDecimal.ZERO);
        progress.setScore(BigDecimal.ZERO);
        progress.setStarted_at(LocalDateTime.now());
        return userLessonProgressRepository.save(progress);
    }

    @Override
    @Transactional
    public LearningExerciseSubmissionResponse submit(Long exerciseId, LearningExerciseSubmissionRequest request) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise does not exist"));
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        Lesson lesson = exercise.getLesson_id();

        UserLessonProgress progress = startLesson(lesson.getId(), user.getId());
        String answer = request.getAnswer().trim();
        boolean correct = isCorrect(exercise.getCorrect_answer(), answer);
        BigDecimal score = correct ? BigDecimal.valueOf(exercise.getPoints()) : BigDecimal.ZERO;

        UserExerciseAttempt attempt = new UserExerciseAttempt();
        attempt.setUser_id(user);
        attempt.setExercise_id(exercise);
        attempt.setAnswer(answer);
        attempt.setIs_correct(correct);
        attempt.setScore(score);
        attempt.setAttempted_at(LocalDateTime.now());
        attempt = userExerciseAttemptRepository.save(attempt);

        updateProgress(progress, user.getId(), lesson.getId());

        LearningExerciseSubmissionResponse response = new LearningExerciseSubmissionResponse();
        response.setAttempt_id(attempt.getId());
        response.setExercise_id(exercise.getId());
        response.setAnswer(answer);
        response.setIs_correct(correct);
        response.setScore(score);
        response.setExplanation(correct
                ? "Correct. Your answer matches the expected answer for this lesson."
                : "Review the lesson rule and compare your answer with: " + exercise.getCorrect_answer());
        response.setLesson_status(progress.getStatus());
        response.setCompletion_percentage(progress.getCompletion_percentage());
        response.setLesson_score(progress.getScore());
        return response;
    }

    private boolean isCorrect(String expected, String answer) {
        if (expected == null || expected.isBlank()) return false;
        String normalizedAnswer = normalize(answer);
        return List.of(expected.split("\\|"))
                .stream()
                .map(this::normalize)
                .anyMatch(normalizedAnswer::equals);
    }

    private String normalize(String value) {
        return value == null ? "" : value
                .trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("[.!?]+$", "")
                .replaceAll("\\s+", " ");
    }

    private void updateProgress(UserLessonProgress progress, Long userId, Long lessonId) {
        List<Exercise> exercises = entityManager.createQuery(
                        "select e from Exercise e where e.lesson_id.id = :lessonId order by e.sequence",
                        Exercise.class)
                .setParameter("lessonId", lessonId)
                .getResultList();

        List<UserExerciseAttempt> attempts = entityManager.createQuery(
                        "select a from UserExerciseAttempt a where a.user_id.id = :userId and a.exercise_id.lesson_id.id = :lessonId order by a.attempted_at",
                        UserExerciseAttempt.class)
                .setParameter("userId", userId)
                .setParameter("lessonId", lessonId)
                .getResultList();

        long attempted = exercises.stream()
                .filter(exercise -> attempts.stream().anyMatch(a -> a.getExercise_id().getId().equals(exercise.getId())))
                .count();

        BigDecimal completion = exercises.isEmpty() ? BigDecimal.ZERO : BigDecimal.valueOf(attempted)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(exercises.size()), 2, RoundingMode.HALF_UP);
        progress.setCompletion_percentage(completion);

        BigDecimal totalPoints = exercises.stream()
                .map(e -> BigDecimal.valueOf(e.getPoints()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal earned = exercises.stream()
                .map(exercise -> attempts.stream()
                        .filter(a -> a.getExercise_id().getId().equals(exercise.getId()))
                        .max(Comparator.comparing(UserExerciseAttempt::getAttempted_at))
                        .map(UserExerciseAttempt::getScore)
                        .orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        progress.setScore(totalPoints.signum() == 0 ? BigDecimal.ZERO : earned
                .multiply(BigDecimal.valueOf(100))
                .divide(totalPoints, 2, RoundingMode.HALF_UP));

        if (!exercises.isEmpty() && attempted == exercises.size()) {
            progress.setStatus("COMPLETED");
            progress.setCompleted_at(LocalDateTime.now());
        } else {
            progress.setStatus("IN_PROGRESS");
        }
        userLessonProgressRepository.save(progress);
    }

    private LessonSectionResponse toSection(LessonSection section) {
        LessonSectionResponse response = new LessonSectionResponse();
        response.setId(section.getId());
        response.setSection_type(section.getSection_type());
        response.setTitle(section.getTitle());
        response.setContent(section.getContent());
        response.setSequence(section.getSequence());
        return response;
    }

    private LearningExerciseResponse toExercise(Exercise exercise) {
        LearningExerciseResponse response = new LearningExerciseResponse();
        response.setId(exercise.getId());
        response.setType(exercise.getType());
        response.setQuestion(exercise.getQuestion());
        response.setDifficulty(exercise.getDifficulty());
        response.setPoints(exercise.getPoints());
        response.setSequence(exercise.getSequence());
        return response;
    }
}
