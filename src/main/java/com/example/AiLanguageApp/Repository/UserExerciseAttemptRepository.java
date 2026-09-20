package com.example.AiLanguageApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.UserExerciseAttempt;

public interface UserExerciseAttemptRepository
        extends JpaRepository<UserExerciseAttempt, Long> {

    @Query("""
            SELECT uea
            FROM UserExerciseAttempt uea
            WHERE uea.user_id.id = :userId
              AND uea.exercise_id.lesson_id.id = :lessonId
            ORDER BY uea.attempted_at ASC
            """)
    List<UserExerciseAttempt> findByUserAndLesson(
            @Param("userId") Long userId,
            @Param("lessonId") Long lessonId
    );

    @Query("""
            SELECT uea
            FROM UserExerciseAttempt uea
            WHERE uea.user_id.id = :userId
              AND uea.exercise_id.id = :exerciseId
            ORDER BY uea.attempted_at DESC
            """)
    List<UserExerciseAttempt> findByUserAndExercise(
            @Param("userId") Long userId,
            @Param("exerciseId") Long exerciseId
    );
}