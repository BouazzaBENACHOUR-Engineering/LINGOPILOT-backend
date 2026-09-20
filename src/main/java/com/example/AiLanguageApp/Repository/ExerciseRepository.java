package com.example.AiLanguageApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.Exercise;

public interface ExerciseRepository
        extends JpaRepository<Exercise, Long> {

    @Query("""
            SELECT e
            FROM Exercise e
            WHERE e.lesson_id.id = :lessonId
            ORDER BY e.sequence ASC
            """)
    List<Exercise> findByLessonOrdered(
            @Param("lessonId") Long lessonId
    );
}